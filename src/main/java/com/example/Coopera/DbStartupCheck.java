package com.example.Coopera;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DbStartupCheck implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DbStartupCheck.class);

    private final DataSource dataSource;
    private final Environment env;

    public DbStartupCheck(DataSource dataSource, Environment env) {
        this.dataSource = dataSource;
        this.env = env;
    }

    @Override
    public void run(String... args) {
        boolean failFast = env.getProperty("app.db.fail-fast", Boolean.class, Boolean.TRUE);
        // Log useful Oracle Wallet/TNS context for troubleshooting
        String tnsAdminFromUrl = null;
        String jdbcUrl = env.getProperty("spring.datasource.url");
        if (jdbcUrl != null) {
            int idx = jdbcUrl.toLowerCase().indexOf("tns_admin=");
            if (idx >= 0) {
                tnsAdminFromUrl = jdbcUrl.substring(idx + "tns_admin=".length());
            }
        }
        String tnsAdminProp = env.getProperty("spring.datasource.hikari.data-source-properties.oracle.net.tns_admin");
        String tnsAdminEnv = System.getenv("TNS_ADMIN");
        String tnsAdminSys = System.getProperty("oracle.net.tns_admin");

        log.info("DB startup check: TNS_ADMIN sources — url='{}', hikariProp='{}', envVar='{}', sysProp='{}'",
                tnsAdminFromUrl, tnsAdminProp, tnsAdminEnv, tnsAdminSys);

    // Show which DB username Spring resolved and whether env vars are present
    String resolvedUser = env.getProperty("spring.datasource.username");
    boolean hasEnvUser = System.getenv("DB_USERNAME") != null;
    boolean hasEnvPass = System.getenv("DB_PASSWORD") != null;
    log.info("DB startup check: datasource.url='{}' user='{}' (DB_USERNAME set? {} | DB_PASSWORD set? {})",
        jdbcUrl, resolvedUser, hasEnvUser, hasEnvPass);

        // Choose an existing path to validate
        String chosen = firstNonBlank(tnsAdminProp, tnsAdminFromUrl, tnsAdminEnv, tnsAdminSys);
        if (chosen != null) {
            Path base = Paths.get(chosen);
            Path tnsnames = base.resolve("tnsnames.ora");
            Path sqlnet = base.resolve("sqlnet.ora");
            if (!Files.exists(base)) {
                log.error("TNS_ADMIN folder does not exist: {}", base.toAbsolutePath());
            } else {
                log.info("TNS_ADMIN folder found: {}", base.toAbsolutePath());
                try {
                    var files = Files.list(base)
                            .map(p -> p.getFileName().toString())
                            .sorted()
                            .toList();
                    log.info("TNS_ADMIN folder contents: {}", files);
                } catch (Exception e) {
                    log.warn("Could not list TNS_ADMIN folder contents: {}", e.getMessage());
                }
            }
            if (!Files.exists(tnsnames)) {
                log.error("Missing tnsnames.ora at: {}", tnsnames.toAbsolutePath());
            }
            if (!Files.exists(sqlnet)) {
                log.warn("sqlnet.ora not found at: {} (not always required, but recommended)", sqlnet.toAbsolutePath());
            }
        } else {
            log.warn("No TNS_ADMIN configuration detected in URL, Hikari properties, env var, or system property.");
        }

        log.info("DB startup check: attempting to obtain connection from DataSource...");
        try (Connection c = dataSource.getConnection()) {
            if (c != null && !c.isClosed()) {
                log.info("Successfully obtained JDBC connection (catalog={})", c.getCatalog());
            } else {
                log.error("Obtained connection is null or closed — failing application startup");
                if (failFast) {
                    throw new IllegalStateException("Unable to obtain a valid JDBC connection from DataSource");
                } else {
                    log.warn("Continuing startup despite no DB connection because app.db.fail-fast=false");
                    return;
                }
            }
        } catch (SQLException ex) {
            log.error("Failed to obtain JDBC connection at startup: {}", ex.getMessage());
            log.debug("Full exception:", ex);
            if (failFast) {
                // Fail fast: rethrow runtime exception so the application will not start without a DB
                throw new IllegalStateException("Failed to obtain JDBC connection at startup", ex);
            } else {
                log.warn("Continuing startup without DB because app.db.fail-fast=false");
            }
        }
    }

    private static String firstNonBlank(String... values) {
        if (values == null) return null;
        for (String v : values) {
            if (v != null && !v.trim().isEmpty()) return v.trim();
        }
        return null;
    }
}

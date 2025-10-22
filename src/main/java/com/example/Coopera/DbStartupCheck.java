package com.example.Coopera;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbStartupCheck implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DbStartupCheck.class);

    private final DataSource dataSource;

    public DbStartupCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        log.info("DB startup check: attempting to obtain connection from DataSource...");
        try (Connection c = dataSource.getConnection()) {
            if (c != null && !c.isClosed()) {
                log.info("Successfully obtained JDBC connection (catalog={})", c.getCatalog());
            } else {
                log.error("Obtained connection is null or closed — failing application startup");
                throw new IllegalStateException("Unable to obtain a valid JDBC connection from DataSource");
            }
        } catch (SQLException ex) {
            log.error("Failed to obtain JDBC connection at startup: {}", ex.getMessage());
            log.debug("Full exception:", ex);
            // Fail fast: rethrow runtime exception so the application will not start without a DB
            throw new IllegalStateException("Failed to obtain JDBC connection at startup", ex);
        }
    }
}

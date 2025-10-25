package com.example.Coopera.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/conexion")
    public Map<String, String> testConexion() {
        Map<String, String> resultado = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT 'Conexión exitosa a Oracle!' as mensaje FROM DUAL")) {
            
            if (resultSet.next()) {
                resultado.put("estado", "EXITOSO");
                resultado.put("mensaje", resultSet.getString("mensaje"));
                resultado.put("catalogo", connection.getCatalog() != null ? connection.getCatalog() : "N/A");
                resultado.put("url", connection.getMetaData().getURL());
            }
            
        } catch (Exception e) {
            resultado.put("estado", "ERROR");
            resultado.put("mensaje", "Error en la conexión: " + e.getMessage());
        }
        
        return resultado;
    }

    @GetMapping("/db-info")
    public Map<String, String> getDatabaseInfo() {
        Map<String, String> info = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT banner FROM v$version WHERE ROWNUM = 1")) {
            
            if (resultSet.next()) {
                info.put("estado", "EXITOSO");
                info.put("version", resultSet.getString("banner"));
                info.put("usuario", connection.getMetaData().getUserName());
            }
            
        } catch (Exception e) {
            info.put("estado", "ERROR");
            info.put("mensaje", "Error: " + e.getMessage());
        }
        
        return info;
    }

    @GetMapping("/tablas")
    public Map<String, Object> listarTablas() {
        Map<String, Object> resultado = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                 "SELECT table_name FROM user_tables ORDER BY table_name")) {
            
            java.util.List<String> tablas = new java.util.ArrayList<>();
            while (resultSet.next()) {
                tablas.add(resultSet.getString("table_name"));
            }
            
            resultado.put("estado", "EXITOSO");
            resultado.put("total", tablas.size());
            resultado.put("tablas", tablas);
            
        } catch (Exception e) {
            resultado.put("estado", "ERROR");
            resultado.put("mensaje", "Error: " + e.getMessage());
        }
        
        return resultado;
    }

    @GetMapping("/fecha-servidor")
    public Map<String, String> getFechaServidor() {
        Map<String, String> resultado = new HashMap<>();
        
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT SYSDATE FROM DUAL")) {
            
            if (resultSet.next()) {
                resultado.put("estado", "EXITOSO");
                resultado.put("fecha", resultSet.getString(1));
            }
            
        } catch (Exception e) {
            resultado.put("estado", "ERROR");
            resultado.put("mensaje", "Error: " + e.getMessage());
        }
        
        return resultado;
    }
}

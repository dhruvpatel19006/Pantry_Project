package com.pantry.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DatabaseConfig{
    private static final String URL = "jdbc:sqlite:pantry.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void initializeDatabase(){
        try (Connection conn = getConnection()) {
            try(Statement pragma = conn.createStatement()){
                pragma.execute("PRAGMA foreign_keys = ON;");
            }
            runSchema(conn);
            System.out.println("Database initialized successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }

    public static void runSchema(Connection conn) throws Exception {
        InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("schema.sql");
        if (inputStream == null) {
            throw new RuntimeException("schema.sql not found in classpath");
        }
        String sql = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

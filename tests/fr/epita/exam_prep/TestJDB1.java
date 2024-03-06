package fr.epita.exam_prep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDB1 {
    public static void test() {
        // Step 1: Establishing a connection to the in-memory H2 database
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")) {
            // System.out.println("Connected to the in-memory H2 database.");

            // Step 2: Creating a table "PERSONS" with appropriate columns, types, and
            // constraints
            String createTableQuery = "CREATE TABLE PERSONS ("
                    + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                    + "NAME VARCHAR(50) NOT NULL,"
                    + "AGE INT NOT NULL,"
                    + "SEX VARCHAR(10),"
                    + "HEIGHT INT,"
                    + "WEIGHT DOUBLE"
                    + ")";

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTableQuery);
                // System.out.println("Table PERSONS created successfully.");
                System.out.println("TestJDB1 passed.");
            } catch (SQLException e) {
                System.out.println("Error creating table: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // test();
    }
}

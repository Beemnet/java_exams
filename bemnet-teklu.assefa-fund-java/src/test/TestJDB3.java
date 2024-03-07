package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDB3 {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) {
        // test();
    }

    public static void test() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            System.out.println("Database connection established.");
            // Load and execute the SQL scripts
            executeScript("./resources/create-insurances.sql", connection);
            executeScript("./resources/create-medications.sql", connection);
            executeScript("./resources/create-prescriptions.sql", connection);
            executeScript("./resources/create-patients.sql", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void executeScript(String scriptFilePath, Connection connection) {
        try (Statement statement = connection.createStatement()) {
            String scriptContent = readFileContent(scriptFilePath);
            statement.execute(scriptContent);
            System.out.println(scriptFilePath + " executed successfully.");
        } catch (SQLException e) {
            System.out.println("Error executing script: " + scriptFilePath);
            e.printStackTrace();
        }
    }

    private static String readFileContent(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }
}

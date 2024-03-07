package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDB1 {
    // JDBC URL for in-memory H2 database
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) {
        // test();
    }

    public static void test() {
        try {
            // Load the H2 JDBC driver
            Class.forName("org.h2.Driver");

            // Establish a connection to the in-memory database
            try (Connection connection = DriverManager.getConnection(JDBC_URL);
                    Statement statement = connection.createStatement()) {
                // Create the PATIENTS table
                String createTableSQL = "CREATE TABLE PATIENTS (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "pat_num_HC BIGINT," + // Remove NOT NULL constraint if necessary
                        "pat_lastname VARCHAR(255)," +
                        "pat_firstname VARCHAR(255)," +
                        "pat_address VARCHAR(255)," +
                        "pat_tel VARCHAR(20)," +
                        "pat_insurance_id INT," +
                        "pat_subscription_date DATE," +
                        "UNIQUE(pat_num_HC)" + // Add UNIQUE constraint if necessary
                        ")";

                statement.executeUpdate(createTableSQL);

                System.out.println("PATIENTS table created successfully.");

                // Create the INSURANCES table
                String createInsurancesTableSQL = "CREATE TABLE INSURANCES (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "insurance_id INT NOT NULL," +
                        "insurance_name VARCHAR(255)" +
                        ")";
                statement.executeUpdate(createInsurancesTableSQL);
                System.out.println("INSURANCES table created successfully.");

                // Create the MEDICATIONS table
                String createMedicationsTableSQL = "CREATE TABLE MEDICATIONS (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "medication_code INT," +
                        "medication_name VARCHAR(255)," +
                        "medication_comment VARCHAR(255)," +
                        "UNIQUE(medication_code)" + // Add UNIQUE constraint to medication_code
                        ")";

                statement.executeUpdate(createMedicationsTableSQL);
                System.out.println("MEDICATIONS table created successfully.");

                // Create the PRESCRIPTIONS table
                String createPrescriptionsTableSQL = "CREATE TABLE PRESCRIPTIONS (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "presc_id INT NOT NULL," +
                        "presc_ref_pat BIGINT," +
                        "presc_code INT," +
                        "presc_days INT" +
                        ")";
                statement.executeUpdate(createPrescriptionsTableSQL);
                System.out.println("PRESCRIPTIONS table created successfully.");

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}

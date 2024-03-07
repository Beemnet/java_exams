package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Patient;

public class PatientDAO extends AbstractDAO<Patient> {
    private Connection connection;

    // Constructor
    public PatientDAO(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    // Create a patient
    @Override
    public void create(Patient patient) throws SQLException {
        String sql = "INSERT INTO PATIENTS (pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel, pat_insurance_id, pat_subscription_date) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, patient.getPatNumHC());
            statement.setString(2, patient.getLastName());
            statement.setString(3, patient.getFirstName());
            statement.setString(4, patient.getAddress());
            statement.setString(5, patient.getTelephone());
            statement.setInt(6, patient.getInsuranceId());
            statement.setDate(7, new java.sql.Date(patient.getSubscriptionDate().getTime()));
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Patient " + patient.getLastName() + " created successfully.");
            }
        }
    }

    // Update a patient
    @Override
    public void update(Patient patient) throws SQLException {
        String sql = "UPDATE PATIENTS SET pat_lastname = ?, pat_firstname = ?, pat_address = ?, pat_tel = ?, pat_insurance_id = ?, pat_subscription_date = ? "
                +
                "WHERE pat_num_HC = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, patient.getLastName());
            statement.setString(2, patient.getFirstName());
            statement.setString(3, patient.getAddress());
            statement.setString(4, patient.getTelephone());
            statement.setInt(5, patient.getInsuranceId());
            statement.setDate(6, new java.sql.Date(patient.getSubscriptionDate().getTime()));
            statement.setLong(7, patient.getPatNumHC());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Patient " + patient.getPatNumHC() + " updated successfully");
            }
        }
    }

    // Delete a patient
    @Override
    public void delete(long patNumHC) throws SQLException {
        String sql = "DELETE FROM PATIENTS WHERE pat_num_HC = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, patNumHC);
            int rowsDel = statement.executeUpdate();
            if (rowsDel > 0) {
                System.out.println("Patient " + patNumHC + " deleted successfully.");
            }
        }
    }

    @Override
    public List<Patient> search() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM PATIENTS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                patients.add(mapRowToEntity(resultSet));
            }
        }
        return patients;
    }

    // Search patients using search criteria (first name and last name)
    public List<Patient> search(String firstName, String lastName) throws SQLException {
        String sql = "SELECT * FROM PATIENTS WHERE pat_firstname = ? AND pat_lastname = ?";
        List<Patient> patients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Patient patient = new Patient(
                            resultSet.getLong("pat_num_HC"),
                            resultSet.getString("pat_lastname"),
                            resultSet.getString("pat_firstname"),
                            resultSet.getString("pat_address"),
                            resultSet.getString("pat_tel"),
                            resultSet.getInt("pat_insurance_id"),
                            resultSet.getDate("pat_subscription_date"));
                    patients.add(patient);
                }
            }
        }
        return patients;
    }

    @Override
    protected Patient mapRowToEntity(ResultSet resultSet) throws SQLException {
        // long id = resultSet.getLong("id");
        long patNumHC = resultSet.getLong("pat_num_HC");
        String lastName = resultSet.getString("pat_lastname");
        String firstName = resultSet.getString("pat_firstname");
        String address = resultSet.getString("pat_address");
        String tel = resultSet.getString("pat_tel");
        int insuranceId = resultSet.getInt("pat_insurance_id");
        LocalDate subscriptionDate = resultSet.getDate("pat_subscription_date").toLocalDate();

        return new Patient(patNumHC, lastName, firstName, address, tel, insuranceId, subscriptionDate);
    }
}
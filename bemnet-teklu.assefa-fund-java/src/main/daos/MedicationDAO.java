package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Medication;

public class MedicationDAO extends AbstractDAO<Medication> {
    public MedicationDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Medication> search() throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT * FROM MEDICATIONS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                medications.add(mapRowToEntity(resultSet));
            }
        }
        return medications;
    }

    // Add method to search medication by code
    public Medication searchByCode(int code) throws SQLException {
        String sql = "SELECT * FROM MEDICATIONS WHERE medication_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, code);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToEntity(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void update(Medication medication) throws SQLException {
        String sql = "UPDATE MEDICATIONS SET medication_name = ?, medication_comment = ? WHERE medication_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medication.getMedicationName());
            statement.setString(2, medication.getMedicationComment());
            statement.setInt(3, medication.getMedicationCode());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Medication " + medication.getMedicationCode() + " updated successfully.");
            } else {
                System.out.println("Medications update failed");
            }
        }
    }

    @Override
    public void delete(long code) throws SQLException {
        String sql = "DELETE FROM MEDICATIONS WHERE medication_code = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, code);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Medication " + code + " deleted successfully.");
            } else {
                System.out.println("Medications deletion failed");
            }
        }
    }

    @Override
    public void create(Medication medication) throws SQLException {
        String sql = "INSERT INTO MEDICATIONS (medication_code, medication_name, medication_comment) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medication.getMedicationCode());
            statement.setString(2, medication.getMedicationName());
            statement.setString(3, medication.getMedicationComment());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Medication " + medication.getMedicationCode() + " created successfully.");
            } else {
                System.out.println("Medications creation failed");
            }
        }
    }

    @Override
    protected Medication mapRowToEntity(ResultSet resultSet) throws SQLException {
        int code = resultSet.getInt("medication_code");
        String name = resultSet.getString("medication_name");
        String comment = resultSet.getString("medication_comment");

        return new Medication(code, name, comment);
    }

}

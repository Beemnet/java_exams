package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Prescription;

public class PrescriptionDAO extends AbstractDAO<Prescription> {
    public PrescriptionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Prescription> search() throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM PRESCRIPTIONS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                prescriptions.add(mapRowToEntity(resultSet));
            }
        }
        return prescriptions;
    }

    // Add method to search prescriptions by patient reference
    public List<Prescription> searchByRefPat(long refPat) throws SQLException {
        List<Prescription> prescriptions = new ArrayList<>();
        String sql = "SELECT * FROM PRESCRIPTIONS WHERE presc_ref_pat = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, refPat);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    prescriptions.add(mapRowToEntity(resultSet));
                }
            }
        }
        return prescriptions;
    }

    @Override
    public void update(Prescription prescription) throws SQLException {
        String sql = "UPDATE PRESCRIPTIONS SET presc_ref_pat = ?, presc_code = ?, presc_days = ? WHERE presc_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, prescription.getPatientReference());
            statement.setInt(2, prescription.getMedicationCode());
            statement.setInt(3, prescription.getPrescriptionDays());
            statement.setLong(4, prescription.getPrescriptionId());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Prescription " + prescription.getPrescriptionId() + " updated sucessfully.");
            }
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM PRESCRIPTIONS WHERE presc_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Prescription " + id + " deleted successfully.");
            }
        }
    }

    @Override
    public void create(Prescription prescription) throws SQLException {
        String sql = "INSERT INTO PRESCRIPTIONS (PRESC_ID, presc_ref_pat, presc_code, presc_days) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, prescription.getPrescriptionId());
            statement.setLong(2, prescription.getPatientReference());
            statement.setInt(3, prescription.getMedicationCode());
            statement.setInt(4, prescription.getPrescriptionDays());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Prescription " + prescription.getPrescriptionId() + " with patient "
                        + prescription.getPatientReference() + " and medication " + prescription.getMedicationCode()
                        + " created successfully.");
            }
        }
    }

    @Override
    protected Prescription mapRowToEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("presc_id");
        long refPat = resultSet.getLong("presc_ref_pat");
        int code = resultSet.getInt("presc_code");
        int days = resultSet.getInt("presc_days");

        return new Prescription(id, refPat, code, days);
    }
}

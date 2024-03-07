package main.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Insurance;

public class InsuranceDAO extends AbstractDAO<Insurance> {
    public InsuranceDAO(Connection connection) {
        super(connection);
    }

    @Override
    public List<Insurance> search() throws SQLException {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM INSURANCES";
        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                insurances.add(mapRowToEntity(resultSet));
            }
        }
        return insurances;
    }

    @Override
    public void update(Insurance insurance) throws SQLException {
        String sql = "UPDATE INSURANCES SET insurance_name = ? WHERE insurance_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, insurance.getInsuranceName());
            statement.setInt(2, insurance.getInsuranceId());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Insurance updated successfully to: " + insurance.getInsuranceName());
            }
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM INSURANCES WHERE insurance_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Insurance " + id + " deleted Successfully");
            }
        }
    }

    @Override
    public void create(Insurance insurance) throws SQLException {
        String sql = "INSERT INTO INSURANCES (insurance_id, insurance_name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, insurance.getInsuranceId());
            statement.setString(2, insurance.getInsuranceName());
            int row = statement.executeUpdate();
            if (row > 0) {
                System.out.println("Insurance " + insurance.getInsuranceId() + " created Successfully");
            }
        }
    }

    @Override
    protected Insurance mapRowToEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("insurance_id");
        String name = resultSet.getString("insurance_name");

        return new Insurance(id, name);
    }

    public List<Insurance> search(String insuranceName) throws SQLException {
        List<Insurance> insurances = new ArrayList<>();
        String sql = "SELECT * FROM INSURANCES WHERE insurance_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, insuranceName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    insurances.add(mapRowToEntity(resultSet));
                }
            }
        }
        return insurances;
    }

}

package fr.epita.exam_prep.daos;

import java.sql.*;

import fr.epita.exam_prep.datamodels.Person;

public class PersonJDBCDAO {
    private static final String INSERT_QUERY = "INSERT INTO PERSONS (NAME, AGE, SEX, HEIGHT, WEIGHT) VALUES (?, ?, ?, ?, ?)";
    private static final String SEARCH_QUERY = "SELECT * FROM PERSONS";

    public void create(String name, int age, String sex, int height, double weight) {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
                PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, sex);
            statement.setInt(4, height);
            statement.setDouble(5, weight);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error executing create query: " + e.getMessage());
        }
    }

    public void search() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SEARCH_QUERY)) {

            System.out.println("Result list:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                String sex = resultSet.getString("SEX");
                int height = resultSet.getInt("HEIGHT");
                int weight = resultSet.getInt("WEIGHT");

                Person person = new Person(name, age, sex, height, weight);
                System.out.println("ID: " + id + " is " + person.toString());

            }

        } catch (SQLException e) {
            System.out.println("Error executing search query: " + e.getMessage());
        }
    }
}
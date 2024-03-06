package fr.epita.exam_prep;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.exam_prep.daos.PersonJDBCDAO;
import fr.epita.exam_prep.datamodels.Person;

public class TestJDB2 {
    public static void test() {
        PersonJDBCDAO dao = new PersonJDBCDAO();
        String csvFilePath = "data/data.csv";

        // Read the persons list from the CSV file
        List<Person> persons = readPersonsFromCSV(csvFilePath);

        // Insert each person into the database
        for (Person person : persons) {
            dao.create(person.getName(), person.getAge(), person.getSex(), person.getHeight(), person.getWeight());
        }

        // Validate the inserted data by searching and displaying the result list
        dao.search();
        System.out.println("TestJDB2 passed.");
    }

    private static List<Person> readPersonsFromCSV(String csvFilePath) {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true; // Flag to track the first line (header line)
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Skip the first line (header line)
                }

                String[] values = line.split(",");

                String name = values[0].trim();
                int age = Integer.parseInt(values[2].trim());
                String sex = values[1].trim().replaceAll("\"", ""); // Remove any quotes around the value
                int height = Integer.parseInt(values[3].trim());
                int weight = Integer.parseInt(values[4].trim());
                // Print values for debugging
                // System.out.println("Name: " + name);
                // System.out.println("Age: " + age);
                // System.out.println("Sex: " + sex);
                // System.out.println("Height: " + height);
                // System.out.println("Weight: " + weight);

                persons.add(new Person(name, age, sex, height, weight));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing values from CSV: " + e.getMessage());
        }

        return persons;
    }

    public static void main(String[] args) {
        // TestJDB1.test(); // to create the table
        // test();
    }
}
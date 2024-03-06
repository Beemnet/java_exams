package fr.epita.last_exam.test;

import fr.epita.last_exam.services.CSVReader;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestA {
    private static final Logger LOGGER = Logger.getLogger(TestA.class.getName());

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        String csvFile = "./data/mnist_train.csv";

        // Test readCSVFile
        LOGGER.log(Level.INFO, "Testing readCSVFile function");
        try {
            csvReader.readCSVFile(csvFile).forEach(row -> {
                String[] subValues = Arrays.copyOfRange(row, 0, 15);
                LOGGER.log(Level.INFO, Arrays.toString(subValues));
            });
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred while reading the CSV file", e);
        }
    }
}
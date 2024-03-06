package fr.epita.last_exam.test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.last_exam.services.CSVReader;

public class TestB {
    private static final Logger logger = Logger.getLogger(TestB.class.getName());

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        String csvFile = "./data/mnist_train.csv"; // Replace with the actual path to your CSV file

        List<String[]> rows = csvReader.readCSVFile(csvFile);

        // Storing the header separately
        String[] header = rows.get(0);
        rows.remove(0); // Remove the header from the data rows

        // Extracting values from the data rows
        double[] values = Arrays.stream(rows.get(0))
                .mapToDouble(Double::parseDouble)
                .toArray();

        double[][] matrix = CSVReader.reshapeData(values);
        CSVReader.showMatrix(matrix);

        // Print the first column of the matrix
        for (int i = 0; i < 28; i++) {
            // System.out.println(matrix[i][0]);
        }

        // Logging example
        logger.log(Level.INFO, "CSV file processed successfully.");
    }
}

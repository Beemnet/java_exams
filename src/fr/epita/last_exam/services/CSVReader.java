package fr.epita.last_exam.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
    public List<String[]> readCSVFile(String csvFile) {
        List<String[]> data = new ArrayList<>();
        BufferedReader reader = null;
        String[] headers = null;
        try {
            reader = new BufferedReader(new FileReader(csvFile));
            String line;

            int lineCount = 0;

            while ((line = reader.readLine()) != null && lineCount < 2) {
                String[] values = line.split(",");

                if (lineCount == 0) {
                    headers = values;
                } else {
                    data.add(values);
                }

                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        data.add(0, headers); // Add headers at the beginning of the data list
        return data;
    }

    public double[] extractValues(String csvFile) {
        List<String[]> rows = readCSVFile(csvFile);

        // Assuming the values are in the first row of the CSV file
        String[] firstRow = rows.get(0);
        double[] values = new double[firstRow.length];

        for (int i = 0; i < firstRow.length; i++) {
            values[i] = Double.parseDouble(firstRow[i]);
        }

        return values;
    }

    public static double[][] reshapeData(double[] values) {
        double[][] matrix = new double[28][28];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int index = i * 28 + j;
                matrix[i][j] = values[index];
            }
        }
        return matrix;
    }

    public static void showMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > 100) {
                    System.out.print("xx ");
                } else {
                    System.out.print(".. ");
                }
            }
            System.out.println(); // Move to the next line after each row
        }

    }

    public static void main(String[] args) {
        String csvFile = "./data/mnist_train.csv";
        CSVReader csvReader = new CSVReader();

        List<String[]> data = csvReader.readCSVFile(csvFile);

        // Print the headers
        System.out.println(Arrays.toString(data.get(0)));

        // Print the first 15 items in the data list
        for (int i = 1; i < 16; i++) {
            String[] row = data.get(i);
            String[] subValues = Arrays.copyOfRange(row, 0, 15);
            // System.out.println(Arrays.toString(subValues));
        }
    }

}

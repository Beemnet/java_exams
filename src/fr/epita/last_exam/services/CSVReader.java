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
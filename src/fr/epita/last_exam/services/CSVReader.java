package fr.epita.last_exam.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = "./data/mnist_train.csv";
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(csvFile));
            String line;
            String[] headers = null;
            int lineCount = 0;

            while ((line = reader.readLine()) != null && lineCount < 2) {
                // System.out.println(line.substring(0, 50));
                String[] data = line.split(",");

                if (lineCount == 0) {
                    headers = data;
                    String[] subHeaders = Arrays.copyOfRange(headers, 0, 15);
                    System.out.println(Arrays.toString(subHeaders));
                } else {
                    double[] values = new double[data.length];
                    for (int i = 0; i < data.length; i++) {
                        values[i] = Double.parseDouble(data[i]);
                    }
                    double[] subValues = Arrays.copyOfRange(values, 0, 15);
                    System.out.println(Arrays.toString(subValues));
                }
                // System.out.println(Arrays.toString(data));
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
    }
}
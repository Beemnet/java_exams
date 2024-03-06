package fr.epita.last_exam.services;

import fr.epita.last_exam.datamodels.Image;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CentroidService {
    public Map<Integer, double[][]> trainCentroids(List<Image> images) {
        Map<Integer, double[][]> centroids = new HashMap<>();
        Map<Integer, Integer> digitCount = new HashMap<>();
        Map<Integer, double[][]> digitSum = new HashMap<>();

        for (Image image : images) {
            int label = image.getLabel();
            double[][] dataMatrix = image.getDataMatrix();

            if (!digitCount.containsKey(label)) {
                digitCount.put(label, 0);
                digitSum.put(label, new double[28][28]);
            }

            int count = digitCount.get(label);
            double[][] sum = digitSum.get(label);

            for (int i = 0; i < 28; i++) {
                for (int j = 0; j < 28; j++) {
                    sum[i][j] += dataMatrix[i][j];
                }
            }

            digitCount.put(label, count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : digitCount.entrySet()) {
            int label = entry.getKey();
            int count = entry.getValue();
            double[][] sum = digitSum.get(label);

            double[][] centroid = new double[28][28];
            for (int i = 0; i < 28; i++) {
                for (int j = 0; j < 28; j++) {
                    centroid[i][j] = sum[i][j] / count;
                }
            }

            centroids.put(label, centroid);
        }

        return centroids;
    }

    public void testCentroids() {
        List<Image> testData = readTestDataFromCSV("mnist_test.csv");

        Map<Integer, double[][]> centroids = trainCentroids(testData);

        // Print the centroids
        for (Map.Entry<Integer, double[][]> entry : centroids.entrySet()) {
            int label = entry.getKey();
            double[][] centroidDataMatrix = entry.getValue();
            System.out.println("Centroid for label " + label + ":");
            printDataMatrix(centroidDataMatrix);
            System.out.println();
        }
    }

    private List<Image> readTestDataFromCSV(String csvFilePath) {
        List<Image> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./data/mnist_test.csv"))) {
            String line;
            // Skip the first line (headers/labels)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] csvValues = line.split(",");
                // Parsing the values from the CSV file and creating Image objects
                int label = Integer.parseInt(csvValues[0]);
                double[][] dataMatrix = new double[28][28];
                for (int i = 1; i <= 28; i++) {
                    for (int j = 1; j <= 28; j++) {
                        dataMatrix[i - 1][j - 1] = Double.parseDouble(csvValues[(i - 1) * 28 + j]);
                    }
                }
                Image image = new Image(label, dataMatrix);
                data.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void printDataMatrix(double[][] dataMatrix) {
        for (int i = 0; i < dataMatrix.length; i++) {
            for (int j = 0; j < dataMatrix[i].length; j++) {
                System.out.print(dataMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
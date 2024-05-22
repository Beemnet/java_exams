package fr.epita.last_exam.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import fr.epita.last_exam.datamodels.Image;

public class ImageClassifierService {

    private static final Logger logger = Logger.getLogger(ImageClassifierService.class.getName());

    public List<Image> loadMNISTTestData(String filePath) throws CsvValidationException, NumberFormatException {
        List<Image> images = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // Assuming each line represents an image, create an Image instance
                int label = Integer.parseInt(nextLine[0]);
                double[][] dataMatrix = new double[nextLine.length - 1][];
                for (int i = 1; i < nextLine.length; i++) {
                    String[] matrixRow = nextLine[i].split(",");
                    double[] row = new double[matrixRow.length];
                    for (int j = 0; j < matrixRow.length; j++) {
                        row[j] = Double.parseDouble(matrixRow[j]);
                    }
                    dataMatrix[i - 1] = row;
                }
                Image image = new Image(label, dataMatrix);
                images.add(image);
            }
        } catch (IOException e) {
            logger.severe("Error loading MNIST test data: " + e.getMessage());
        }

        return images;
    }

    public List<Image> isolateFirstTenZeroes(List<Image> images) {
        List<Image> firstTenZeroes = new ArrayList<>();
        int zeroCount = 0;

        for (Image image : images) {
            if (image.getLabel() == 0 && zeroCount < 10) {
                firstTenZeroes.add(image);
                zeroCount++;
            }
        }

        return firstTenZeroes;
    }

    public double calculateDistance(Image instance, List<Image> centroids) {
        double minDistance = Double.MAX_VALUE;

        for (Image centroid : centroids) {
            double distance = 0.0;
            for (int i = 0; i < instance.getDataMatrix().length; i++) {
                for (int j = 0; j < instance.getDataMatrix()[i].length; j++) {
                    double diff = instance.getDataMatrix()[i][j] - centroid.getDataMatrix()[i][j];
                    distance += Math.pow(diff, 2);
                }
            }
            distance = Math.sqrt(distance);
            if (distance < minDistance) {
                minDistance = distance;
            }
        }

        return minDistance;
    }

    // Dummy prediction algorithm
    public int predictLabel(Image testImage, List<Image> centroids) {
        double minDistance = Double.MAX_VALUE;
        int predictedLabel = -1; // Default value if no centroid is found
        for (int i = 0; i < centroids.size(); i++) {
            // double distance = calculateDistance(testImage, centroids.get(i));
            // if (distance < minDistance) {
            // minDistance = distance;
            // predictedLabel = centroids.get(i).getLabel(); // Update predicted label
            // }
        }
        return predictedLabel;
    }
}
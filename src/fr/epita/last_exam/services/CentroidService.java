package fr.epita.last_exam.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.epita.last_exam.datamodels.Image;

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
}
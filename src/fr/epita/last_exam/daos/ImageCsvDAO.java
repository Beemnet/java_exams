package fr.epita.last_exam.daos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.last_exam.datamodels.Image;

public class ImageCsvDAO {
    private String csvFile;

    public ImageCsvDAO(String csvFile) {
        this.csvFile = csvFile;
    }

    public List<Image> getAllImages() {
        List<Image> images = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int label = Integer.parseInt(values[0]);
                double[] data = new double[values.length - 1];

                for (int i = 1; i < values.length; i++) {
                    data[i - 1] = Double.parseDouble(values[i]);
                }

                double[][] dataMatrix = reshapeData(data);
                images.add(new Image(label, dataMatrix));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return images;
    }

    private double[][] reshapeData(double[] values) {
        double[][] matrix = new double[28][28];
        for (int i = 0; i < 28; i++) {
            for (int j = 0; j < 28; j++) {
                int index = i * 28 + j;
                matrix[i][j] = values[index];
            }
        }
        return matrix;
    }
}

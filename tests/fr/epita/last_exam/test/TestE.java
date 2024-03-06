package fr.epita.last_exam.test;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.last_exam.daos.ImageCsvDAO;
import fr.epita.last_exam.datamodels.Image;
import fr.epita.last_exam.services.CSVReader;
import fr.epita.last_exam.services.CentroidService;

public class TestE {
    private static final Logger logger = Logger.getLogger(TestE.class.getName());

    public static void main(String[] args) {
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO("./data/mnist_train.csv");
        List<Image> images = imageCsvDAO.getAllImages();

        CentroidService centroidService = new CentroidService();
        Map<Integer, double[][]> centroids = centroidService.trainCentroids(images);

        for (Map.Entry<Integer, double[][]> entry : centroids.entrySet()) {
            logger.log(Level.INFO, "Digit " + entry.getKey() + ": Centroid Matrix");
            CSVReader.showMatrix(entry.getValue());
        }
    }
}
package fr.epita.last_exam.test;

import java.util.List;

import fr.epita.last_exam.daos.ImageCsvDAO;
import fr.epita.last_exam.datamodels.Image;
import fr.epita.last_exam.services.CSVReader;

// import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TestC {
    private static final Logger logger = Logger.getLogger(TestC.class.getName());

    public static void main(String[] args) {
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO("./data/mnist_train.csv");

        List<Image> images = imageCsvDAO.getAllImages();

        for (Image image : images) {
            logger.log(Level.INFO, "Label: " + image.getLabel());
            logger.log(Level.INFO, "Data Matrix:");
            CSVReader.showMatrix(image.getDataMatrix());
        }
    }
}

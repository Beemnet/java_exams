package fr.epita.last_exam.test;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.epita.last_exam.daos.ImageCsvDAO;
import fr.epita.last_exam.datamodels.Image;
import fr.epita.last_exam.services.DigitDistributionService;

public class TestD {
    private static final Logger logger = Logger.getLogger(TestD.class.getName());

    public static void main(String[] args) {
        ImageCsvDAO imageCsvDAO = new ImageCsvDAO("./data/mnist_train.csv");
        List<Image> images = imageCsvDAO.getAllImages();

        DigitDistributionService digitDistributionService = new DigitDistributionService();
        Map<Integer, Integer> digitDistribution = digitDistributionService.calculateDistribution(images);

        for (Map.Entry<Integer, Integer> entry : digitDistribution.entrySet()) {
            logger.log(Level.INFO, "Digit " + entry.getKey() + ": Count = " + entry.getValue());
        }
    }
}

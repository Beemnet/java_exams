package fr.epita.last_exam.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.epita.last_exam.datamodels.Image;

public class DigitDistributionService {
    public Map<Integer, Integer> calculateDistribution(List<Image> images) {
        Map<Integer, Integer> digitDistribution = new HashMap<>();

        for (Image image : images) {
            int label = image.getLabel();
            digitDistribution.put(label, digitDistribution.getOrDefault(label, 0) + 1);
        }

        return digitDistribution;
    }
}

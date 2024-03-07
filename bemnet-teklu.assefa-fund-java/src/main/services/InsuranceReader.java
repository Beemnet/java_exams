package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Insurance;

public class InsuranceReader {
    public List<Insurance> readAll(String filePath) throws IOException {
        List<Insurance> insurances = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the first line
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            int insuranceId = Integer.parseInt(parts[0]);
            String insuranceName = parts[1];
            Insurance insurance = new Insurance(insuranceId, insuranceName);
            insurances.add(insurance);
        }
        reader.close();
        return insurances;
    }
}

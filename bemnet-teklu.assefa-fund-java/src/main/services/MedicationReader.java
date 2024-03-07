package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Medication;

public class MedicationReader {
    public List<Medication> readAll(String filePath) throws IOException {
        List<Medication> medications = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the first line (header)
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            int medicationCode = Integer.parseInt(parts[0]);
            String medicationName = parts[1];
            String medicationComment = parts[2];
            Medication medication = new Medication(medicationCode, medicationName, medicationComment);
            medications.add(medication);
        }
        reader.close();
        return medications;
    }
}

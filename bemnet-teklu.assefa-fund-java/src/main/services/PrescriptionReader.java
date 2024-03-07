package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.datamodels.Prescription;

public class PrescriptionReader {
    public List<Prescription> readAll(String filePath) throws IOException {
        List<Prescription> prescriptions = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the first line (header)
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            int prescriptionId = Integer.parseInt(parts[0]);
            long patientReference = Long.parseLong(parts[1]);
            int medicationCode = Integer.parseInt(parts[2]);
            int prescriptionDays = Integer.parseInt(parts[3]);
            Prescription prescription = new Prescription(prescriptionId, patientReference, medicationCode,
                    prescriptionDays);
            prescriptions.add(prescription);
        }
        reader.close();
        return prescriptions;
    }
}
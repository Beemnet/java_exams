package main.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.datamodels.Patient;

public class PatientReader {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public List<Patient> readAll(String filePath) throws IOException, ParseException {
        List<Patient> patients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the first line
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            long patNumHC = Long.parseLong(parts[0].replaceAll("\"", ""));
            String lastName = parts[1];
            String firstName = parts[2];
            String address = parts[3];
            String telephone = parts[4];
            int insuranceId = Integer.parseInt(parts[5]);
            Date subscriptionDate = DATE_FORMAT.parse(parts[6]);
            Patient patient = new Patient(patNumHC, lastName, firstName, address, telephone, insuranceId,
                    subscriptionDate);
            patients.add(patient);
        }
        reader.close();
        return patients;
    }
}

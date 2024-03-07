package test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import main.datamodels.Patient;
import main.services.PatientBLService;
import main.services.PatientReader;

public class TestBLI2 {
    public static void main(String[] args) {

    }

    public static void test() {
        try {
            // Load a list of patients using PatientsReader
            PatientReader patientReader = new PatientReader();
            List<Patient> patients = patientReader.readAll("./resources/patients.csv");

            // Compute seniority by patient
            Map<Long, Integer> seniorityByPatient = PatientBLService.computeSeniorityByPatient(patients);

            // Display the data structure in the console
            System.out.println("Patient Seniority:");
            for (Map.Entry<Long, Integer> entry : seniorityByPatient.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

package test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import main.datamodels.Patient;
import main.services.PatientBLService;
import main.services.PatientReader;

public class TestBLI1 {

    public static void main(String[] args) {
    }

    public static void test() {
        try {
            PatientReader patientReader = new PatientReader();
            List<Patient> patients = patientReader.readAll("./resources/patients.csv");

            // Assuming there are at least 6 patients in the list
            if (patients.size() >= 6) {
                Patient patient = patients.get(5); // Getting the 6th patient entry
                Integer seniority = PatientBLService.computeSeniority(patient);
                System.out.println("Seniority of patient " + patient.getPatNumHC() + ": " + seniority + " years");
            } else {
                System.out.println("There are not enough patients in the list.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}

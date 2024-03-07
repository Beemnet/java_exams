package test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import main.datamodels.Insurance;
import main.datamodels.Patient;
import main.services.InsuranceReader;
import main.services.PatientReader;

public class TestOOP2 {
    public static void main(String[] args) {

    }

    public static void test() {
        try {
            PatientReader patientReader = new PatientReader();
            List<Patient> patients = patientReader.readAll("./resources/patients.csv");
            System.out.println("Patients:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }

            InsuranceReader insuranceReader = new InsuranceReader();
            List<Insurance> insurances = insuranceReader.readAll("./resources/insurances.csv");
            System.out.println("\nInsurances:");
            for (Insurance insurance : insurances) {
                System.out.println(insurance);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}

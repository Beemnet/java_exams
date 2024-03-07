package test;

import java.sql.*;
import java.util.*;

import main.daos.MedicationDAO;
import main.daos.PatientDAO;
import main.daos.PrescriptionDAO;
import main.datamodels.Medication;
import main.datamodels.Patient;
import main.datamodels.Prescription;

public class TestJDB4 {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) {
        // TestJDB2.test();
        // test();

    }

    public static void test() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            PatientDAO patientDAO = new PatientDAO(connection);
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO(connection);
            MedicationDAO medicationDAO = new MedicationDAO(connection);

            // Retrieve all patients
            List<Patient> patients = patientDAO.search();

            for (Patient patient : patients) {
                System.out.println("Patient: " + patient.getFirstName() + " " + patient.getLastName() + " with ID "
                        + patient.getPatNumHC());

                // Retrieve prescriptions associated with the patient
                List<Prescription> prescriptions = prescriptionDAO.search();
                // System.out.println(prescriptions.size());

                for (Prescription prescription : prescriptions) {
                    if (prescription.getPatientReference() == patient.getPatNumHC()) {
                        // System.out.println(prescription.getPatientReference());
                        Medication medication = medicationDAO.searchByCode(prescription.getMedicationCode());
                        if (medication != null) {
                            System.out
                                    .println("Prescription: " + medication.getMedicationName() + ", Days: "
                                            + prescription.getPrescriptionDays());
                        } else {
                            System.out.println("Patient has no Prescription");
                        }
                    }
                    // Retrieve medication details for each prescription

                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

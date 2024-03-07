package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import main.daos.InsuranceDAO;
import main.daos.MedicationDAO;
import main.daos.PatientDAO;
import main.daos.PrescriptionDAO;
import main.datamodels.Insurance;
import main.datamodels.Medication;
import main.datamodels.Patient;
import main.datamodels.Prescription;
import main.services.InsuranceReader;
import main.services.MedicationReader;
import main.services.PatientReader;
import main.services.PrescriptionReader;

public class TestJDB2 {
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static void main(String[] args) {
        // test();
    }

    public static void test() {
        try {
            // TestJDB1.test();
            PatientReader patientReader = new PatientReader();
            InsuranceReader insuranceReader = new InsuranceReader();
            PrescriptionReader prescriptionReader = new PrescriptionReader();
            MedicationReader medicationReader = new MedicationReader();
            // Load data from CSV files
            List<Patient> patients = patientReader.readAll("./resources/patients.csv");
            List<Insurance> insurances = insuranceReader.readAll("./resources/insurances.csv");
            List<Prescription> prescriptions = prescriptionReader.readAll("./resources/prescriptions.csv");
            List<Medication> medications = medicationReader.readAll("./resources/medications.csv");

            // Establish connection to H2 database
            try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
                // Handle data for each entity
                handlePatients(connection, patients);
                handleInsurances(connection, insurances);
                handleMedications(connection, medications);
                handlePrescriptions(connection, prescriptions);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methods to read data from CSV files (similar to previous implementation)

    // Methods to handle data for each entity
    private static void handlePatients(Connection connection, List<Patient> patients) throws SQLException {
        PatientDAO patientDAO = new PatientDAO(connection);
        for (Patient patient : patients) {
            patientDAO.create(patient);
        }
        // Implement search, update, delete operations for patients
        // Search for a patient
        String firstName = "Pauline";
        String lastName = "Dart";
        List<Patient> foundPatients = patientDAO.search(firstName, lastName);
        if (!foundPatients.isEmpty()) {
            Patient patientToUpdate = foundPatients.get(0);

            // Update the patient
            patientToUpdate.setLastName("Smith");
            patientDAO.update(patientToUpdate);

            // Delete the patient
            patientDAO.delete(patientToUpdate.getPatNumHC());
        } else {
            System.out.println("Patient not found.");
        }
    }

    private static void handleInsurances(Connection connection, List<Insurance> insurances) throws SQLException {
        InsuranceDAO insuranceDAO = new InsuranceDAO(connection);
        for (Insurance insurance : insurances) {
            insuranceDAO.create(insurance);
        }
        // Implement search, update, delete operations for insurances
        // Search for an insurance
        String insuranceName = "MACIF";
        List<Insurance> foundInsurances = insuranceDAO.search(insuranceName);
        if (!foundInsurances.isEmpty()) {
            Insurance insuranceToUpdate = foundInsurances.get(0);

            // Update the insurance
            insuranceToUpdate.setInsuranceName("Updated Insurance Name");
            insuranceDAO.update(insuranceToUpdate);

            // Delete the insurance
            insuranceDAO.delete(insuranceToUpdate.getInsuranceId());
        } else {
            System.out.println("Insurance not found.");
        }
    }

    private static void handlePrescriptions(Connection connection, List<Prescription> prescriptions)
            throws SQLException {
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO(connection);
        for (Prescription prescription : prescriptions) {
            prescriptionDAO.create(prescription);
        }
        // Implement search, update, delete operations for prescriptions
        // Search for a prescription
        long patientId = 2758965423102L;
        List<Prescription> foundPrescriptions = prescriptionDAO.searchByRefPat(patientId);
        if (!foundPrescriptions.isEmpty()) {
            Prescription prescriptionToUpdate = foundPrescriptions.get(0);

            // Update the prescription
            prescriptionToUpdate.setPrescriptionDays(5);
            prescriptionDAO.update(prescriptionToUpdate);

            // Delete the prescription
            prescriptionDAO.delete(prescriptionToUpdate.getPrescriptionId());
        } else {
            System.out.println("Prescription not found.");
        }

    }

    private static void handleMedications(Connection connection, List<Medication> medications) throws SQLException {
        MedicationDAO medicationDAO = new MedicationDAO(connection);
        for (Medication medication : medications) {
            medicationDAO.create(medication);
        }
        // Implement search, update, delete operations for medications
        // Search for a medication
        int medicationCode = 1;
        Medication foundMedication = medicationDAO.searchByCode(medicationCode);
        Medication medicationToUpdate = foundMedication;

        // Update the medication
        medicationToUpdate.setMedicationName("New Med");
        medicationDAO.update(medicationToUpdate);

        // Delete the medication (assuming delete method is implemented)
        medicationDAO.delete(medicationToUpdate.getMedicationCode());
        // if (!foundMedication) {
        // Medication medicationToUpdate = foundMedications.get(0);

        // // Update the medication (assuming update method is implemented)
        // medicationToUpdate("New Medication Name");
        // medicationDAO.update(medicationToUpdate);

        // // Delete the medication (assuming delete method is implemented)
        // medicationDAO.delete(medicationToUpdate.getId());
        // } else {
        // System.out.println("Medication not found.");
        // }

    }
}

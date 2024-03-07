package main.datamodels;

public class Prescription {
    private int prescriptionId;
    private long patientReference;
    private int medicationCode;
    private int prescriptionDays;

    // Constructor
    public Prescription(int prescriptionId, long patientReference, int medicationCode, int prescriptionDays) {
        this.prescriptionId = prescriptionId;
        this.patientReference = patientReference;
        this.medicationCode = medicationCode;
        this.prescriptionDays = prescriptionDays;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public long getPatientReference() {
        return patientReference;
    }

    public int getMedicationCode() {
        return medicationCode;
    }

    public int getPrescriptionDays() {
        return prescriptionDays;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setPatientReference(long patientReference) {
        this.patientReference = patientReference;
    }

    public void setMedicationCode(int medicationCode) {
        this.medicationCode = medicationCode;
    }

    public void setPrescriptionDays(int prescriptionDays) {
        this.prescriptionDays = prescriptionDays;
    }
}

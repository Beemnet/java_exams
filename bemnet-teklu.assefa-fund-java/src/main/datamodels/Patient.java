package main.datamodels;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    private long id;
    private long patNumHC;
    private String lastName;
    private String firstName;
    private String address;
    private String telephone;
    private int insuranceId;
    private Date subscriptionDate;
    private LocalDate subLocalDate;

    // Constructor
    public Patient(long patNumHC, String lastName, String firstName, String address, String telephone,
            int insuranceId, Date subscriptionDate) {
        this.patNumHC = patNumHC;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
        this.insuranceId = insuranceId;
        this.subscriptionDate = subscriptionDate;
    }

    // Overloaded constructor for mapRowToEntity
    public Patient(long id, long patNumHC, String lastName, String firstName, String address, String telephone,
            int insuranceId, LocalDate subscriptionDate) {
        this.id = id;
        this.patNumHC = patNumHC;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
        this.insuranceId = insuranceId;
        this.subLocalDate = subscriptionDate;
    }

    // Overloaded constructor for mapRowToEntity
    public Patient(long patNumHC, String lastName, String firstName, String address, String telephone,
            int insuranceId, LocalDate subscriptionDate) {
        this.patNumHC = patNumHC;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.telephone = telephone;
        this.insuranceId = insuranceId;
        this.subLocalDate = subscriptionDate;
    }

    public long getPatNumHC() {
        return patNumHC;
    }

    public void setPatNumHC(long patNumHC) {
        this.patNumHC = patNumHC;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Override
    public String toString() {
        return "Patient [patNumHC=" + patNumHC + ", lastName=" + lastName + ", firstName=" + firstName + ", address="
                + address + ", telephone=" + telephone + ", insuranceId=" + insuranceId + ", subscriptionDate="
                + subscriptionDate + "]";
    }

    public long getId() {
        return id;
    }

    public LocalDate getSubLocalDate() {
        return subLocalDate;
    }

}

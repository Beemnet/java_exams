package main.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.datamodels.Patient;

public class PatientBLService {

    public static Integer computeSeniority(Patient patient) {
        Date subscriptionDate = patient.getSubscriptionDate();
        LocalDate currentDate = LocalDate.now();
        LocalDate subscriptionLocalDate = subscriptionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(subscriptionLocalDate, currentDate);
        return period.getYears();
    }

    public static Map<Long, Integer> computeSeniorityByPatient(List<Patient> patients) {
        Map<Long, Integer> seniorityByPatient = new HashMap<>();

        for (Patient patient : patients) {
            Date subscriptionDate = patient.getSubscriptionDate();
            LocalDate localSubscriptionDate = subscriptionDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(localSubscriptionDate, currentDate);
            int seniority = period.getYears();
            seniorityByPatient.put(patient.getPatNumHC(), seniority);
        }

        return seniorityByPatient;
    }
}

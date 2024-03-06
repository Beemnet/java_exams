package fr.epita.exam_prep.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.epita.exam_prep.datamodels.Person;

public class PersonDataService {
    public int averageAge(List<Person> persons) {
        int sum = 0;
        for (Person person : persons) {
            sum += person.getAge();
        }

        return sum / persons.size();
    }

    public List<Person> filter(List<Person> persons, int thresholdAge) {
        List<Person> filteredPersons = new ArrayList<>();
        for (Person person : persons) {
            if (person.getAge() > thresholdAge) {
                filteredPersons.add(person);
            }
        }

        return filteredPersons;
    }

    public int calculateYearOfBirth(Person person) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        return currentYear - person.getAge();
    }
}
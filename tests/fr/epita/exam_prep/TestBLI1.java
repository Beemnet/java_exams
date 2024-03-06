package fr.epita.exam_prep;

import java.util.ArrayList;
import java.util.List;

import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.services.PersonDataService;

public class TestBLI1 {
    /**
     * 
     */
    public static void test() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Alex", 25));
        persons.add(new Person("Emily", 30));
        persons.add(new Person("Mark", 35));
        persons.add(new Person("Jane", 28));

        PersonDataService dataService = new PersonDataService();

        int averageAge = dataService.averageAge(persons);
        int thresholdAge = 32;
        List<Person> filteredPersons = dataService.filter(persons, thresholdAge);
        Person firstPerson = persons.get(0);
        int birthYear = dataService.calculateYearOfBirth(firstPerson);

        // Check if average age is calculated correctly
        if (averageAge == 29) {
            System.out.println("TestBLI1 (averageAge) passed.");
        } else {
            System.out.println("TestBLI1 (averageAge) failed.");
        }

        // Check if filter returns correct number of persons
        if (filteredPersons.size() == 1) {
            System.out.println("TestBLI1 (filter) passed.");
        } else {
            System.out.println("TestBLI1 (filter) failed.");
        }

        // Check if calculateBirthYear returns correct birth year
        if (birthYear == (java.time.Year.now().getValue() - firstPerson.getAge())) {
            System.out.println("TestBLI1 (calculateBirthYear) passed.");
        } else {
            System.out.println("TestBLI1 (calculateBirthYear) failed.");
        }

    }

    public static void main(String[] args) {
        // test();
    }
}

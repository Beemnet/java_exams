package fr.epita.exam_prep.daos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;

import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class PersonCDVDAO {

    public List<Person> readAll() throws PersonExtractionException {
        List<Person> people = new ArrayList<>();
        Path currentFilePath = Path.of("./data/data.csv");
        // System.out.println("Looking for file at this location: " +
        // currentFilePath.toAbsolutePath());
        List<String> lines;
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            throw new PersonExtractionException(e);
        }

        lines.remove(0); // Removing header line
        for (String line : lines) {
            String[] parts = line.split(",");
            Person person = extractPerson(parts);
            people.add(person);
        }

        // Sorting the list of people by height
        Collections.sort(people, Comparator.comparingInt(Person::getHeight));

        return people;
    }

    private Person extractPerson(String[] row) {
        Person person = new Person();
        person.setName(row[0].trim());
        person.setSex(row[1].trim());
        person.setAge(Integer.parseInt(row[2].trim()));
        person.setHeight(Integer.parseInt(row[3].trim()));
        person.setWeight(Integer.parseInt(row[4].trim()));
        return person;
    }

}

package fr.epita.exam_prep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class TestSER1 {
    private static String column;

    public static void test() throws PersonExtractionException {
        List<Person> people = read();
        // System.out.println(column);

        if (!people.isEmpty()) {
            System.out.println("TestSER1 passed.");
        }

    }

    public static List<Person> read() throws PersonExtractionException {

        Path currentFilePath = Path.of("./data/data.csv");
        // System.out.println("looking for file at this location:" +
        // currentFilePath.toFile().getAbsolutePath());
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(currentFilePath);
        } catch (IOException e) {
            throw new PersonExtractionException(e);
        }
        column = lines.get(0);
        lines.remove(0);
        List<Person> persons = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            Person person = extractPerson(parts);
            persons.add(person);

        }

        return persons;
    }

    private static Person extractPerson(String[] row) {
        Person person = new Person();
        person.setName(row[0]);
        person.setSex(row[1]);
        person.setAge(Integer.parseInt(row[2].trim()));
        person.setHeight(Integer.parseInt(row[3].trim()));
        person.setWeight(Integer.parseInt(row[4].trim()));

        return person;

    }
}
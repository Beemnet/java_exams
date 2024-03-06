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

    /**
     * @return
     * @throws PersonExtractionException
     */
    public List<Person> readAll() throws PersonExtractionException {
        List<Person> people = new ArrayList<>();
        Path currentFilePath = Path.of("./data/data.csv");
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

    /**
     * @param row
     * @return
     */
    private Person extractPerson(String[] row) {
        Person person = new Person();
        person.setName(row[0].trim());
        person.setSex(row[1].trim());
        person.setAge(Integer.parseInt(row[2].trim()));
        person.setHeight(Integer.parseInt(row[3].trim()));
        person.setWeight(Integer.parseInt(row[4].trim()));
        return person;
    }

    /**
     * @param persons
     * @throws PersonExtractionException
     */
    public void writeAll(List<Person> persons) throws PersonExtractionException {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Name,Height (in),Weight (lbs),Age,Sex\n");

        for (Person person : persons) {
            csvContent.append(person.getName()).append(",");
            csvContent.append(person.getHeight()).append(",");
            csvContent.append(person.getWeight()).append(",");
            csvContent.append(person.getAge()).append(",");
            csvContent.append(person.getSex()).append("\n");
        }

        try {
            Path outputPath = Path.of("./data/data_output.csv");
            Files.writeString(outputPath, csvContent.toString());
        } catch (IOException e) {
            throw new PersonExtractionException(e);
        }
    }
}

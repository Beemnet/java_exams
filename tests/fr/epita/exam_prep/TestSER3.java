package fr.epita.exam_prep;

import java.io.File;
import java.util.List;

import fr.epita.exam_prep.daos.PersonCDVDAO;
import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class TestSER3 {
    public static void test() {
        PersonCDVDAO dao = new PersonCDVDAO();
        String outputFile = "./data/data_output.csv";

        try {
            List<Person> persons = dao.readAll();
            dao.writeAll(persons);

            File file = new File(outputFile);
            if (file.exists() && file.length() > 0) {
                System.out.println("TestSER3 passed.");
            } else {
                System.out.println("TestSER3 failed. Output file was not created or is empty.");
            }

        } catch (PersonExtractionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // test();
    }
}
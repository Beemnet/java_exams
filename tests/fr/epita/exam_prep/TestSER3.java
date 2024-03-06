package fr.epita.exam_prep;

import java.util.List;

import fr.epita.exam_prep.daos.PersonCDVDAO;
import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class TestSER3 {
    public static void test() {
        PersonCDVDAO dao = new PersonCDVDAO();
        try {
            List<Person> persons = dao.readAll();
            dao.writeAll(persons);
            System.out.println("TestSER3 passed.");
        } catch (PersonExtractionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // test();
    }
}
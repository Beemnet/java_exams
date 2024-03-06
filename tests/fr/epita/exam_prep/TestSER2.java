package fr.epita.exam_prep;

import java.util.List;

import fr.epita.exam_prep.daos.PersonCDVDAO;
import fr.epita.exam_prep.datamodels.Person;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class TestSER2 {

    public static void test() {
        PersonCDVDAO personDAO = new PersonCDVDAO();
        try {
            List<Person> people = personDAO.readAll();
            if (!people.isEmpty()) {
                System.out.println("TestSER2 passed.");
            }
        } catch (PersonExtractionException e) {
            System.err.println("Error occurred while reading people data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }
}

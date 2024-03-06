package fr.epita.exam_prep;

import fr.epita.exam_prep.datamodels.Person;

public class TestDMO2 {
    public static void test() {
        // create sample person Alex

        Person person = new Person("Alex", 42);
        if (!person.getName().isBlank()) {
            System.out.println("TestDMO2 passed.");
        }
    }

    public static void main(String[] args) {
        // test();
    }
}

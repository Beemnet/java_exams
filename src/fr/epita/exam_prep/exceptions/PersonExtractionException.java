package fr.epita.exam_prep.exceptions;

public class PersonExtractionException extends Exception {
    public PersonExtractionException(Exception e) {
        super(e);
    }

    public static void main(String[] args) {
        String errorMessage = "An error occurred during person extraction: " + args[0];
        System.out.println(errorMessage);
    }
}

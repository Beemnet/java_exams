package fr.epita.exam_prep.launchers;

import fr.epita.exam_prep.TestDMO2;
import fr.epita.exam_prep.TestSER1;
import fr.epita.exam_prep.TestSER2;
import fr.epita.exam_prep.TestSER3;
import fr.epita.exam_prep.exceptions.PersonExtractionException;

public class Launcher {
    /**
     * @param args
     * @throws PersonExtractionException
     */
    public static void main(String[] args) throws PersonExtractionException {
        TestDMO2.test();
        TestSER1.test();
        TestSER2.test();
        TestSER3.test();

    }
}

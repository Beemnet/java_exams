package test.launcher;

import test.TestBLI1;
import test.TestBLI2;
import test.TestJDB2;
import test.TestJDB3;
import test.TestJDB4;
import test.TestOOP2;

public class TestLauncher {
    public static void main(String[] args) {
        System.out.println("\n\nRunning TestOOP1");
        TestOOP1.test();

        System.out.println("\n\nRunning TestOOP2");
        TestOOP2.test();

        System.out.println("\n\nRunning TestBLI1");
        TestBLI1.test();

        System.out.println("\n\nRunning TestBLI2");
        TestBLI2.test();

        System.out.println("\n\nSkipped running TestJDB1, tables created later.");
        // TestJDB1.test();
        // not necessary as TestJDB3 creates the same database, creates conflict.

        // TestJDB2 inserts into DB, so must come after table creation.
        System.out.println("\n\nRunning TestJDB3");
        TestJDB3.test();

        System.out.println("\n\nRunning TestJDB2");
        TestJDB2.test();

        System.out.println("\n\nRunning TestJDB4");
        TestJDB4.test();
    }
}

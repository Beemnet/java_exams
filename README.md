# Java Project - Fundamentals

This Java project focuses on the fundamentals of programming, covering four domains: Data Modeling, Serialization / Deserialization, Business Logic Implementation, and Java Database Connectivity (JDBC).

## Prerequisites

- Java Development Kit (JDK) installed
- IDE (Integrated Development Environment) of your choice (e.g., Eclipse, IntelliJ)

## Getting Started

1. Clone the repository: `git clone https://github.com/Beemnet/java_exams.git`
2. Import the project into your IDE.
3. Build the project to resolve dependencies.

## Domain 1: Data Modeling

In this domain, we have implemented the Person class to represent a person's bio-characteristics. The class provides encapsulation, appropriate constructors, and a toString() method. You can find the implementation in the `src/main/java/com/example/datamodeling/Person.java` file.

To test the Person class, run the `TestDMO2` class located in `src/fr/epita/exam_prep/datamodels/TestDMO2.java`.

## Domain 2: Serialization / Deserialization

This domain focuses on working with serialization and deserialization. We have implemented the necessary classes to read data from a CSV file and perform serialization and deserialization operations. The main classes to look at are:

- `PersonCSVDAO`: Handles reading data from the CSV file.
- `TestSER1`: Tests the serialization and deserialization operations.

To test the serialization and deserialization functionality, run the `TestSER1` class located in `test/fr/epita/exam_prep/TestSER1.java`.

## Domain 3: Business Logic Implementation

In this domain, we have implemented the `PersonDataService` class that provides various business logic methods related to persons. These methods include calculating statistics, filtering persons, and retrieving birth years.

To test the business logic methods, run the `TestBLI1` class located in `test/fr/epita/exam_prep/TestBLI1.java`.

## Domain 4: Java Database Connectivity (JDBC)

The final domain focuses on JDBC and working with a relational database. We have implemented the `PersonJDBCDAO` class to handle database operations such as inserting and searching persons.

To test the JDBC functionality, run the `TestJDB2` class located in `test/fr/epita/exam_prep/TestJDB2.java`.

## Contributing

Contributions to this project are always welcome. If you find any issues or have suggestions for improvements, please submit a pull request.

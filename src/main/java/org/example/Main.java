package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");
        personMatcher.processRecords(records);
    }
}
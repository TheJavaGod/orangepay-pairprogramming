package org.example;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class Person {
    public String id;

    public String name;

    public String idType;

    public String idNumber;

    public String dateOfBirth;

    public static Person buildPerson(String data) throws ParseException {
        String[] values = data.split(",");

        if (values.length < 5) {
            throw new ParseException("Missing fields in record.", 5 - values.length);
        }

        // check that all fields are non-empty
        for (int i = 0; i < values.length; i++) {
            if (values[i].isBlank()) {
                throw new ParseException("Field contains no data", i);
            }
        }

        Person person = new Person();
        person.id = values[0];
        person.name = values[1];
        person.idType = values[2];
        person.idNumber = values[3];
        person.dateOfBirth = values[4];

        return person;
    }
}

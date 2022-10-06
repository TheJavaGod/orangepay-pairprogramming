package org.example;

public class Person {
    public String id;

    public String name;

    public String idType;

    public String idNumber;

    public String dateOfBirth;

    public static Person buildPerson(String data) {
        String[] values = data.split(",");

        Person person = new Person();
        person.id = values[0];
        person.name = values[1];
        person.idType = values[2];
        person.idNumber = values[3];
        person.dateOfBirth = values[4];

        return person;
    }
}

package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("records.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        List<Person> records = br.lines().map(s -> {
            try {
                return Person.buildPerson(s);
            } catch (ParseException e) {
                System.err.println(e.getMessage());
                return null;
            }
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

        Map<String, Set<Person>> nameMap = new HashMap<>();
        Map<String, Set<Person>> idNumberMap = new HashMap<>();
        Map<String, Set<Person>> dobMap = new HashMap<>();

        List<Set<Person>> matches = new ArrayList<>();
        for (Person person : records) {
            nameMap.computeIfAbsent(person.name, k -> new HashSet<>()).add(person);
            idNumberMap.computeIfAbsent(person.idNumber, k -> new HashSet<>()).add(person);
            dobMap.computeIfAbsent(person.dateOfBirth, k -> new HashSet<>()).add(person);

            List<Person> matchingPersons = Stream.of(nameMap.get(person.name).stream(), idNumberMap.get(person.idNumber).stream(), dobMap.get(person.dateOfBirth).stream())
                    .flatMap(s -> s)
                    .filter(p -> !p.equals(person))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .filter(p -> p.getValue() > 1)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            for (Person matchingPerson : matchingPersons) {
                Set<Person> matchingPair = new HashSet<>(Arrays.asList(person, matchingPerson));
                if (!matches.contains(matchingPair)) {
                    matches.add(matchingPair);
                }
            }
        }

        for (Set<Person> match : matches) {
            System.out.println(match.stream().map(p -> p.id).collect(Collectors.joining(", ")) + " match");
        }
    }
}
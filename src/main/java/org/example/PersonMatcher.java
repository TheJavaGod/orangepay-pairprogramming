package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonMatcher {
    public List<Person> loadFromFile(String path) throws IOException {
        File file = new File("records.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file)))  {
            return br.lines().map(s -> {
                        try {
                            return Person.buildPerson(s);
                        } catch (ParseException e) {
                            System.err.println(e.getMessage());
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    public void processRecords(List<Person> records) {
        Map<String, Set<Person>> nameMap = new HashMap<>();
        Map<String, Set<Person>> idNumberMap = new HashMap<>();
        Map<String, Set<Person>> dobMap = new HashMap<>();

        List<Set<Person>> matches = new ArrayList<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < records.size(); i++) {
            Person person = records.get(i);
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
            if (i % 50 == 0) {
                System.out.println(String.format("50 records processed in %.3f ms", (System.nanoTime() - startTime) / 1000000f));
                startTime = System.nanoTime();
            }
        }

        for (Set<Person> match : matches) {
            System.out.println(match.stream().map(p -> p.id).collect(Collectors.joining(", ")) + " match");
        }
    }
}

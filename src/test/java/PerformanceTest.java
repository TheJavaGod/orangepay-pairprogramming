import org.example.Person;
import org.example.PersonMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class PerformanceTest {
    @Test
    public void test6Records() throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");

        long startTime = System.nanoTime();
        personMatcher.processRecords(records);
        float totalTime = (System.nanoTime() - startTime) / 1000000f;

        System.out.println(String.format("Total Time: %.3f ms", totalTime));
        System.out.println(String.format("Time Per Record: %.3f ms", totalTime / records.size()));
        Assertions.assertTrue(true);
    }

    @Test
    public void test50Records() throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");
        for (int i = 0; i < 9; i++) {
            records.addAll(records.subList(0, 4));
        }
        long startTime = System.nanoTime();
        personMatcher.processRecords(records);
        float totalTime = (System.nanoTime() - startTime) / 1000000f;

        System.out.println(String.format("Total Time: %.3f ms", totalTime));
        System.out.println(String.format("Time Per Record: %.3f ms", totalTime / records.size()));
        Assertions.assertTrue(true);
    }

    @Test
    public void test500Records() throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");
        for (int i = 0; i < 99; i++) {
            records.addAll(records.subList(0, 4));
        }
        long startTime = System.nanoTime();
        personMatcher.processRecords(records);
        float totalTime = (System.nanoTime() - startTime) / 1000000f;

        System.out.println(String.format("Total Time: %.3f ms", totalTime));
        System.out.println(String.format("Time Per Record: %.3f ms", totalTime / records.size()));
        Assertions.assertTrue(true);
    }

    @Test
    public void test1000Records() throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");
        for (int i = 0; i < 199; i++) {
            records.addAll(records.subList(0, 4));
        }
        long startTime = System.nanoTime();
        personMatcher.processRecords(records);
        float totalTime = (System.nanoTime() - startTime) / 1000000f;

        System.out.println(String.format("Total Time: %.3f ms", totalTime));
        System.out.println(String.format("Time Per Record: %.3f ms", totalTime / records.size()));
        Assertions.assertTrue(true);
    }

    @Test
    public void test5000Records() throws IOException {
        PersonMatcher personMatcher = new PersonMatcher();
        List<Person> records = personMatcher.loadFromFile("records.txt");
        for (int i = 0; i < 999; i++) {
            records.addAll(records.subList(0, 4));
        }
        long startTime = System.nanoTime();
        personMatcher.processRecords(records);
        float totalTime = (System.nanoTime() - startTime) / 1000000f;

        System.out.println(String.format("Total Time: %.3f ms", totalTime));
        System.out.println(String.format("Time Per Record: %.3f ms", totalTime / records.size()));
        Assertions.assertTrue(true);
    }
}

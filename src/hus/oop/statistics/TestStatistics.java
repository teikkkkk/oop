package hus.oop.statistics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestStatistics {
    private Statistics statistics;
    private static final Random random = new Random();
    private static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileWriter("NguyenVanA_123456_Statistics.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        TestStatistics arrayTest = new TestStatistics(new Statistics(new MyArrayList()));
        TestStatistics listTest = new TestStatistics(new Statistics(new MyLinkedList()));

        System.out.println("Testing Array Implementation:");
        writer.println("Testing Array Implementation:");
        arrayTest.testMyArrayList();

        System.out.println("\nTesting Linked List Implementation:");
        writer.println("\nTesting Linked List Implementation:");
        listTest.testMyLinkedList();

        writer.close();
    }

    public void testMyArrayList() {
        // Generate random length between 30 and 50
        int length = random.nextInt(21) + 30;
        printAndWrite("Number of elements: " + length);

        // Create and fill random data
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < length; i++) {
            double value = random.nextDouble() * 19 + 1; // Random double between 1 and 20
            list.add(value);
        }

        // Create statistics object
        statistics = new Statistics(list);
        runStatisticalTests();
    }

    public void testMyLinkedList() {
        // Generate random length between 30 and 50
        int length = random.nextInt(21) + 30;
        printAndWrite("Number of elements: " + length);

        // Create and fill random data
        MyLinkedList list = new MyLinkedList();
        for (int i = 0; i < length; i++) {
            double value = random.nextDouble() * 19 + 1; // Random double between 1 and 20
            list.add(value);
        }

        // Create statistics object
        statistics = new Statistics(list);
        runStatisticalTests();
    }

    private void runStatisticalTests() {
        // Print original data
        printAndWrite("\nOriginal dataset:");
        printAndWrite(statistics.getData().toString());

        // Print sorted data
        printAndWrite("\nSorted dataset:");
        MyList sortedList = statistics.getData().sortIncreasing();
        printAndWrite(sortedList.toString());

        // Calculate and print statistics
        printAndWrite("\nBasic Statistics:");
        printAndWrite("Maximum: " + statistics.max());
        printAndWrite("Minimum: " + statistics.min());
        printAndWrite("Mean: " + String.format("%.2f", statistics.mean()));
        printAndWrite("Variance: " + String.format("%.2f", statistics.variance()));

        // Calculate and print ranks
        printAndWrite("\nRanks:");
        double[] ranks = statistics.rank();
        StringBuilder rankStr = new StringBuilder();
        for (double rank : ranks) {
            rankStr.append(String.format("%.1f ", rank));
        }
        printAndWrite(rankStr.toString());

        // Test search functionality
        double searchValue = random.nextDouble() * 19 + 1;
        int searchResult = statistics.search(searchValue);
        printAndWrite("\nSearching for value " + String.format("%.2f", searchValue));
        printAndWrite("Search result index: " + searchResult);
    }

    private void printAndWrite(String message) {
        System.out.println(message);
        writer.println(message);
    }
}

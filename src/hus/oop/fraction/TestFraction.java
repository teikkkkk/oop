package hus.oop.fraction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestFraction {
    private MyDataSet myDataSet;
    private static final Random random = new Random();
    private static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileWriter("NguyenVanA_123456_MyFractions.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TestFraction(MyDataSet myDataSet) {
        this.myDataSet = myDataSet;
    }

    public static void main(String[] args) {
        TestFraction arrayTest = new TestFraction(new MyArrayDataSet());
        TestFraction listTest = new TestFraction(new MyListDataSet());

        System.out.println("Testing Array Implementation:");
        writer.println("Testing Array Implementation:");
        arrayTest.testMyArrayDataSet();

        System.out.println("\nTesting List Implementation:");
        writer.println("\nTesting List Implementation:");
        listTest.testMyListDataSet();

        writer.close();
    }

    public void testMyArrayDataSet() {
        // 1. Generate random number between 30 and 50
        int numbers = random.nextInt(21) + 30;
        printAndWrite("Number of fractions: " + numbers);

        // 2. Create random fractions and store them
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1;
            int denominator = random.nextInt(100) + 1;
            myDataSet.append(new MyFraction(numerator, denominator));
        }
        printAndWrite("Original dataset:");
        printAndWrite(myDataSet.myDataSetToString());

        // 3. Sort and print
        printAndWrite("\nSorted increasing:");
        printAndWrite(myDataSet.sortIncreasing().myDataSetToString());

        printAndWrite("\nSorted decreasing:");
        printAndWrite(myDataSet.sortDecreasing().myDataSetToString());

        // 4. Simplify and print
        printAndWrite("\nSimplified fractions (original order):");
        printAndWrite(myDataSet.toSimplify().myDataSetToString());

        printAndWrite("\nSimplified fractions (increasing order):");
        printAndWrite(myDataSet.toSimplify().sortIncreasing().myDataSetToString());

        printAndWrite("\nSimplified fractions (decreasing order):");
        printAndWrite(myDataSet.toSimplify().sortDecreasing().myDataSetToString());
    }

    public void testMyListDataSet() {
        // 1. Generate random number between 30 and 50
        int numbers = random.nextInt(21) + 30;
        printAndWrite("Number of fractions: " + numbers);

        // 2. Create random fractions and store them
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1;
            int denominator = random.nextInt(100) + 1;
            myDataSet.append(new MyFraction(numerator, denominator));
        }
        printAndWrite("Original dataset:");
        printAndWrite(myDataSet.myDataSetToString());

        // 3. Sort and print
        printAndWrite("\nSorted increasing:");
        printAndWrite(myDataSet.sortIncreasing().myDataSetToString());

        printAndWrite("\nSorted decreasing:");
        printAndWrite(myDataSet.sortDecreasing().myDataSetToString());

        // 4. Simplify and print
        printAndWrite("\nSimplified fractions (original order):");
        printAndWrite(myDataSet.toSimplify().myDataSetToString());

        printAndWrite("\nSimplified fractions (increasing order):");
        printAndWrite(myDataSet.toSimplify().sortIncreasing().myDataSetToString());

        printAndWrite("\nSimplified fractions (decreasing order):");
        printAndWrite(myDataSet.toSimplify().sortDecreasing().myDataSetToString());
    }

    private void printAndWrite(String message) {
        System.out.println(message);
        writer.println(message);
    }
}

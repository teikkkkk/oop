package hus.oop.vector;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestVector {
    private MyVector vector;
    private static final Random random = new Random();
    private static final PrintWriter writer;

    static {
        try {
            writer = new PrintWriter(new FileWriter("NguyenVanA_123456_Vector.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TestVector(MyVector vector) {
        this.vector = vector;
    }

    public static void main(String[] args) {
        TestVector arrayTest = new TestVector(new MyArrayVector());
        TestVector listTest = new TestVector(new MyListVector());

        System.out.println("Testing Array Vector Implementation:");
        writer.println("Testing Array Vector Implementation:");
        arrayTest.testArrayVector();

        System.out.println("\nTesting List Vector Implementation:");
        writer.println("\nTesting List Vector Implementation:");
        listTest.testListVector();

        writer.close();
    }

    public void testArrayVector() {
        // Generate random size
        int n = random.nextInt(5) + 5; // Random size between 5 and 9
        MyArrayVector vector = new MyArrayVector();
        
        // Create vector with random elements
        System.out.println("Creating vector of size " + n);
        writer.println("Creating vector of size " + n);
        for (int i = 0; i < n; i++) {
            double value = random.nextDouble() * 10;
            vector.insert(value);
        }
        
        printAndWrite("Initial vector: " + vector);

        // Test insert
        double insertValue = random.nextDouble() * 10;
        vector.insert(insertValue, 2);
        printAndWrite("After inserting " + insertValue + " at index 2: " + vector);

        // Test remove
        vector.remove(1);
        printAndWrite("After removing element at index 1: " + vector);

        // Test set
        double setValue = random.nextDouble() * 10;
        vector.set(setValue, 0);
        printAndWrite("After setting first element to " + setValue + ": " + vector);

        // Test add scalar
        double addValue = random.nextDouble() * 5;
        MyVector added = vector.add(addValue);
        printAndWrite("After adding " + addValue + " to all elements: " + added);

        // Test scale
        double scaleValue = random.nextDouble() * 3;
        vector.scale(scaleValue);
        printAndWrite("After scaling by " + scaleValue + ": " + vector);

        // Test power
        vector.pow(2);
        printAndWrite("After squaring all elements: " + vector);

        // Test norm
        printAndWrite("Vector norm: " + vector.norm());

        // Test dot product
        MyArrayVector another = new MyArrayVector();
        for (int i = 0; i < vector.size(); i++) {
            another.insert(random.nextDouble() * 5);
        }
        printAndWrite("Another vector: " + another);
        printAndWrite("Dot product: " + vector.dot(another));
    }

    public void testListVector() {
        // Generate random size
        int n = random.nextInt(5) + 5; // Random size between 5 and 9
        MyListVector vector = new MyListVector();
        
        // Create vector with random elements
        System.out.println("Creating vector of size " + n);
        writer.println("Creating vector of size " + n);
        for (int i = 0; i < n; i++) {
            double value = random.nextDouble() * 10;
            vector.insert(value);
        }
        
        printAndWrite("Initial vector: " + vector);

        // Test insert
        double insertValue = random.nextDouble() * 10;
        vector.insert(insertValue, 2);
        printAndWrite("After inserting " + insertValue + " at index 2: " + vector);

        // Test remove
        vector.remove(1);
        printAndWrite("After removing element at index 1: " + vector);

        // Test extract
        int[] indices = {0, 2};
        MyVector extracted = vector.extract(indices);
        printAndWrite("Extracted vector with indices [0, 2]: " + extracted);

        // Test addTo
        MyListVector another = new MyListVector();
        for (int i = 0; i < vector.size(); i++) {
            another.insert(random.nextDouble() * 5);
        }
        printAndWrite("Another vector: " + another);
        vector.addTo(another);
        printAndWrite("After adding another vector: " + vector);

        // Test minusFrom
        vector.minusFrom(2.5);
        printAndWrite("After subtracting 2.5 from all elements: " + vector);

        // Test norm
        printAndWrite("Vector norm: " + vector.norm());
    }

    private void printAndWrite(String message) {
        System.out.println(message);
        writer.println(message);
    }
}

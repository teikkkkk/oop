package hus.oop.vector;

import java.util.ArrayList;
import java.util.List;

public class MyListVector extends MyAbstractVector {
    private List<Double> data;

    /**
     * Khởi tạo mặc định cho vector.
     */
    public MyListVector() {
        data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public double coordinate(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        return data.get(index);
    }

    @Override
    public double[] coordinates() {
        double[] result = new double[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = data.get(i);
        }
        return result;
    }

    @Override
    public MyListVector insert(double value) {
        data.add(value);
        return this;
    }

    @Override
    public MyListVector insert(double value, int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        data.add(index, value);
        return this;
    }

    @Override
    public MyListVector remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        data.remove(index);
        return this;
    }

    @Override
    public MyListVector extract(int[] indices) {
        MyListVector result = new MyListVector();
        for (int index : indices) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Invalid index in extraction");
            }
            result.insert(data.get(index));
        }
        return result;
    }

    @Override
    public void set(double value, int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        data.set(index, value);
    }

    @Override
    public MyListVector add(double value) {
        MyListVector result = new MyListVector();
        for (double element : data) {
            result.insert(element + value);
        }
        return result;
    }

    @Override
    public MyListVector add(MyVector another) {
        if (another.size() != size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        MyListVector result = new MyListVector();
        for (int i = 0; i < size(); i++) {
            result.insert(data.get(i) + another.coordinate(i));
        }
        return result;
    }

    @Override
    public MyListVector addTo(double value) {
        for (int i = 0; i < size(); i++) {
            data.set(i, data.get(i) + value);
        }
        return this;
    }

    @Override
    public MyListVector addTo(MyVector another) {
        if (another.size() != size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        for (int i = 0; i < size(); i++) {
            data.set(i, data.get(i) + another.coordinate(i));
        }
        return this;
    }

    @Override
    public MyListVector minus(double value) {
        return add(-value);
    }

    @Override
    public MyListVector minus(MyVector another) {
        if (another.size() != size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        MyListVector result = new MyListVector();
        for (int i = 0; i < size(); i++) {
            result.insert(data.get(i) - another.coordinate(i));
        }
        return result;
    }

    @Override
    public MyListVector minusFrom(double value) {
        return addTo(-value);
    }

    @Override
    public MyListVector minusFrom(MyVector another) {
        if (another.size() != size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        for (int i = 0; i < size(); i++) {
            data.set(i, data.get(i) - another.coordinate(i));
        }
        return this;
    }

    @Override
    public double dot(MyVector another) {
        if (another.size() != size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        double result = 0;
        for (int i = 0; i < size(); i++) {
            result += data.get(i) * another.coordinate(i);
        }
        return result;
    }

    @Override
    public MyListVector pow(double power) {
        for (int i = 0; i < size(); i++) {
            data.set(i, Math.pow(data.get(i), power));
        }
        return this;
    }

    @Override
    public MyListVector scale(double value) {
        for (int i = 0; i < size(); i++) {
            data.set(i, data.get(i) * value);
        }
        return this;
    }

    @Override
    public double norm() {
        double sum = 0;
        for (double element : data) {
            sum += element * element;
        }
        return Math.sqrt(sum);
    }
}

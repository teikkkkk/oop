package hus.oop.fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyListDataSet implements MyDataSet {
    private List<MyFraction> fractions;

    /**
     * Hàm dựng khởi tạo list chứa các phân số.
     */
    public MyListDataSet() {
        this.fractions = new ArrayList<>();
    }

    /**
     * Hàm dựng khởi tạo list chứa các phân số theo truyền vào.
     * @param fractions
     */
    public MyListDataSet(List<MyFraction> fractions) {
        this.fractions = new ArrayList<>(fractions);
    }

    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (index < 0 || index > fractions.size()) {
            return false;
        }
        fractions.add(index, fraction);
        return true;
    }

    @Override
    public boolean append(MyFraction fraction) {
        return fractions.add(fraction);
    }

    @Override
    public MyListDataSet toSimplify() {
        MyListDataSet result = new MyListDataSet();
        for (MyFraction fraction : fractions) {
            MyFraction simplified = fraction.clone();
            simplified.simplify();
            result.append(simplified);
        }
        return result;
    }

    @Override
    public MyListDataSet sortIncreasing() {
        MyListDataSet result = new MyListDataSet(fractions);
        Collections.sort(result.fractions, (f1, f2) -> f1.compareTo(f2));
        return result;
    }

    @Override
    public MyListDataSet sortDecreasing() {
        MyListDataSet result = new MyListDataSet(fractions);
        Collections.sort(result.fractions, (f1, f2) -> f2.compareTo(f1));
        return result;
    }

    @Override
    public String myDataSetToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < fractions.size(); i++) {
            result.append(fractions.get(i).toString());
            if (i < fractions.size() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }
}

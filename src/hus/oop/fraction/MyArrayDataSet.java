package hus.oop.fraction;

public class MyArrayDataSet implements MyDataSet {
    private static int DEFAULT_CAPACITY = 16;
    private MyFraction[] fractions;
    private int length;

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số có kích thước là DEFAULT_CAPACITY.
     */
    public MyArrayDataSet() {
        fractions = new MyFraction[DEFAULT_CAPACITY];
        length = 0;
    }

    /**
     * Hàm dựng khởi tạo mảng chứa các phân số truyền vào.
     * @param fractions mảng các phân số
     */
    public MyArrayDataSet(MyFraction[] fractions) {
        // Khởi tạo mảng mới với kích thước phù hợp
        this.fractions = new MyFraction[Math.max(fractions.length, DEFAULT_CAPACITY)];
        this.length = 0; // Reset length về 0
        
        // Chỉ copy các phần tử không null
        for (MyFraction fraction : fractions) {
            if (fraction != null) {
                this.fractions[length++] = fraction.clone();
            }
        }
    }

    /**
     * Phương thức chèn phân số fraction vào vị trí index.
     * Nếu index nằm ngoài đoạn [0, length] thì không chèn được vào.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction là một phân số.
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (!checkBoundaries(index, length)) {
            return false;
        }
        
        if (length >= fractions.length) {
            allocateMore();
        }
        
        for (int i = length; i > index; i--) {
            fractions[i] = fractions[i - 1];
        }
        
        fractions[index] = fraction;
        length++;
        return true;
    }

    /**
     * Phương thức thêm phân số fraction vào vị trí cuối cùng chưa có dứ liệu của mảng data.
     * Nếu mảng hết chỗ để thêm dữ liệu, mở rộng kích thước mảng gấp đôi.
     * @param fraction
     * @return true nếu chèn được số vào, false nếu không chèn được số vào.
     */
    @Override
    public boolean append(MyFraction fraction) {
        if (length >= fractions.length) {
            allocateMore();
        }
        
        fractions[length++] = fraction;
        return true;
    }

    @Override
    public MyArrayDataSet toSimplify() {
        MyArrayDataSet result = new MyArrayDataSet();
        for (int i = 0; i < length; i++) {
            MyFraction copy = new MyFraction(fractions[i].getNumerator(), fractions[i].getDenominator());
            copy.simplify(); 
            result.append(copy);
        }
        return result;
    }

    @Override
    public MyArrayDataSet sortIncreasing() {
        MyArrayDataSet result = new MyArrayDataSet(fractions);
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (result.fractions[j].compareTo(result.fractions[j + 1]) > 0) {
                    // Swap elements
                    MyFraction temp = result.fractions[j];
                    result.fractions[j] = result.fractions[j + 1];
                    result.fractions[j + 1] = temp;
                }
            }
        }
        return result;
    }

    @Override
    public MyArrayDataSet sortDecreasing() {
        MyArrayDataSet result = new MyArrayDataSet(fractions);
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (result.fractions[j].compareTo(result.fractions[j + 1]) < 0) {
                    // Swap elements
                    MyFraction temp = result.fractions[j];
                    result.fractions[j] = result.fractions[j + 1];
                    result.fractions[j + 1] = temp;
                }
            }
        }
        return result;
    }

    @Override
    public String myDataSetToString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (fractions[i] != null) {
                result.append(fractions[i].toString());
                if (i < length - 1) {
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }

    /**
     * Phương thức mở rộng kích thước mảng gấp đôi.
     */
    private void allocateMore() {
        MyFraction[] newFractions = new MyFraction[fractions.length * 2];
        System.arraycopy(fractions, 0, newFractions, 0, length);
        fractions = newFractions;
    }

    /**
     * Phương thức kiểm tra xem index có nằm trong khoảng [0, upperBound] hay không.
     */
    private boolean checkBoundaries(int index, int upperBound) {
        return index >= 0 && index <= upperBound;
    }
}

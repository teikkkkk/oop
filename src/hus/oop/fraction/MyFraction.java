package hus.oop.fraction;

public class MyFraction implements MyFractionComparable {
    private int numerator;
    private int denominator;

    /**
     * Hàm dựng khởi tạo giá trị mặc định là 1/1.
     */
    public MyFraction() {
        this.numerator = 1;
        this.denominator = 1;
    }

    /**
     * Hàm dựng khởi tạo giá trị cho tử số và mẫu số.
     * @param numerator
     * @param denominator
     */
    public MyFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Hàm dựng copy, copy giá trị của phân số truyền vào.
     * @param copyMyFraction
     */
    public MyFraction(MyFraction copyMyFraction) {
        this.numerator = copyMyFraction.numerator;
        this.denominator = copyMyFraction.denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.denominator = denominator;
    }

    /**
     * Phương thức trả về giá trị kiểu byte của phân số.
     * @return
     */
    public byte byteValue() {
        return (byte) (numerator / denominator);
    }

    /**
     * Phương thức trả về giá trị kiểu int của phân số.
     * @return
     */
    public int intValue() {
        return numerator / denominator;
    }

    /**
     * Phương thức trả về giá trị kiểu long của phân số.
     * @return
     */
    public long longValue() {
        return (long) numerator / denominator;
    }

    /**
     * Phương thức trả về giá trị kiểu short của phân số.
     * @return
     */
    public short shortValue() {
        return (short) (numerator / denominator);
    }

    /**
     * Phương thức trả về giá trị kiểu double của phân số.
     * @return
     */
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    /**
     * Phương thức trả về giá trị kiểu float của phân số.
     * @return
     */
    public float floatValue() {
        return (float) numerator / denominator;
    }

    /**
     * Phương thức tính ước số chung lớn nhất của tử số và mẫu số.
     * @return ước số chung lớn nhất của tử số và mẫu số.
     */
    private int gcd() {
        int a = Math.abs(numerator);
        int b = Math.abs(denominator);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Phương thức rút gọn phân số về phân số tối giản.
     */
    public void simplify() {
        int gcd = gcd();
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    @Override
    public int compareTo(MyFraction another) {
        long thisNumerator = (long) this.numerator * another.denominator;
        long anotherNumerator = (long) another.numerator * this.denominator;
        return Long.compare(thisNumerator, anotherNumerator);
    }

    /**
     * Phương thức mô tả phân số theo định dạng numerator/denominator;
     * @return
     */
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public MyFraction clone() {
        return new MyFraction(this);
    }
}

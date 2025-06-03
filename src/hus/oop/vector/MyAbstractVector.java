package hus.oop.vector;

public abstract class MyAbstractVector implements MyVector {
    /**
     * Mô tả vector theo định dạng [a1 a2 ... an]
     * @return String representation of the vector
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            result.append(coordinate(i));
            if (i < size() - 1) {
                result.append(" ");
            }
        }
        result.append("]");
        return result.toString();
    }

    /**
     * So sánh hai vector có bằng nhau không.
     * Hai vector bằng nhau nếu có cùng kích thước và có các phần tử bằng nhau.
     * @param another vector to compare with
     * @return true if vectors are equal, false otherwise
     */
    @Override
    public boolean equals(MyVector another) {
        if (another == null || size() != another.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (coordinate(i) != another.coordinate(i)) {
                return false;
            }
        }
        return true;
    }
}

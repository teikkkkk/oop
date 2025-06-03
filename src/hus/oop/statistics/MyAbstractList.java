package hus.oop.statistics;

public abstract class MyAbstractList implements MyList {
    /**
     * Mô tả dữ liệu của list.
     * @return mô tả list theo định dạng [a1 a2 a3 ... an]
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        MyIterator iterator = iterator();
        while (iterator.hasNext()) {
            Number current = iterator.next();
            sb.append(current);
            if (iterator.hasNext()) {
                sb.append(" ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    /**
     * Phương thức abstract để lấy iterator,
     * sẽ được cài đặt bởi các lớp con cụ thể
     */
    public abstract MyIterator iterator();
}

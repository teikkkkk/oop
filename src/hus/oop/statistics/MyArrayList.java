package hus.oop.statistics;

public class MyArrayList extends MyAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayList() {
        data = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(double element) {
        if (size >= data.length) {
            allocateMore();
        }
        data[size++] = element;
    }

    @Override
    public void insert(double element, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        if (size >= data.length) {
            allocateMore();
        }
        
        // Dịch phải các phần tử để tạo chỗ trống
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        
        data[index] = element;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        
        // Dịch trái các phần tử để ghi đè lên phần tử cần xóa
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        
        size--;
    }

    @Override
    public MyArrayList sortIncreasing() {
        MyArrayList result = new MyArrayList();
        // Copy dữ liệu
        for (int i = 0; i < size; i++) {
            result.add(data[i]);
        }
        
        // Sắp xếp bubble sort
        for (int i = 0; i < result.size - 1; i++) {
            for (int j = 0; j < result.size - i - 1; j++) {
                if (result.data[j] > result.data[j + 1]) {
                    // Đổi chỗ
                    double temp = result.data[j];
                    result.data[j] = result.data[j + 1];
                    result.data[j + 1] = temp;
                }
            }
        }
        
        return result;
    }

    @Override
    public int binarySearch(double element) {
        // Tìm kiếm nhị phân trên mảng đã sắp xếp
        MyArrayList sorted = sortIncreasing();
        int left = 0;
        int right = sorted.size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sorted.data[mid] == element) {
                return mid;
            }
            
            if (sorted.data[mid] < element) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1; // Không tìm thấy
    }

    @Override
    public MyIterator iterator() {
        return new MyArrayListIterator(0);
    }

    @Override
    public MyIterator iterator(int position) {
        return new MyArrayListIterator(position);
    }

    /**
     * Cấp phát gấp đôi chỗ cho danh sách khi cần thiết.
     */
    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private class MyArrayListIterator implements MyIterator {
        private int currentPosition;

        public MyArrayListIterator(int position) {
            this.currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Number next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            return data[currentPosition++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--currentPosition);
        }
    }
}

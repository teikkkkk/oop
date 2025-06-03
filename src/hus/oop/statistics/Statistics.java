package hus.oop.statistics;

public class Statistics {
    private MyList data;

    /**
     * Khởi tạo dữ liệu cho BasicStatistic.
     */
    public Statistics(MyList data) {
        this.data = data;
    }

    /**
     * Lấy giá trị lớn nhất trong list.
     * @return giá trị lớn nhất.
     */
    public double max() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list");
        }
        
        MyIterator iterator = data.iterator(0);   
        double max = (double) iterator.next();
        
        while (iterator.hasNext()) {
            double current = (double) iterator.next();
            if (current > max) {
                max = current;
            }
        }
        return max;
    }

    /**
     * Lấy giá trị nhỏ nhất trong list.
     * @return giá trị nhỏ nhất.
     */
    public double min() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list");
        }
        
        MyIterator iterator = data.iterator(0);   
        double min = (double) iterator.next();
        
        while (iterator.hasNext()) {
            double current = (double) iterator.next();
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    /**
     * Tính kỳ vọng của mẫu theo dữ liệu trong list.
     * @return kỳ vọng.
     */
    public double mean() {
        if (data.size() == 0) {
            throw new IllegalStateException("Empty list");
        }
        
        double sum = 0;
        MyIterator iterator = data.iterator(0);   
        while (iterator.hasNext()) {
            sum += (double) iterator.next();
        }
        return sum / data.size();
    }

    /**
     * Tính phương sai của mẫu theo dữ liệu trong list.
     * @return phương sai.
     */
    public double variance() {
        if (data.size() <= 1) {
            throw new IllegalStateException("Need at least 2 elements");
        }
        
        double mean = mean();
        double sumSquaredDiff = 0;
        
        MyIterator iterator = data.iterator(0);   
        while (iterator.hasNext()) {
            double diff = (double) iterator.next() - mean;
            sumSquaredDiff += diff * diff;
        }
        
        return sumSquaredDiff / (data.size() - 1);
    }

    /**
     * Tìm kiếm trong list có phẩn tử nào có giá trị bằng data không.
     * @return index của phần tử, -1 nếu không tìm thấy
     */
    public int search(double element) {
        return data.binarySearch(element);
    }

    /**
     * Tính rank của các phần tử trong list.
     * @return rank của các phần tử trong list
     */
    public double[] rank() {
        int size = data.size();
        if (size == 0) {
            return new double[0];
        }

        // Create array of indices and values
        double[] values = new double[size];
        int[] indices = new int[size];
        MyIterator iterator = data.iterator(0);   
        for (int i = 0; i < size; i++) {
            values[i] = (double) iterator.next();
            indices[i] = i;
        }

        // Sort indices based on values
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (values[indices[j]] > values[indices[j + 1]]) {
                    int temp = indices[j];
                    indices[j] = indices[j + 1];
                    indices[j + 1] = temp;
                }
            }
        }

        // Compute ranks
        double[] ranks = new double[size];
        for (int i = 0; i < size; i++) {
            int j = i;
            while (j < size - 1 && values[indices[j]] == values[indices[j + 1]]) {
                j++;
            }
            
            // Average rank for ties
            double avgRank = (i + j) / 2.0 + 1;
            for (int k = i; k <= j; k++) {
                ranks[indices[k]] = avgRank;
            }
            i = j;
        }

        return ranks;
    }

    /**
     * Lấy danh sách dữ liệu.
     * @return danh sách dữ liệu
     */
    public MyList getData() {
        return data;
    }
}

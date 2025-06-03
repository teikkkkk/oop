package hus.oop.statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(double data) {
        MyNode newNode = new MyNode(data);
        if (top == null) {
            top = newNode;
        } else {
            MyNode current = top;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode newNode = new MyNode(data);
        if (index == 0) {
            newNode.setNext(top);
            top = newNode;
        } else {
            MyNode previous = getNodeByIndex(index - 1);
            newNode.setNext(previous.getNext());
            previous.setNext(newNode);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            top = top.getNext();
        } else {
            MyNode previous = getNodeByIndex(index - 1);
            previous.setNext(previous.getNext().getNext());
        }
        size--;
    }

    @Override
    public MyLinkedList sortIncreasing() {
        MyLinkedList result = new MyLinkedList();
        if (size == 0) return result;

        // Copy data to array for easier sorting
        double[] arr = new double[size];
        MyNode current = top;
        for (int i = 0; i < size; i++) {
            arr[i] = current.getData();
            current = current.getNext();
        }

        // Bubble sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // Create new sorted list
        for (double value : arr) {
            result.add(value);
        }
        return result;
    }

    @Override
    public int binarySearch(double data) {
        // Convert to sorted array first
        MyLinkedList sorted = sortIncreasing();
        int left = 0;
        int right = sorted.size - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            double midVal = sorted.getNodeByIndex(mid).getData();

            if (midVal == data) {
                return mid;
            }
            if (midVal < data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public MyIterator iterator() {
        return new MyLinkedListIterator(0);
    }

    @Override
    public MyIterator iterator(int position) {
        return new MyLinkedListIterator(position);
    }

    /**
     * Lấy node ở vị trí index.
     * @param index vị trí cần lấy node
     * @return node tại vị trí index
     */
    private MyNode getNodeByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        MyNode current = top;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private class MyLinkedListIterator implements MyIterator {
        private int currentPosition;
        private MyNode currentNode;

        public MyLinkedListIterator(int position) {
            this.currentPosition = position;
            this.currentNode = position == 0 ? top : getNodeByIndex(position);
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
            double data = currentNode.getData();
            currentNode = currentNode.getNext();
            currentPosition++;
            return data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(--currentPosition);
            currentNode = currentPosition == 0 ? top : getNodeByIndex(currentPosition);
        }
    }
}

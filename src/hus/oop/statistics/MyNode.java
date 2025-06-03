package hus.oop.statistics;

public class MyNode {
    private double data;
    private MyNode next;
    private MyNode previous;

    public MyNode(double data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    public MyNode(double data, MyNode next, MyNode previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public MyNode getNext() {
        return next;
    }

    public void setNext(MyNode next) {
        this.next = next;
    }

    public MyNode getPrevious() {
        return previous;
    }

    public void setPrevious(MyNode previous) {
        this.previous = previous;
    }
}

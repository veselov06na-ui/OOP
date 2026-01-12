package functions;

/**
 * Linked-list-backed implementation of AbstractTabulatedFunction using a doubly linked circular list.
 */
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    // package-private helper node class
    static class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    private Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues == null || yValues == null) {
            throw new IllegalArgumentException("xValues and yValues must not be null");
        }
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("xValues and yValues lengths differ");
        }
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (source == null) {
            throw new IllegalArgumentException("source function must not be null");
        }
        if (count < 1) {
            throw new IllegalArgumentException("count must be >= 1");
        }
        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }
        if (count == 1) {
            addNode(xFrom, source.apply(xFrom));
            return;
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            double x = xFrom + step * i;
            addNode(x, source.apply(x));
        }
    }

    private void addNode(double x, double y) {
        Node node = new Node();
        node.x = x;
        node.y = y;
        if (head == null) {
            head = node;
            node.next = node;
            node.prev = node;
        } else {
            Node last = head.prev;
            last.next = node;
            node.prev = last;
            node.next = head;
            head.prev = node;
        }
        count++;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }
        Node current;
        if (index < count / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = head.prev;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.x == x) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i < count; i++) {
            if (current.y == y) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (count == 0) {
            return 0;
        }
        if (x < head.x) {
            return 0;
        }
        Node current = head;
        int index = 0;
        while (index < count - 1 && current.next.x <= x) {
            current = current.next;
            index++;
        }
        if (index == count - 1 && x > current.x) {
            return count;
        }
        return index;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return head.y;
        }
        Node first = head;
        Node second = head.next;
        return interpolate(x, first.x, second.x, first.y, second.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return head.y;
        }
        Node last = head.prev;
        Node beforeLast = last.prev;
        return interpolate(x, beforeLast.x, last.x, beforeLast.y, last.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return head.y;
        }
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }
}

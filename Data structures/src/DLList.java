/**
 * A DLList is a list of integers. Like SLList, it also hides the terrible
 * truth of the nakedness within, but with a few additional optimizations.
 */
public class DLList<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node p, Node n) {
            prev = p;
            item = i;
            next = n;
        }

        public Node(T i) {
            item = i;
            prev = null;
            next = null;
        }
    }

    /* The first item of the list is at sentinel.next */
    private Node sentinel;
    private int size;

    /* Empty DLList */
    public DLList() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public static <T> DLList of(T... values) {
        DLList<T> list = new DLList<>();
        for (T value: values) {
            list.addLast(value);
        }
        return list;
    }

    public int size() {
        return size;
    }

    // add element in the first
    public void addFirst(T item) {
        Node newNode = new Node(item);
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        newNode.prev = sentinel;
        sentinel.next = newNode;

        size += 1;
    }

    // add element in the last
    public void addLast(T item) {
        Node newNode = new Node(item);
        sentinel.prev.next = newNode;
        newNode.prev = sentinel.next;
        newNode.next = sentinel;
        sentinel.prev = newNode;

        size += 1;
    }

    public void add(int index, T item) {
        if (index < 0) {
            index = 0;
        }
        if (index > size) {
            index = size;
        }
        Node p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        Node newNode = new Node(item);
        p.next.prev = newNode;
        newNode.next = p.next;
        newNode.prev = p;
        p.next = newNode;

        size += 1;
    }


    /* Remove item by index */
    /*
    public void remove(int index) {
        Node p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        Node markNode = p.next.next;
        p.next = markNode;
        markNode.prev = p;

        size -= 1;
    }
     */

    /* remove the first instance of the given item */
    public void remove(T item) {
        if (size == 0) {
            return;
        }
        Node p = sentinel;
        boolean foundItem = false;
        while (!foundItem && p.next != sentinel) {
            p = p.next;
            if (p.item == item) {
                foundItem = true;
                p.prev.next = p.next;
                p.next.prev = p.prev;
                size -= 1;
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        Node p = sentinel.next;
        boolean first = true;
        while (p != sentinel) {
            if (first) {
                result += p.item.toString();
                first = false;
            } else {
                result += " " + p.item.toString();
            }
            p = p.next;
        }
        return result;
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        DLList other = (DLList) o;
        if (size() != other.size()) {
            return false;
        }
        Node op = other.sentinel.next;
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            if (!(p.item.equals(op.item))) {
                return false;
            }
            op = op.next;
        }
        return true;
    }

    public static void main(String[] args) {
        DLList<Integer> myList = new DLList<>();
        myList.addLast(0);
        myList.addLast(1);
        myList.addLast(2);
        myList.add(0, 999);
        myList.addFirst(19980221);
        myList.add(5, 3);
        System.out.println(myList);
        myList.remove(100);
        System.out.println(myList);
    }
}
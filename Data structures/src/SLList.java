import java.util.Objects;

public class SLList {
    /*
    *  This file implements singly linked list, where the elements are integers
    * */

    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }

        public String toString() {
            return "IntNode{"
                    + "item="
                    + item
                    + ", next="
                    + next
                    + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IntNode intNode = (IntNode) o;
            return item == intNode.item
                    && Objects.equals(next, intNode.next);
        }
    }

    private IntNode sentinel;
    private int size;

    /*
    * Below are several constructors to construct a new SLList
    * */

    public SLList() {
        sentinel = new IntNode(19980221, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(19980221, null);
        sentinel.next = new IntNode(x, null);
    }

    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0 ; i-=1) {
            list.addFirst(values[i]);
        }
        return list;
    }

    public int size() {
        return size;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public void addLast(int x) {
        IntNode p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size += 1;
    }

    public void add(int index, int x) {
        if (index > size()) {
            index = size;
        }
        if (index < 0) {
            index = 0;
        }
        IntNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.next = new IntNode(x, p.next);
        size += 1;
    }

    public int get(int index) {
        if (index <0 || index >size) {
            System.out.println("invalid index, please check again");
            return Integer.MIN_VALUE;
        }
        IntNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next.item;
    }

    public IntNode getReference(int index) {
        if (index <0 || index >size) {
            System.out.println("invalid index, please check again");
            return null;
        }
        IntNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.next;
    }

    public IntNode getReference(int index, IntNode mark) {
        if (index <0 || index >size) {
            System.out.println("invalid index, please check again");
            return null;
        }
        IntNode p = mark;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }

    /** Reorder the linked list in reverse order. This method is destructive */
    public void reverse() {
        if (size == 0) {
            return;
        }
        IntNode mark = sentinel.next;
        sentinel.next = getReference(size - 1);
        for (int i = size - 1; i > 0; i--) {
            IntNode temp = getReference(i, mark);
            temp.next = getReference(i-1, mark);
        }
    }

    public static void main(String[] args) {
        SLList myList = SLList.of(1,2,3,4,5);
        for (int i = 0; i < myList.size; i++) {
            System.out.println(myList.get(i));
        }
        System.out.println();
        myList.add(3, 999);
        for (int i = 0; i < myList.size; i++) {
            System.out.println(myList.get(i));
        }
    }
}

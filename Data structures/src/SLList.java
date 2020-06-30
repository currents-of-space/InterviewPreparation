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

    private void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }
}

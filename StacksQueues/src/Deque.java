import java.util.Iterator;

/**
 * Created by MM on 07.02.2016.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size = 0;

    private class Node {
        private Item item;
        private Node prev, next;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size < 1;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node oldF = first;
        first = new Node();
        first.item = item;
        if (oldF != null) oldF.prev = first;
        first.next = oldF;
        if (last == null) last = first;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node oldL = last;
        last = new Node();
        last.item = item;
        if (oldL != null) oldL.next = last;
        last.prev = oldL;
        if (first == null) first = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;

        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        else first.prev = null;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        size--;

        Item item = last.item;
        last = last.prev;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException();

            Item i = current.item;
            current = current.next;
            return i;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size <= 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        items[size++] = item;
        doResizing();
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        if (size > 0) size--;
        int n = isEmpty() ? 0 : getRandom(size);

        Item item = items[n];
        if (n != size) items[n] = items[size];
        items[size] = null;
        doResizing();
        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        return size > 1 ? items[getRandom(size - 1)] : items[0];
    }

    private void doResizing() {
        int len = items.length;
        if (size == len) resize(2 * len);
        if (len > 3 && size == len / 4) resize(len / 2);
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int len = items.length > capacity ? capacity : items.length;

        for (int i = 0; i < len; i++)
            copy[i] = items[i];

        items = copy;
    }

    private int getRandom(int size) {
        return StdRandom.uniform(size);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private int sz = size;

        @Override
        public boolean hasNext() {
            return sz > 0;
        }

        @Override
        public Item next() {
            sz--;
            return sample();
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}
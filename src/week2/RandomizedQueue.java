package week2;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    private void ensureCapacity(boolean enlarge) {
        if (enlarge) {
            if (items == null) {
                items = (Item[]) new Object[1];
            }
            else if (items.length == size) {
                Item[] newItems = (Item[]) new Object[items.length * 2];
                System.arraycopy(items, 0, newItems, 0, items.length);
                items = newItems;
            }
        }
        else {
            if (items.length >= size * 4) {
                Item[] newItems = (Item[]) new Object[items.length / 2];
                System.arraycopy(items, 0, newItems, 0, size);
                items = newItems;
            }
        }
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("We cannot enqueue null into the queue");
        ensureCapacity(true);
        items[size++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0)
            throw new NoSuchElementException("Nothing to dequeue. Queue is empty!");
        ensureCapacity(false);

        int index = StdRandom.uniform(0, size);
        Item item = null;
        if (index == size - 1) {
            item = items[size - 1];
            items[size - 1] = null;
        }
        else {
            item = items[index];
            items[index] = items[size - 1];
            items[size - 1] = null;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException("Nothing to dequeue. Queue is empty!");
        int index = StdRandom.uniform(0, size);
        return items[index];
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        for (int i = 0; i < 100; i++) {
            rq.enqueue(i);
        }

        for (int i = 0; i < 100; i++) {
            rq.dequeue();
        }

        /* rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.toStringLocal());*/
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int position;
        private final int[] randomIndex;

        public RandomizedQueueIterator() {
            randomIndex = new int[size];

            for (int i = 0; i < size; i++) {
                randomIndex[i] = i;
            }

            StdRandom.shuffle(randomIndex);
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public Item next() {
            if (position >= size)
                throw new NoSuchElementException("There are no more elements");
            Item item = items[randomIndex[position++]];
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not yet supported");
        }

    }
}

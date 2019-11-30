package week2;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private boolean validateInput(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Input cannot be null");
        return true;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateInput(item);
        Node<Item> node = new Node<Item>(item);

        if (isEmpty()) {
            last = node;
            first = node;
        }
        else {
            first.prev = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        validateInput(item);
        Node<Item> node = new Node<Item>(item);

        if (isEmpty()) {
            last = node;
            first = node;
        }
        else {
            node.prev = last;
            last.next = node;
            last = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("The deque is empty");

        Item item = null;

        if (size == 1) {
            item = first.item;
            last = null;
            first = null;
        }
        else {
            Node<Item> oldFirst = first;
            item = oldFirst.item;
            first = first.next;
            oldFirst.next = null;
            first.prev = null;
        }
        size--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("The deque is empty");

        Item item = null;

        if (size == 1) {
            item = first.item;
            last = null;
            first = null;
        }
        else {
            Node<Item> oldLast = last;
            item = oldLast.item;
            last = last.prev;
            oldLast.prev = null;
            last.next = null;
        }
        size--;
        return item;
    }

    private class DequeIterator implements Iterator<Item> {

        Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException("There are no more elements");
            Item item = current.item;
            current = current.next;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not yet supported");
        }

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /* private String toStringLocal() {
        DequeIterator iterator = new DequeIterator();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (iterator.hasNext()) {
            sb.append(iterator.next() + ",");
        }
        if (sb.length() > 1)
            sb.replace(sb.length() - 1, sb.length(), "");

        sb.append("]");
        return sb.toString();
    }*/

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addFirst(8);
        dq.addFirst(7);
        dq.addFirst(6);
        dq.addFirst(5);
        dq.addFirst(4);
        dq.addFirst(3);
        dq.addFirst(2);
        dq.addFirst(1);
        // StdOut.println(dq.toStringLocal());


        dq = new Deque<>();
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);
        dq.addLast(4);
        dq.addLast(5);
        dq.addLast(6);
        dq.addLast(7);
        dq.addLast(8);

        // StdOut.println(dq.toStringLocal());

        dq.removeFirst();
        dq.removeLast();
        // StdOut.println(dq.toStringLocal());

        dq.removeFirst();
        dq.removeLast();
        // StdOut.println(dq.toStringLocal());

        dq.removeFirst();
        dq.removeLast();
        // StdOut.println(dq.toStringLocal());

        dq.removeFirst();
        dq.removeLast();
        // StdOut.println(dq.toStringLocal());

    }

}
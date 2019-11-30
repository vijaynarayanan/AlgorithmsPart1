package week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
        // StdOut.println(toStringLocal(rq));

    }

    /*private static String toStringLocal(RandomizedQueue<String> randomizedQueue) {

        Iterator<String> iterator = randomizedQueue.iterator();
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
}

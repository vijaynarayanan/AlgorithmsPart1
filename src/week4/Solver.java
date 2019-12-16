package week4;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean isSolvable = true;
    //private ArrayList<Board> seenBoards = new  ArrayList<>();
    private SearchNode goalBoard;

    private class SearchNode implements Comparable<SearchNode> {

        private final int manhattan;

        SearchNode(Board current, SearchNode prev, int moves) {
            this.current = current;
            this.prev = prev;
            this.moves = moves;
            this.manhattan = current.manhattan();
        }

        Board current;
        SearchNode prev;
        int moves;

        public int compareTo(SearchNode o) {
            int cPriority = this.manhattan + this.moves;
            int oPriority = o.manhattan + o.moves;

            if (cPriority < oPriority)
                return -1;
            else if (cPriority > oPriority)
                return 1;
            else {
                return 0;
            }
        }
    }

    public Solver(Board initial) {

        if (initial == null)
            throw new IllegalArgumentException("The inital board cannot be null");

        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();

        Board initialTwin = initial.twin();
        SearchNode node = new SearchNode(initial, null, 0);
        SearchNode twinNode = new SearchNode(initialTwin, null, 0);
        Board lastNode = null;
        Board twinLastNode = null;

        priorityQueue.insert(node);
        SearchNode next = priorityQueue.delMin();

        twinPriorityQueue.insert(twinNode);
        SearchNode twinNext = twinPriorityQueue.delMin();

        while (!next.current.isGoal() && !twinNext.current.isGoal()) {
            for (Board nBoard : next.current.neighbors()) {
                if (lastNode == null || !lastNode.equals(nBoard))
                    priorityQueue.insert(new SearchNode(nBoard, next, next.moves + 1));
            }

            for (Board nBoard : twinNext.current.neighbors()) {
                if (twinLastNode == null || !twinLastNode.equals(nBoard))
                    twinPriorityQueue.insert(new SearchNode(nBoard, twinNext, twinNext.moves + 1));
            }

            next = priorityQueue.delMin();
            twinNext = twinPriorityQueue.delMin();

            lastNode = next.prev.current;
            twinLastNode = twinNext.prev.current;
        }

        if (next.current.isGoal()) {
            goalBoard = next;
            isSolvable = true;
        }
        else {
            isSolvable = false;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return goalBoard != null ? goalBoard.moves : -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        Stack<Board> solution = new Stack<>();
        SearchNode node = goalBoard;

        if (node != null) {
            solution.push(node.current);
            while (node.prev != null) {
                solution.push(node.prev.current);
                node = node.prev;
            }
            return solution;
        }
        else {
            return null;
        }
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("puzzle3x3-28.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}

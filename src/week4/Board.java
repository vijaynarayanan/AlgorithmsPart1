package week4;/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int[] array;
    private final int rows;
    private int hammingDistance;
    private boolean hammingComputed;
    private boolean manhattanComputed;
    private int manhattanDistance;
    private int zeroPos;
    //private int[] neighboringCells;
    // private boolean neighborsComputed;
    private Board twin;
    // private final ArrayList<Board> neighbors = new ArrayList<>();
    // private int numberOfMoves;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        rows = tiles.length;
        array = new int[rows * rows];
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                if (tiles[i][j] == 0)
                    zeroPos = counter;
                array[counter++] = tiles[i][j];
            }
        }
        hamming();
        manhattan();
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(rows + "\n");
        String delimiter = " ";
        int len = array.length;
        for (int i = 1; i <= len; i++) {
            if (i % rows == 0)
                delimiter = "\n";
            sb.append(array[i - 1] + delimiter);
            delimiter = " ";
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return rows;
    }

    // number of tiles out of place
    public int hamming() {
        if (hammingComputed) return hammingDistance;
        hammingComputed = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] - 1 != i && array[i] != 0)
                hammingDistance++;
        }
        return hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattanComputed) return manhattanDistance;

        manhattanComputed = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] - 1 != i && array[i] != 0) {
                int row = getRow(array[i]);
                int col = getCol(array[i]);
                int actualRow = getRow(i + 1);
                int actualCol = getCol(i + 1);
                manhattanDistance += Math.abs(actualRow - row) + Math.abs(actualCol - col);
            }
        }
        // manhattanDistance += numberOfMoves;
        return manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        hamming();
        return hammingDistance == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null)
            return false;
        if (y instanceof Board) {
            Board other = (Board) y;
            return Arrays.equals(this.array, other.array);
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        //if (neighborsComputed) return new ArrayList<>(neighbors);
        ArrayList<Board> neighbors = new ArrayList<>();

        int[] neighboringCells = getNeighbours();
        for (int i = 0; i < neighboringCells.length; i++) {
            int[][] tiles = getNewArrayAfterSwap(zeroPos, neighboringCells[i]);
            Board n = new Board(tiles);
            // n.numberOfMoves = numberOfMoves + 1;
            neighbors.add(n);
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        if (twin != null) return twin;

        int pos1 = StdRandom.uniform(0, array.length);

        while (pos1 == zeroPos)
            pos1 = StdRandom.uniform(0, array.length);

        int pos2 = StdRandom.uniform(0, array.length);

        while (pos1 == pos2 || pos2 == zeroPos)
            pos2 = StdRandom.uniform(0, array.length);

        int[][] tiles = getNewArrayAfterSwap(pos1, pos2);
        twin = new Board(tiles);
        return twin;
    }

    // Arguments are 0 based indexes
    private int[][] getNewArrayAfterSwap(int pos1, int pos2) {
        int counter = 0;
        int[] newArray = new int[array.length];
        System.arraycopy(array, 0, newArray, 0, array.length);
        swap(newArray, pos1, pos2);

        int[][] tiles = new int[rows][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                tiles[i][j] = newArray[counter++];
            }
        }
        return tiles;
    }

    private int[] getNeighbours() {
        int[] neighboringCells;
        int counter = 0;
        int row = getRow(zeroPos + 1);
        int col = getCol(zeroPos + 1);

        int rowNumber = row % rows;
        int colNumber = col % rows;

        if (rowNumber <= 1 && colNumber <= 1) {
            neighboringCells = new int[2];
            if (colNumber == 0) {
                neighboringCells[counter++] = getIndex(row, col - 1);
            }
            else {
                neighboringCells[counter++] = getIndex(row, col + 1);
            }

            if (rowNumber == 0) {
                neighboringCells[counter++] = getIndex(row - 1, col);
            }
            else {
                neighboringCells[counter++] = getIndex(row + 1, col);
            }
        }
        else if (rowNumber <= 1 || colNumber <= 1) {
            neighboringCells = new int[3];

            if (colNumber == 0) {
                neighboringCells[counter++] = getIndex(row, col - 1);
                neighboringCells[counter++] = getIndex(row - 1, col);
                neighboringCells[counter++] = getIndex(row + 1, col);
            }
            else if (colNumber == 1) {
                neighboringCells[counter++] = getIndex(row - 1, col);
                neighboringCells[counter++] = getIndex(row, col + 1);
                neighboringCells[counter++] = getIndex(row + 1, col);
            }
            else if (rowNumber == 0) {
                neighboringCells[counter++] = getIndex(row - 1, col);
                neighboringCells[counter++] = getIndex(row, col - 1);
                neighboringCells[counter++] = getIndex(row, col + 1);
            }
            else {
                neighboringCells[counter++] = getIndex(row + 1, col);
                neighboringCells[counter++] = getIndex(row, col - 1);
                neighboringCells[counter++] = getIndex(row, col + 1);
            }

        }
        else {
            neighboringCells = new int[4];

            neighboringCells[counter++] = getIndex(row - 1, col);
            neighboringCells[counter++] = getIndex(row, col + 1);
            neighboringCells[counter++] = getIndex(row + 1, col);
            neighboringCells[counter++] = getIndex(row, col - 1);
        }

        return neighboringCells;
    }

    // Argument 1 based index. Return 0 based index
    private int getIndex(int row, int col) {
        return (row - 1) * rows + col - 1;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // Returns 1 based index
    private int getRow(int i) {
        return (i - 1) / rows + 1;
    }

    // Returns 1 based index
    private int getCol(int i) {
        int temp = i % rows;
        return temp == 0 ? rows : temp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        /*int[][] a = {
                {3,6,1},
                {0,4,8},
                {7,2,5}
        };
        Board initial = new Board(a);*/
        In in = new In("puzzle2x2-unsolvable2.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        System.out.println("Zero Pos : " + initial.zeroPos);
        for (int i = 0; i < initial.array.length; i++) {
            System.out.println(
                    "For " + initial.array[i] + " Current : (" + initial.getRow(initial.array[i])
                            + "," + initial.getCol(initial.array[i]) + ") "
                            + ", Actual : (" + initial.getRow(i + 1) + "," + initial.getCol(i + 1)
                            + ")");
        }
        System.out.println(
                "Hamming : " + initial.hamming() + "," + "Manhattan : " + initial.manhattan());
        System.out.println(initial.toString());
        for (Board nBoard : initial.neighbors()) {
            System.out.println(
                    "Hamming : " + nBoard.hamming() + "," + "Manhattan : " + nBoard.manhattan());
            System.out.println(nBoard.toString());
        }
        System.out.println("Twin : \n" + initial.twin());
    }

}

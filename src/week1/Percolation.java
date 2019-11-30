package week1;
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] sites;
    private boolean[] sitesConnectedToBottom;
    private final WeightedQuickUnionUF wq;
    private final int rowLength;
    private int numOfOpenSites;
    private final int top = 0;
    private boolean percolates;

    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n is less than 0. n: " + n);

        sites = new boolean[n * n + 1];
        sitesConnectedToBottom = new boolean[n * n + 1];
        wq = new WeightedQuickUnionUF(n * n + 1);
        rowLength = n;
        sites[top] = true;
    }

    private int getCell1D(int row, int col) {
        if (row < 1 || row > rowLength || col < 1 || col > rowLength)
            throw new IllegalArgumentException(
                    "row or col is out of range. row : " + row + ", col : " + col + ", n : "
                            + rowLength);
        return (row - 1) * rowLength + col;
    }

    public void open(int row, int col) {
        int cell = getCell1D(row, col);
        if (sites[cell])
            return;

        int cellAbove = cell <= rowLength ? -1 : cell - rowLength;
        int cellBelow = cell >= rowLength * (rowLength - 1) + 1 ? -1 : cell + rowLength;
        int cellLeft = cell % rowLength == 1 ? -1 : cell - 1;
        int cellRight = cell % rowLength == 0 ? -1 : cell + 1;

        sites[cell] = true;
        numOfOpenSites++;

        if (row == 1) {
            wq.union(top, cell);
        }
        if (row == rowLength) {
             sitesConnectedToBottom[cell] = true;
        }
        if (cellAbove != -1 && sites[cellAbove]) {
            if (sitesConnectedToBottom[ wq.find(cellAbove)]){
                sitesConnectedToBottom[cell] = true;
            }
            wq.union(cellAbove, cell);
        }
        if (cellLeft != -1 && sites[cellLeft]) {
            if (sitesConnectedToBottom[wq.find(cellLeft)]){
                sitesConnectedToBottom[cell] = true;
            }
            wq.union(cellLeft, cell);
        }
        if (cellRight != -1 && sites[cellRight]) {
            if (sitesConnectedToBottom[wq.find(cellRight)]){
                sitesConnectedToBottom[cell] = true;
            }
            wq.union(cellRight, cell);
        }
        if (cellBelow != -1 && sites[cellBelow]) {
            if(sitesConnectedToBottom[wq.find(cellBelow)]){
                sitesConnectedToBottom[cell] = true;
            }
            wq.union(cellBelow, cell);
        }

        if(sitesConnectedToBottom[wq.find(cell)] || sitesConnectedToBottom[cell] ){
            sitesConnectedToBottom[wq.find(cell)] = true;
            sitesConnectedToBottom[cell] = true;
        }
        if(wq.connected(top, cell) && sitesConnectedToBottom[cell]){
            percolates = true;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int cell = getCell1D(row, col);

        return sites[cell];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int cell = getCell1D(row, col);
        if (!sites[cell])
            return false;

        return wq.connected(cell, top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 5);
        p.open(2, 5);
        p.open(4, 2);
        p.open(3, 2);
        p.open(5, 2);
        StdOut.println(p.percolates());

        p.open(3, 3);
        p.open(3, 4);
        p.open(3, 5);
        StdOut.println(p.isFull(5, 2));
        StdOut.println(p.percolates());
    }
}

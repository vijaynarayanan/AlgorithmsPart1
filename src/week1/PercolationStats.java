package week1;
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_INTERVAL_95 = 1.96;
    private final double mean;
    private final double stddev;
    private final double loConfInterval;
    private final double highConfInterval;

    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException(
                    "Trials or n cannot be less than 0. n : " + n + ", trials : " + trials);

        double[] x = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int counter = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (p.isOpen(row, col))
                    continue;
                p.open(row, col);
                counter++;
            }
            x[i] = 1.0 * counter / (n * n);
            // StdOut.println("Trial " + i + " : " + x[i]);
        }

        mean = StdStats.mean(x);
        double sqrt = Math.sqrt(trials);

        stddev = StdStats.stddev(x);
        loConfInterval = mean - CONFIDENCE_INTERVAL_95 * stddev / sqrt;
        highConfInterval = mean + CONFIDENCE_INTERVAL_95 * stddev / sqrt;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return loConfInterval;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return highConfInterval;
    }

    //UncommentedEmptyMethodBody
    public static void main(String[] args) {
        if(args.length != 2)
            return;
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        StdOut.println("mean                    = " + p.mean);
        StdOut.println("stddev                  = " + p.stddev);
        StdOut.println(
                "95% confidence interval = [" + p.loConfInterval + "," + p.highConfInterval
                        + "]");
    }
}

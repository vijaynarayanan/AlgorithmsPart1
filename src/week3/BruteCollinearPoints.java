/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    private int numberOfSegments;

    public BruteCollinearPoints(Point[] points) {

        if (points == null)
            throw new IllegalArgumentException("Points cannot be null!");

        int N = points.length;
        for (int i = 0; i < N; i++) {
            throwExceptionWhenNull(points[i]);

            for (int j = i + 1; j < N; j++) {
                throwExceptionWhenNull(points[j]);
                double slope1 = points[i].slopeTo(points[j]);
                throwExceptionWhenNegativeInfinity(slope1);

                for (int k = j + 1; k < N; k++) {

                    throwExceptionWhenNull(points[k]);
                    double slope2 = points[i].slopeTo(points[k]);
                    throwExceptionWhenNegativeInfinity(slope2);

                    for (int l = k + 1; l < N; l++) {

                        throwExceptionWhenNull(points[l]);
                        double slope3 = points[i].slopeTo(points[l]);
                        throwExceptionWhenNegativeInfinity(slope3);

                        if (Double.compare(slope1, slope2) == 0
                                && Double.compare(slope2, slope3) == 0) {
                            Point[] tPoints = new Point[4];
                            tPoints[0] = points[i];
                            tPoints[1] = points[j];
                            tPoints[2] = points[k];
                            tPoints[3] = points[l];
                            Arrays.sort(tPoints);
                            addSegment(new LineSegment(tPoints[0], tPoints[3]));
                        }
                    }
                }
            }
        }
    }

    private boolean throwExceptionWhenNegativeInfinity(double slope) {
        if (Double.compare(slope, Double.NEGATIVE_INFINITY) == 0)
            throw new IllegalArgumentException("Duplicate points!");
        return false;
    }

    private boolean throwExceptionWhenNull(Point p) {
        if (p == null)
            throw new IllegalArgumentException("Point cannot be null!");
        return false;
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    private void addSegment(LineSegment segment) {
        if (lineSegments == null) {
            lineSegments = new LineSegment[1];
        }
        else if (lineSegments.length == numberOfSegments) {
            LineSegment[] newSegments = new LineSegment[numberOfSegments * 2];
            System.arraycopy(lineSegments, 0, newSegments, 0, numberOfSegments);
            lineSegments = newSegments;
        }
        lineSegments[numberOfSegments++] = segment;
    }

    public LineSegment[] segments() {
        LineSegment[] newSegments = new LineSegment[numberOfSegments];

        if (numberOfSegments > 0)
            System.arraycopy(lineSegments, 0, newSegments, 0, numberOfSegments);

        return newSegments;
    }

    public static void main(String[] args) {
        In in = new In("equidistant.txt");
        int N = in.readInt();
        Point[] points = new Point[N];
        int counter = 0;

        while (!in.isEmpty()) {
            points[counter++] = new Point(in.readInt(), in.readInt());
        }

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        for (LineSegment segment : bruteCollinearPoints.segments()) {
            System.out.println(segment.toString());
        }
    }
}

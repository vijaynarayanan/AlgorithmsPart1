/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> segments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new IllegalArgumentException("Points cannot be null!");
        Point[] pointsCopy = points.clone();
        int length = points.length;
        checkIfAnyNullPoints(points);

        if(points.length == 2){
            throwExceptionWhenNegativeInfinity(points[0].slopeTo(points[1]));
        }

        for (int i = 0; i < length; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {

                double slope1 = pointsCopy[p].slopeTo(pointsCopy[first]);
                double slope2 = 0.0;
                while (last < pointsCopy.length
                        && Double.compare(slope1, (slope2 = pointsCopy[p].slopeTo(pointsCopy[last]))) == 0) {
                    last++;
                }
                throwExceptionWhenNegativeInfinity(slope1);
                throwExceptionWhenNegativeInfinity(slope2);

                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                first = last;
            }
        }
    }
    public int numberOfSegments() {
        return segments.size();
    }
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
    private void checkIfAnyNullPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new IllegalArgumentException("Points cannot be null");
        }
    }


    /*public static void main(String[] args) {
        In in = new In("equidistant.txt");
        int N = in.readInt();
        Point[] points = new Point[N];
        int counter = 0;

        while (!in.isEmpty()) {
            points[counter++] = new Point(in.readInt(), in.readInt());
        }

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        for (LineSegment segment : fastCollinearPoints.segments()) {
            System.out.println(segment.toString());
        }
    }*/

    public static void main(String[] args) {
        Point[] points = new Point[2];
        points[0] = new Point(1,2);
        points[1] = new Point(1,2);
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
    }

    private boolean throwExceptionWhenNegativeInfinity(double slope) {
        if (Double.compare(slope, Double.NEGATIVE_INFINITY) == 0)
            throw new IllegalArgumentException("Duplicate points!");
        return false;
    }
}

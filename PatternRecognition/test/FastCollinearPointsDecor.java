import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mark on 2/25/16.
 */
public class FastCollinearPointsDecor {
    private List<LineSegment> lineSegments = new ArrayList<>();
    private List<Point> checked = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPointsDecor(Point[] points) {
        int n = points.length;
        double delta, slope0, slope1;
        Point pivot;
        Point[] pnts;

        List<Point> tmpPnts = new ArrayList<>();

        for (int p = 1; p < n; p++) {
            tmpPnts.clear();

            Arrays.sort(points, new CoordsOrder());
            pivot = points[p];

            Arrays.sort(points, pivot.slopeOrder());
            System.out.println();
            System.out.println("Pivot: " + PointTest.toStr(pivot));
            for (Point po : points) StdOut.print(PointTest.toStr(po) + ", ");
            System.out.println();

            int p0 = 0;
            slope0 = pivot.slopeTo(points[p0]);
            System.out.print("s" + p0 + PointTest.toStr(pivot) + "^" + PointTest.toStr(points[p0]) + "=" + pivot.slopeTo(points[p0]));

            for (int i = 1; i < n; i++) {

                if (slope0 == Double.NEGATIVE_INFINITY) {
                    p0++;
                    slope0 = pivot.slopeTo(points[p0]);
                    tmpPnts.add(pivot);
                    tmpPnts.add(points[p0]);
                    continue;
                }

                slope1 = pivot.slopeTo(points[i]);

                if (slope0 == Double.POSITIVE_INFINITY && slope1 == Double.POSITIVE_INFINITY) {
                    delta = 0.0;
                } else if (slope1 == Double.NEGATIVE_INFINITY) {
                    delta = 0.0;
                } else {
                    delta = Math.abs(slope0 - slope1);
                }
                System.out.print("; s" + i + PointTest.toStr(pivot) + "^" + PointTest.toStr(points[i]) + "=" + slope1);

                if (delta < 0.001) {
                    tmpPnts.add(points[i]);

                }
                if (delta > 0.001 || i >= n - 1) {
                    if (tmpPnts.size() > 3) {
                        pnts = tmpPnts.toArray(new Point[tmpPnts.size()]);
                        Arrays.sort(pnts, new CoordsOrder());

                        lineSegments.add(new LineSegment(pnts[0], pnts[pnts.length - 1]));
                        System.out.println("\t\tLine: " + pnts[0] + "->" + pnts[pnts.length - 1]);
                    }
                    p0 = i;
                    tmpPnts.clear();
                    tmpPnts.add(pivot);
                    tmpPnts.add(points[p0]);
                    slope0 = pivot.slopeTo(points[p0]);
                    System.out.println();
                    System.out.print("s" + p0 + PointTest.toStr(pivot) + "^" + PointTest.toStr(points[p0]) + "=" + pivot.slopeTo(points[p0]));
                }
            }

            System.out.println("\n***************Level next: " + p + "*****************");
        }
        System.out.println("done");
    }

    // the number of line segments
    public int numberOfSegments() {
        return lineSegments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    private class CoordsOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
            if (q1 == null || q2 == null) throw new java.lang.NullPointerException();
            if (q1.compareTo(q2) == 0) throw new java.lang.IllegalArgumentException();
            return q1.compareTo(q2);
        }
    }
}

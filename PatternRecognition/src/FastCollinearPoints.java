import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private static final double PRECISION = 0.00000000000000000000000000000000001;
    private List<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        double delta, slope0, slope1;
        Point pivot;
        Point[] pnts;

        List<Point> tmpPnts = new ArrayList<>();
        Point[]cpoints = new Point[n];
        System.arraycopy(points, 0, cpoints, 0, n);

        for (int p = 0; p < n; p++) {
            tmpPnts.clear();

            Arrays.sort(cpoints, new CoordsOrder());
            pivot = cpoints[p];
            int p0 = 0;

            Arrays.sort(cpoints, pivot.slopeOrder());
            slope0 = pivot.slopeTo(cpoints[p0]);

            for (int i = 1; i < n; i++) {

                if (slope0 == Double.NEGATIVE_INFINITY) {
                    p0++;
                    slope0 = pivot.slopeTo(cpoints[p0]);
                    tmpPnts.add(pivot);
                    tmpPnts.add(cpoints[p0]);
                    continue;
                }

                slope1 = pivot.slopeTo(cpoints[i]);

                if (slope0 == Double.POSITIVE_INFINITY && slope1 == Double.POSITIVE_INFINITY) {
                    delta = 0.0;
                } else if (slope1 == Double.NEGATIVE_INFINITY) {
                    delta = 0.0;
                } else {
                    delta = Math.abs(slope0 - slope1);
                }

                if (delta < PRECISION) {
                    tmpPnts.add(cpoints[i]);

                }
                if (delta > PRECISION || i >= n - 1) {
                    if (tmpPnts.size() > 3) {
                        pnts = tmpPnts.toArray(new Point[tmpPnts.size()]);
                        Arrays.sort(pnts, new CoordsOrder());
                        if (pivot == pnts[0]) lineSegments.add(new LineSegment(pnts[0], pnts[pnts.length - 1]));
                    }
                    p0 = i;
                    tmpPnts.clear();
                    tmpPnts.add(pivot);
                    tmpPnts.add(cpoints[p0]);
                    slope0 = pivot.slopeTo(cpoints[p0]);
                }
            }

        }
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
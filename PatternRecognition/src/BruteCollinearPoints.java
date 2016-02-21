import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BruteCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<LineSegment>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new java.lang.NullPointerException();
        Arrays.sort(points, new CoordsOrder());

        int n = points.length;

        double p2q, q2r, r2s;
        for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                p2q = points[p].slopeTo(points[q]);
                for (int r = q + 1; r < n - 1; r++) {
                    q2r = points[q].slopeTo(points[r]);
                    for (int s = r + 1; s < n; s++) {
                        r2s = points[r].slopeTo(points[s]);
                        if (p2q == q2r && q2r == r2s) {
//                            Point[] ps = {points[p],points[q],points[r],points[s]};
//                            Arrays.sort(ps, new CoordsOrder());
//                            lineSegments.add(new LineSegment(ps[0], ps[3]));
                            lineSegments.add(new LineSegment(points[p], points[s]));
                        }
                    }
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> lineSegments = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        double delta, slope0, slope1;

        for (int p = 0; p < n; p++) {
            Arrays.sort(points, new CoordsOrder());
            Arrays.sort(points, points[p].slopeOrder());

            int p0 = 0, ni = 1;
            System.out.print("s" + p0 + "=" + points[p].slopeTo(points[p0]));

            for (int i = 1; i < n; i++) {

                slope0 = points[p].slopeTo(points[p0]);
                slope1 = points[p].slopeTo(points[i]);
                delta = Math.abs(slope0 - slope1);
                System.out.print("; s" + i + "=" + slope1);

                if (delta < 0.001) ni++;
                if (delta > 0.001) {
                    if (ni > 3) {
                        lineSegments.add(new LineSegment(points[p], points[p0 + ni - 1]));
                        System.out.println("\t\tLine: " + p + "->" + (ni + p0 - 1));
                    }
                    ni = 1;
                    p0 = i;
                    System.out.println();
                    System.out.print("s" + p0 + "=" + points[p].slopeTo(points[p0]));
                }
            }

            System.out.println("\n***************Level next*****************");
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
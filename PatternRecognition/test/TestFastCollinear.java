import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by mark on 2/23/16.
 */
public class TestFastCollinear {

    private static void fastCollinear(Point[] points) {
        // print and draw the line segments
        FastCollinearPoints fcollinear = new FastCollinearPoints(points);

        for (LineSegment segment : fcollinear.segments()) {
            StdOut.println(segment);
            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(StdDraw.BLACK);
            segment.draw();
        }
    }

    public static void main(String[] args) {
        // read the N points from a file
        int[] x = {0, 0, 0, 0, 0, 5000, 5000, 5000, 5000, 5000,10000,10000,10000,10000,10000,15000,15000,15000,15000,15000,20000,20000,20000,20000,20000};
        int[] y = {0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000};
        int N = x.length;

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        // draw the points
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        StdDraw.show(0);
        StdDraw.setXscale(-32768, 32768);
        StdDraw.setYscale(-32768, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

//        bruteCollinear(points);
        fastCollinear(points);
    }
}

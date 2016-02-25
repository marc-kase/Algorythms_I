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
//        int scaleXmin = -32768;
//        int scaleXmax = 32768;
//        int scaleYmin = -32768;
//        int scaleYmax = 32768;

        // read the N points from a file
//        int[] x = {0, 0, 0, 0, 0, 5000, 5000, 5000, 5000, 5000,10000,10000,10000,10000,10000,15000,15000,15000,15000,15000,20000,20000,20000,20000,20000};
//        int[] y = {0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000, 0, 5000,10000,15000,20000};

        int scaleXmin = -0;
        int scaleXmax = 5;
        int scaleYmin = -0;
        int scaleYmax = 5;

        int s = 4;
        int n = s * s;

        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                x[i * s + j] = j + 1;
                y[i * s + j] = i + 1;
            }
        }

        int N = x.length;

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(x[i], y[i]);
        }

        // draw the points
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);
        StdDraw.show(0);
        StdDraw.setXscale(scaleXmin, scaleXmax);
        StdDraw.setYscale(scaleYmin, scaleYmax);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

//        bruteCollinear(points);
        fastCollinear(points);
    }
}

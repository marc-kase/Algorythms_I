import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SampleClient {

    private static void bruteCollinear(Point[] points) {
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(StdDraw.BLACK);
            segment.draw();
        }
    }

    private static void fastCollinear(Point[] points) {
        // print and draw the line segments
        FastCollinearPoints fcollinear = new FastCollinearPoints(points);
        for (LineSegment segment : fcollinear.segments()) {
            StdOut.println(segment);
//            StdDraw.setPenRadius(0.001);
            StdDraw.setPenColor(StdDraw.BLACK);
            segment.draw();
        }
    }

    public static void main(String[] args) {

        // read the N points from a file
        String filename = "rs1423.txt";
//        String filename = "input200.txt";
        Path f = Paths.get(args[0], filename);
        In in = new In(f.toString());
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.setPenColor(StdDraw.RED);
//        StdDraw.setPenRadius(0.01);
        StdDraw.show(0);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

//        bruteCollinear(points);
        fastCollinear(points);


    }
}

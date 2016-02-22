import java.util.Arrays;

public class FastCollinearPoints {

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        int n = points.length;
        double[] slope = new double[n];


        for (int p=0; p<n; p++) {
            Arrays.sort(points, points[p].slopeOrder());
            for (int i=0; i<n; i++) {
                slope[i] = points[p].slopeTo(points[i]);

            }
            System.out.println("done");
        }
    }

    // the number of line segments
    public int numberOfSegments() {return 0;}

    // the line segments
    public LineSegment[] segments() {return null;}
}
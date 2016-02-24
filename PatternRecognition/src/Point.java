/**
 * ***************************************************************************
 * Compilation:  javac Point.java
 * Execution:    java Point
 * Dependencies: none
 * <p>
 * An immutable data type for points in the plane.
 * For use on Coursera, Algorithms Part I programming assignment.
 * ****************************************************************************
 */

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (this.y == that.y && this.x == that.x) return 0;
        else if (this.y == that.y && this.x < that.x) return -1;
        else if (this.y < that.y) return -1;
        else return 1;
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) throw new java.lang.NullPointerException();
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        else if (this.x == 0 && that.x == 0) return Double.POSITIVE_INFINITY;
        else if (this.y == 0 && that.y == 0) return +0.0;
        else {
            double s = (double) (that.y - this.y) / (that.x - this.x);
            return Math.abs(s) < 0.001 ? +0.0 : s;
        }
    }


    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new PolarOrder();
    }

    private class PolarOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2) {
/*            if (q1 == Point.this || q2 == Point.this) return 0;

            if (q1.x > Point.this.x && q2.x < Point.this.x) return -1;
            if (q1.x < Point.this.x && q2.x > Point.this.x) return 1;
            if (q1.y > Point.this.y && q2.y < Point.this.y) return -1;
            if (q1.y < Point.this.y && q2.y > Point.this.y) return 1;*/

            double s1 = Point.this.slopeTo(q1);
            double s2 = Point.this.slopeTo(q2);

            if (s1 == s2 || Math.abs(s1 - s2) < 0.001) return 0;
            else if (s1 < s2) return -1;
            else return 1;
        }
    }

    private static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) return -1; // clockwise
        else if (area2 > 0) return +1; // counter-clockwise
        else return 0; // collinear
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}

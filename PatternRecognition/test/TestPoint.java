import java.util.Date;

/**
 * Created by MM on 22.02.2016.
 */
public class TestPoint extends Point{
    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public TestPoint(int x, int y) {
        super(x, y);
    }

    public int compare(Point q1, Point q2) {
        double s1 = TestPoint.this.slopeTo(q1);
        double s2 = TestPoint.this.slopeTo(q2);

        double precision = 0.0000000001;
        if (s1 == s2 || Math.abs(s1 - s2) < precision) return 0;
        else if (s1 < s2) return -1;
        else return 1;
    }

    public static void reflexive() {
        TestPoint q = new TestPoint(402, 445);
        TestPoint r = new TestPoint(274, 261);
        TestPoint p = new TestPoint(471, 438);

        System.out.println(p.compare(q,r));
        System.out.println(p.compare(r,q));
    }

    public static void main(String[] args) {
        System.out.println(new Date(1456250400000L));
//        reflexive();
    }
}

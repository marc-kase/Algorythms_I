import java.lang.reflect.Field;

/**
 * Created by mark on 2/25/16.
 */
public class PointTest extends Point {
    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public PointTest(int x, int y) {
        super(x, y);
    }

    public static String toStr(Point p) {
        Class point = Point.class;
        Field fx, fy;
        try {
            fx = point.getDeclaredField("x");
            fy = point.getDeclaredField("y");

            int x = (int) fx.get(p);
            int y = (int) fy.get(p);

            return "(" + x + "," + y + ")";

        } catch (NoSuchFieldException | IllegalAccessException e) {
//            e.printStackTrace();
            return "";
        }
    }

    public int compare(Point q1, Point q2) {
        double s1 = PointTest.this.slopeTo(q1);
        double s2 = PointTest.this.slopeTo(q2);

        double precision = 0.0000000001;
        if (s1 == s2 || Math.abs(s1 - s2) < precision) return 0;
        else if (s1 < s2) return -1;
        else return 1;
    }

    public static void reflexive() {
        PointTest q = new PointTest(402, 445);
        PointTest r = new PointTest(274, 261);
        PointTest p = new PointTest(471, 438);

        System.out.println(p.compare(q,r));
        System.out.println(p.compare(r,q));
    }
}

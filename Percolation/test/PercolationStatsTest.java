import org.junit.Test;

/**
 * Created by mark on 1/29/16.
 */
public class PercolationStatsTest {
    public PercolationStatsTest() {
    }

    @Test
    public void handyTest() {
        PercolationStatsDecor p = new PercolationStatsDecor(64, 150);
        System.out.println(p.toString());
    }
}

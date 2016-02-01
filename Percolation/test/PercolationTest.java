import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationTest {
    private PercolationDecor percolateIt(int N) {
        long t1 = System.currentTimeMillis();

        PercolationDecor p = new PercolationDecor(N);
        while (!p.percolates()) {
            p.open(uniform(1, p.getN() + 1), uniform(1, p.getN() + 1));
        }

        p.time = System.currentTimeMillis() - t1;
        return p;
    }

    @Ignore
    @Test
    public void handyTest() {
        System.out.println("Handy Test...");

        PercolationDecor p = new PercolationDecor(3);
        System.out.println(p.toString());
        System.out.println();

        System.out.println(p.isOpen(1,1));

/*        System.out.println();
        p.open(1, 4);
        System.out.println(p.toString());
        System.out.println("Full: " + p.isFull(1,4));
        System.out.println("Percolates: " + p.percolates());*/

        p.open(3, 1);
        System.out.println(p.toString());
        System.out.println("Full: " + p.isFull(1,1));
//        System.out.println("Percolates: " + p.percolates());

        System.out.println();
        p.open(3, 2);
        System.out.println(p.toString());
        System.out.println("Full: " + p.isFull(1,2));
//        System.out.println("Percolates: " + p.percolates());

        System.out.println();
        p.open(3, 3);
        System.out.println(p.toString());
        System.out.println("Full: " + p.isFull(1,3));
        System.out.println("Percolates: " + p.percolates());

        Assert.assertEquals(p.percolates(), true);
    }

    @Ignore
    @Test
    public void randomTest() {
        StdRandom.getSeed();
        System.out.println();
        System.out.println("Start loop...");
        PercolationDecor p = percolateIt(4);
        System.out.println(p.toString());
    }

//    @Ignore
    @Test
    public void multitest() {
        int n = 5;
        for (int i = 1; i < 6; i++) {
            n *= i;
            System.out.print(n + "; ");
            PercolationDecor p = percolateIt(n);
//            System.out.println(p.time + "; " + p.getThreashold());
        }


    }
}

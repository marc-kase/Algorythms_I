import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean = 0;
    private double stddev = 0;
    private double confLo = 0;
    private double confHi = 0;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();

        double[] th = new double[T];
        double N2 = N * N;
        int openSites;

        for (int i = 0; i < T; i++) {
            Percolation p = percolateIt(N);
            openSites = calcOpened(p, N);
            th[i] = openSites / N2;
//            System.out.println(th[i]);
        }

        mean = StdStats.mean(th);
        stddev = StdStats.stddev(th);
        double delta = 1.96 * stddev / Math.sqrt(T);

        confLo = mean - delta;
        confHi = mean + delta;
    }

    private int calcOpened(Percolation p, int N) {
        int openSites = 0;
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                if (p.isOpen(i, j)) openSites++;
            }
        }
        return openSites;
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return confLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return confHi;
    }

    private Percolation percolateIt(int N) {
        Percolation p = new Percolation(N);
        while (!p.percolates()) {
            p.open(StdRandom.uniform(1, N + 1), StdRandom.uniform(1, N + 1));
        }
        return p;
    }

    // test client (described below)
    public static void main(String[] args) {
    }
}

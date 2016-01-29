import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    protected double mean = 0;
    protected double stddev = 0;
    protected double confLo = 0;
    protected double confHi = 0;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();

        double[] th = new double[T];
        double N2 = N * N;

        for (int i = 0; i < T; i++) {
            Percolation p = percolateIt(N);
            th[i] = p.getOpenSites() / N2;
            System.out.println(th[i]);
        }

        double sqT = Math.sqrt(T);
        mean = StdStats.mean(th);
        stddev = StdStats.stddev(th);
        confLo = mean - 1.96 * stddev / sqT;
        confHi = mean + 1.96 * stddev / sqT;
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

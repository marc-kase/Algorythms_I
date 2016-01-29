import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    public enum Status {
        CLOSED, OPENED
    }

    protected final int N;
    protected final int V;
    protected final int virtTop;
    protected final int virtBot;
    protected int openSites = 0;
    protected int[] map;
    private QuickFindUF qf;

    static {
        StdRandom.setSeed(10000L);
    }

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new java.lang.IllegalArgumentException();

        this.N = N;
        V = N * N;
        virtTop = V;
        virtBot = V + 1;

        map = new int[V];
        qf = new QuickFindUF(V + 2);
        init(N);
    }

    private void init(int N) {
        int n;
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                n = convert(x, y);
//                map[n] = 0;
                map[n] = StdRandom.uniform(2);
            }
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        int n = convert(i, j);
        if (map[n] == Status.CLOSED.ordinal()) {
            map[n] = Status.OPENED.ordinal();
            if (j == 1 && i >= 0 && i <= N) union(n, virtTop);
            if (j == N && i >= 0 && i <= N) union(n, virtBot);
            openSites++;
            connectSides(i, j);
        }
    }

    public void union(int p, int q) {
        qf.union(p, q);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        return map[convert(i, j)] != 0;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return isConnected(convert(i, j), virtTop);
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(virtBot, virtTop);
    }

    public int convert(int i, int j) {
        return (j - 1) * N + i - 1;
    }

    public void connectLeft(int i, int j) {
        if (i > 1) {
            int l = convert(i - 1, j);
            if (l < 0) return;
            if (!isOpen(i - 1, j)) {
                union(l, convert(i, j));
            }
        }
    }

    public void connectRigth(int i, int j) {
        if (i < N) {
            int r = convert(i + 1, j);
            if (r < 0) return;
            if (isOpen(i + 1, j)) {
                union(r, convert(i, j));
            }
        }
    }

    public void connectTop(int i, int j) {
        if (j > 1) {
            int t = convert(i, j - 1);
            if (t < 0) return;
            if (isOpen(i, j - 1)) {
                union(t, convert(i, j));
            }
        }
    }

    public void connectBottom(int i, int j) {
        if (j < N) {
            int b = convert(i, j + 1);
            if (b < 0) return;
            if (isOpen(i, j + 1)) {
                union(b, convert(i, j));
            }
        }
    }

    public void connectSides(int i, int j) {
        connectTop(i, j);
        connectBottom(i, j);
        connectLeft(i, j);
        connectRigth(i, j);
    }

    public boolean isConnected(int p, int q) {
        return qf.connected(p, q);
    }

    public int getOpenSites() {
        return openSites;
    }

    public double getThreashold() {
        return (double) openSites / (N * N);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}

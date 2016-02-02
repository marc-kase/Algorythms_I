import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private enum Status {
        CLOSED, OPENED
    }

    private final int num;
    private final int virtTop;
    private final int virtBot;
    private int lastLineStart;
    private int[] borderShiftX = {0, 1, 0, -1};
    private int[] borderShiftY = {-1, 0, 1, 0};
    private int[] map;
    private int[] botm;
    private WeightedQuickUnionUF qf;


    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0) throw new java.lang.IllegalArgumentException();

        this.num = N;
        int v = N * N;
        virtTop = v;
        virtBot = v + 1;
        lastLineStart = v - N - 1;

        map = new int[v];
        botm = new int[v];
        qf = new WeightedQuickUnionUF(v + 2);
        init(num);
    }

    private void init(int numN) {
        StdRandom.setSeed(10000L);

        int n;
        for (int y = 1; y <= numN; y++) {
            for (int x = 1; x <= numN; x++) {
                n = grid2line(x, y);
                map[n] = 0;
                botm[n] = -1;
            }
        }
    }

    private boolean outOfBounds(int i, int j) {
        return (i <= 0 || i > num || j <= 0 || j > num);
    }

    private void checkLimit(int i, int j) {
        if (outOfBounds(i, j))
            throw new IndexOutOfBoundsException();
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        checkLimit(i, j);
        int n = grid2line(i, j);
        if (map[n] == Status.CLOSED.ordinal()) {
            map[n] = Status.OPENED.ordinal();
            if (i == 1 && j > 0 && j <= num) union(n, virtTop);
            if (isBottom(n) && isFull(i, j)) union(n, virtBot);

            connectSides(i, j);
            int b = botm[qf.find(n)];
//            System.out.println("p = " + n + "; root = " + qf.find(n) + "; bot = " + b);
            if (b != -1) {
                if (isConnected(b, virtTop)) union(b, virtBot);
//                System.out.println(n + " - " + isConnected(b, virtTop));
            }
        }
    }

    private void union(int p, int q) {
        qf.union(p, q);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkLimit(i, j);
        return map[grid2line(i, j)] != 0;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkLimit(i, j);
        return isConnected(grid2line(i, j), virtTop);
    }

    // does the system percolate?
    public boolean percolates() {
        return qf.connected(virtBot, virtTop);
    }

    private int grid2line(int i, int j) {
        return (i - 1) * num + j - 1;
    }

    private void connectSides(int i, int j) {
        int p = grid2line(i, j);
        int q, x, y;
        for (int k = 0; k < 4; k++) {
            x = i + borderShiftX[k];
            y = j + borderShiftY[k];
            q = grid2line(x, y);
            if (outOfBounds(x, y)) continue;
            if (isOpen(x, y)) {
                if (isBottom(q)) registerBottom(p, q);
                else if (isBottom(p)) registerBottom(q, p);
                else {
                    moveBottom(p, q);
                }
                union(p, q);
            }
        }
    }

    private boolean isBottom(int p) {
        return p > lastLineStart;
    }

    private void registerBottom(int p, int q) {
        botm[qf.find(p)] = q;
    }

    private void moveBottom(int p, int q) {
        int b1 = botm[qf.find(p)];
        int b2 = botm[qf.find(q)];

        if (b1 != -1) {
            registerBottom(p, b1);
            registerBottom(q, b1);
        } else if (b2 != -1) {
            registerBottom(p, b2);
            registerBottom(q, b2);
        }
    }

    private boolean isConnected(int p, int q) {
        return qf.connected(p, q);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}

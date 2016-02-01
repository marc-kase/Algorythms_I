/**
 * Created by mark on 1/28/16.
 */
public class PercolationDecor extends Percolation {
    public long time = 0;
    private int N;

    public PercolationDecor(int N) {
        super(N);
        this.N = N;
    }

    public PercolationDecor() {
        super(3);
    }

    public int getN() {
        return N;
    }

    @Override
    public String toString() {
        java.text.DecimalFormat format = new java.text.DecimalFormat("###");
        StringBuilder sb = new StringBuilder();

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
//                sb.append(format.format(map[convert(x, y)])).append("\t");
                sb.append(format.format(isOpen(x, y) ? 1 : 0)).append("\t");
//                sb.append(format.format(convert(x, y))).append("\t");
                if (x == N) sb.append("\n");
            }
        }
        return sb.toString();
    }
}

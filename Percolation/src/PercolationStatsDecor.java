/**
 * Created by mark on 1/29/16.
 */
public class PercolationStatsDecor extends PercolationStats {
    private final int N,T;

    public PercolationStatsDecor(int N, int T) {
        super(N, T);
        this.N = N;
        this.T = T;
    }

    @Override
    public String toString() {
        java.text.DecimalFormat format = new java.text.DecimalFormat("###");

        return "% java PercolationStats " + N + " " + T + "\n"
                + "mean     :\t\t\t\t" + super.mean() + "\n"
                + "stddev   :\t\t\t\t" + super.stddev() + "\n"
                + "95% coinf:\t\t\t\t" + super.confidenceLo() + ", " + confidenceHi() + "\n";
    }
}

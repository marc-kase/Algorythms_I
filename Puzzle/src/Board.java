import java.util.Arrays;

public class Board {

    public int[][] blocks;
    private int n, num;
    private int state = 0;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;
        n = blocks.length;
        num = n * n;

        findBlank();
    }

    private int findBlank() {
        int[] c;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            if (blocks[c[0]][c[1]] == num) {
                return i;
            }
        }
        return -1;
    }

    // board dimension N
    public int dimension() {
        return n;
    }

    // number of blocks out of place
    public int hamming() {
        int[] c;
        int h = 0;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            if (blocks[c[0]][c[1]] != i && blocks[c[0]][c[1]] != num) h++;
        }
        return h;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int[] c, goal;
        int m = 0;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            if (blocks[c[0]][c[1]] != i && blocks[c[0]][c[1]] != num) {
                goal = line2grid(blocks[c[0]][c[1]]);
                int dx = Math.abs(goal[0] - c[0]);
                int dy = Math.abs(goal[1] - c[1]);
                m += (dx + dy);
            }
        }
        return m;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int[] c;
        int[][] g = new int[n][n];

        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            g[c[0]][c[1]] = i;
        }

        return equals(new Board(g));
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[] s;

        int[] b = line2grid(findBlank());
        int i = b[0];
        int j = b[1];

        int[][] twins = new int[n][n];
        for (int k = 1; k < num + 1; k++) {
            int[] c = line2grid(k);
            twins[c[0]][c[1]] = blocks[c[0]][c[1]];
        }

        boolean left, right, up, dw;
        s = nextState();
        right = i + s[0] < n;
        left = i + s[0] > -1;
        up = j + s[1] < n;
        dw = j + s[1] > -1;

        if (!(left && right && up && dw)) return null;

        int helper = twins[i][j];
        twins[i][j] = twins[i + s[0]][j + s[1]];
        twins[i + s[0]][j + s[1]] = helper;

        return new Board(twins);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.deepEquals(this.blocks, that.blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String res = blocks[0][0] + " " + blocks[1][0] + " " + blocks[2][0] + "\n";
        res += blocks[0][1] + " " + blocks[1][1] + " " + blocks[2][1] + "\n";
        res += blocks[0][2] + " " + blocks[1][2] + " " + blocks[2][2];
        return res;
    }

    private int grid2line(int i, int j) {
        return i * n + j;
    }

    private int[] line2grid(int line) {
        int j = (line - 1) / n;
        int i = line - 1 - j * n;
        return new int[]{i, j};
    }

    private int[] nextState() {
        state++;
        if (state > 4) state = 1;

        int[] c;
        switch (state) {
            case 1:
                c = new int[]{0, -1};
                break;
            case 2:
                c = new int[]{1, 0};
                break;
            case 3:
                c = new int[]{0, 1};
                break;
            case 4:
                c = new int[]{-1, 0};
                break;
            default:
                c = new int[]{0, 0};
                break;
        }
        return c;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}

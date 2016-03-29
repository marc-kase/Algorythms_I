import java.util.*;

public class Board {

    public final int[][] blocks;
    private List<Board> neighbors = new ArrayList<>();
    private int n, num, blank = 0;
    private int state = 0;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) throw new java.lang.NullPointerException();

        this.blocks = blocks;
        n = blocks.length;
        num = n * n;

        findBlank();
    }

    private int findBlank() {
        int[] c;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            if (blocks[c[1]][c[0]] == blank) {
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
            if (blocks[c[1]][c[0]] != i && blocks[c[1]][c[0]] != blank) h++;
        }
        return h;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int[] c, goal;
        int m = 0;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            if (blocks[c[1]][c[0]] != i && blocks[c[1]][c[0]] != blank) {
                goal = line2grid(blocks[c[1]][c[0]]);
                int dx = Math.abs(goal[1] - c[1]);
                int dy = Math.abs(goal[0] - c[0]);
                m += (dx + dy);
            }
        }
        return m;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[] b = line2grid(findBlank());

        Board twin = null;
        int dirs = 0;
        while (twin == null && dirs++ < 4) {
            twin = createTwin(b, nextState());
        }

        return twin;
    }

    private Board createTwin(int[] blankXY, int[] shiftXY) {

        int i = blankXY[1];
        int j = blankXY[0];

        boolean right = i + shiftXY[1] < n;
        boolean left = i + shiftXY[1] >= 0;
        boolean up = j + shiftXY[0] < n;
        boolean dw = j + shiftXY[0] >= 0;
        boolean isInside = left && right && up && dw;

        if (!isInside) return null;

        int[][] twins = new int[n][n];
        for (int k = 1; k < num + 1; k++) {
            int[] c = line2grid(k);
            twins[c[1]][c[0]] = blocks[c[1]][c[0]];
        }

        int helper = twins[i][j];
        twins[i][j] = twins[i + shiftXY[1]][j + shiftXY[0]];
        twins[i + shiftXY[1]][j + shiftXY[0]] = helper;

        return new Board(twins);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return this == that || Arrays.deepEquals(this.blocks, that.blocks);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                int[] b = line2grid(findBlank());

                Board twin;
                for (int i = 0; i < 4; i++) {
                    twin = createTwin(b, nextState());
                    if (twin != null) neighbors.add(twin);
                }
                return neighbors.listIterator();
            }
        };
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        String res = "M: " + manhattan() + " H: " + hamming() + "\n";
        int[] c;
        for (int i = 1; i < num + 1; i++) {
            c = line2grid(i);
            res += "\t" + blocks[c[1]][c[0]];
            if (i % n == 0) res += "\n";
        }
        return res;
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
            case 1:
                c = new int[]{0, -1};
                break;
        }
        return c;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}

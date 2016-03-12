import edu.princeton.cs.algs4.StdOut;

public class TestSolver extends Solver {
    public static int moves = 0;

    public TestSolver(Board initial) {
        super(initial);
    }

    public void testBoard(Board initial) {
        System.out.println("Manhattan: " + initial.manhattan());
        System.out.println("Hamming: " + (initial.hamming() + moves));
    }

    public static void main(String[] args) {

        // create initial board from file
        int N = 3;
        int[][] blocks = new int[N][N];
        blocks[0][0] = N * N;
        blocks[1][0] = 1;
        blocks[2][0] = 3;

        blocks[0][1] = 4;
        blocks[1][1] = 2;
        blocks[2][1] = 5;

        blocks[0][2] = 7;
        blocks[1][2] = 8;
        blocks[2][2] = 6;

/*        blocks[0][0] = 8;
        blocks[1][0] = 1;
        blocks[2][0] = 3;

        blocks[0][1] = 4;
        blocks[1][1] = N * N;
        blocks[2][1] = 2;

        blocks[0][2] = 7;
        blocks[1][2] = 6;
        blocks[2][2] = 5;*/

        Board initial = new Board(blocks);

        // solve the puzzle
        TestSolver solver = new TestSolver(initial);
//        solver.testBoard(initial);
//        System.out.println(initial.toString());

//        moves++;

//        Board b3 = initial.twin2();
//        solver.testBoard(b3);
//        System.out.println(b3.toString());

//        Board b2 = initial.twin();
//        solver.testBoard(b2);
//        System.out.println(b2.toString());

//        System.out.println("Is a goal: " + b2.isGoal());

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}

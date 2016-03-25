import edu.princeton.cs.algs4.*;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Client {
    public static void main(String[] args) {
//        String filename = "puzzle3x3-08.txt";
        String filename = "puzzle3x3-25.txt";
//        String filename = "puzzle4x4-31.txt";
//        String filename = "puzzle04.txt";
        Path f = Paths.get(args[0], filename);

        // create initial board from file
        In in = new In(f.toFile());
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board.toString());
        }
    }
}

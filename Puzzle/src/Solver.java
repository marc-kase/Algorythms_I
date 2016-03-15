import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solver {

    private SearchNode first, solution = null;
    private int moves = 0;
    private boolean isGoal = false;
    private MinPQ<SearchNode> pq = new MinPQ<>();
    private List<Board> solutions = new ArrayList<>();

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;
        private int priority;

        public SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            priority = board.manhattan() + moves;

            System.out.println(board.toString() + "H:" + board.hamming() + " M:" + board.manhattan() + "\n");//todo test
        }

        @Override
        public int compareTo(SearchNode o) {
            if (o == null) return 1;
            return this.priority - o.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        isGoal = false;
        first = new SearchNode(initial, null, 0);
        pq.insert(first);

        SearchNode sn;
        SearchNode previousSN = pq.delMin();

        while (!isGoal) {
            moves++;

            System.out.println("Moves : " + moves + " =====================================");//todo test
            int k = 0;
            do {
                Board twin = previousSN.board.twin();
                if (twin != null) {
                    isGoal = twin.isGoal();
                    if (isUnique(twin, previousSN)) {
                        sn = new SearchNode(twin, previousSN, moves);
                        pq.insert(sn);
                    }

                    if (isGoal) {
                        solutions.add(twin);
                        k = 3;
                    }
                }
            } while (k++ < 3);
            if (!isGoal) {
                previousSN = pq.delMin();
                solutions.add(previousSN.board);

                System.out.println(" * * * * * \nH:" + previousSN.board.hamming() + " M:" + previousSN.board.manhattan()
                        + "\n" + (previousSN.board.toString() + "\n * * * * * \n"));
                System.out.println();
            }
        }

        System.out.println("Result: \n" + solutions.get(solutions.size() - 1).toString() + "\nmoves: " + moves());//todo test

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isGoal;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return new Iterable<Board>() {
            @Override
            public Iterator<Board> iterator() {
                return solutions.listIterator();
            }
        };
    }

    private boolean isUnique(Board board, SearchNode snode) {
        if (snode == null) return true;
        if (moves == 0) return true;
        if (board.equals(snode.board)) return false;
        if (board.equals(first.board)) return false;

        SearchNode node = snode.previous;
        if (node == null) return true;

        while (node != null) {
            Iterable<Board> it = node.board.neighbors();
            for (Board n : it) {
                if (board.equals(n)) return false;
            }
            node = node.previous;
        }
        return true;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }
}
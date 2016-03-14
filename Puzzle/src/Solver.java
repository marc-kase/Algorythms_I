import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private SearchNode first, solution = null;
    private int moves = 0;

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

            System.out.println(board.toString());
        }

        @Override
        public int compareTo(SearchNode o) {
            if (o == null) return 1;
            return this.priority - o.priority;
        }
    }

    private MinPQ<SearchNode> pq = new MinPQ<>();

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        boolean isGoal = false;
        first = new SearchNode(initial, null, 0);
        pq.insert(first);

        Board currBoard;
        SearchNode previousSN = pq.delMin();

        while (!isGoal) {
            moves++;
            currBoard = previousSN.board;
            System.out.println("Move : " + moves);//todo test

            int k = 0;
            do {
                System.out.println(k + ".");//todo test
                Board twin = currBoard.twin();
                if (twin != null) {
                    isGoal = twin.isGoal();
                    SearchNode sn = new SearchNode(twin, previousSN, moves);
                    if (!sn.board.equals(previousSN))
                        pq.insert(sn);
                    System.out.println("Manhattan: " + sn.board.manhattan());//todo test

                    if (isGoal) {
                        solution = new SearchNode(twin, previousSN, moves);
                        k = 4;
                    }
                }
            } while (k++ < 4);
            if (!isGoal) previousSN = pq.delMin();
        }

        System.out.println("Result: \n" + solution.board.toString() + "\nmoves: " + moves());//todo test

    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }
}
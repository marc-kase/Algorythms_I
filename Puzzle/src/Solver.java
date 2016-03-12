import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private SearchNode first, solution;
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
        first = new SearchNode(initial, null, 0);
        pq.insert(first);
        pq.delMin();


        SearchNode n1 = new SearchNode(initial.twin(), first, 1);
        pq.insert(n1);

        SearchNode n2 = new SearchNode(initial.twin(), first, 1);
        pq.insert(n2);

        pq.delMin();
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return 0;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return null;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {}
}
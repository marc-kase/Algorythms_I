import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solver {

    private SearchNode first;
    private boolean isSolvable = false;
    private SearchNode solution = null;
    private List<Board> solutions = new ArrayList<>();

    private static class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;
        private int manhattan;
        private int hamming;
        private int priority;

        public SearchNode(Board board, SearchNode previous, int moves) {
            this.board = board;
            this.previous = previous;
            this.moves = moves;
            manhattan = board.manhattan();
            hamming = board.hamming();
            priority = manhattan + moves;
        }

        @Override
        public int compareTo(SearchNode o) {
            if (o == null) return 1;
            int p = this.priority - o.priority;
            if (p == 0) return o.hamming - this.hamming;
//            int p = this.manhattan - o.manhattan;
//            if (p == 0) p = this.hamming - o.hamming;
//            int p = o.manhattan - this.manhattan;
            return p;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new java.lang.NullPointerException();

        boolean isGoal = false;
        first = new SearchNode(initial, null, 0);
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(first);

        SearchNode previousSN = pq.delMin();

        while (!isGoal) {
            addNeighbours(pq, previousSN);

            if (pq.size() != 0) {
                previousSN = pq.delMin();
            } else {
                isSolvable = false;
                isGoal = true;
            }

            if (previousSN.board.isGoal()) {
                isGoal = true;
                isSolvable = true;
                solutions.add(previousSN.board);
                solution = previousSN;
            } else {
                isSolvable = false;
                solutions.add(previousSN.board);
/*                System.out.println(" * * * * * \nH:" + previousSN.board.hamming() + " M:" + previousSN.board.manhattan()
                        + " m: " + (previousSN.moves) + "\n" + (previousSN.board.toString() + "\n * * * * * \n"));
                System.out.println();*/
            }
        }

/*        if (isSolvable) {
            System.out.println("Result: \n" + solutions.get(solutions.size() - 1).toString() + "\nmoves: " + moves());//todo test
        } else {
            System.out.println("Unsolvable");
        }*/

    }

    private void addNeighbours(MinPQ<SearchNode> pq, SearchNode previousSN) {
        Iterator<Board> it = previousSN.board.neighbors().iterator();
        while (it.hasNext()) {
            Board node = it.next();
            if (isUnique(node, previousSN)) {
                pq.insert(new SearchNode(node, previousSN, previousSN.moves + 1));


                if ((previousSN.moves + 1) % 5 == 0) System.out.println("Move: " + (previousSN.moves + 1));
/*                    System.out.println(node.toString() + "H:" + node.hamming() + " M:" + node.manhattan() + " m: "
                        + (previousSN.moves + 1) + "\n");//todo test*/
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of circles to solve initial board; -1 if unsolvable
    public int moves() {
        return solution != null ? solution.moves : -1;
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
        if (snode.moves == 0) return true;
        if (board.equals(snode.board)) return false;
        if (board.equals(first.board)) return false;

        SearchNode sn = snode;
        while (sn.previous != null) {
            sn = sn.previous;
            Iterable<Board> it = sn.board.neighbors();
            for (Board n : it) {
                if (board.equals(n)) return false;
            }
        }
        return true;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }
}
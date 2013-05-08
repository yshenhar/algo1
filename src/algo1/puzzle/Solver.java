package algo1.puzzle;

import algs4.BoundedMinPQ;
import algs4.MinPQ;
import algs4.Stack;
import stdlib.In;
import stdlib.StdOut;

import java.util.Comparator;

public class Solver {
  private SearchNode result;

  private static class BoardSolver {
    private SearchNode currentNode;
    MinPQ<SearchNode> pq;

    public BoardSolver(Board initial) {
//      pq = new BoundedMinPQ<SearchNode>(1000, 20000);
      pq = new MinPQ<SearchNode>();
      currentNode = new SearchNode(initial, null);
      pq.insert(currentNode);
    }

    public SearchNode currentNode() {
      return currentNode;
    }

    public boolean solved() {
      return currentNode.board.isGoal();
    }

    public int queueSize() {
      return pq.size();
    }

    public void step() {
      if (!solved() && !pq.isEmpty()) {
        SearchNode node = pq.delMin();
        if (!node.board.isGoal()) {
          for (Board b : node.board.neighbors()) {
            if (currentNode.prev == null || !b.equals(node.prev.board))
              pq.insert(new SearchNode(b, node));
          }
        }
        currentNode = node;
      }
    }
  }

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    BoardSolver solver = new BoardSolver(initial);
    BoardSolver twinSolver = new BoardSolver(initial.twin());

    while (true) {
      solver.step();
//      System.out.println("current node \n" + solver.currentNode().board +
//          "with moves " + solver.currentNode().moves +
//          " score " + solver.currentNode().board.manhattan() +
//          " with " + solver.queueSize() + " boards in pq\n");
      if (solver.solved()) {
        result = solver.currentNode();
        break;
      }
      twinSolver.step();
      if (twinSolver.solved()) {
        result = null;
        break;
      }
    }
  }

  // is the initial board solvable?
  public boolean isSolvable()  {return result != null;}
  // min number of moves to solve initial board; -1 if no solution
  public int moves() {return isSolvable() ? result.moves : -1;}

  // sequence of boards in a shortest solution; null if no solution
  public Iterable<Board> solution() {
    Stack<Board> path = new Stack<Board>();
    SearchNode curr = result;

    while (curr != null) {
      path.push(curr.board);
      curr = curr.prev;
    }
    return path;
  }


  private static class SearchNode implements Comparable<SearchNode>{
    public final Board board;
    public final SearchNode prev;
    public final int moves;
    private int priority;

    SearchNode(Board board, SearchNode prev) {
      this.board = board;
      this.prev = prev;
      this.moves = prev != null ? prev.moves + 1 : 0;
      priority = board.manhattan() + moves;
    }

    @Override
    public String toString() {
      return board + "@" + moves;
    }

    @Override
    public int compareTo(SearchNode that) {
      return this.priority - that.priority;
    }
  }

  public static void main(String[] args) {
    // create initial board from file
    Board initial;
//    if (args.length == 1) {
//      int N = Integer.parseInt(args[0]);
//      initial = new Board(initial(N));
//    } else {
      In in = new In(args[0]);
      int N = in.readInt();
      int[][] blocks = new int[N][N];
      for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
          blocks[i][j] = in.readInt();
      initial = new Board(blocks);
//    }

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
      StdOut.println("No solution possible");
    else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution())
        StdOut.println(board);
    }
  }

  private static int[][] initial(int N) {
    int[] initial = new int[N*N];
    for (int i = 0; i < initial.length; i++)
      initial[i] = i;

    for (int i = 0; i < N*N; i++) {
      // int from remainder of deck
      int r = i + (int) (Math.random() * (N - i));
      int swap = initial[r];
      initial[r] = initial[i];
      initial[i] = swap;
    }

    int[][] ret = new int[N][N];
    for (int i = 0; i < N*N; i++) {
      int row = i / N;
      int col = i % N;
      ret [row][col] = initial[i];
    }
    return ret;
  }
}

package algo1.puzzle;

import algs4.MinPQ;
import algs4.Queue;
import algs4.Stack;
import stdlib.In;
import stdlib.StdOut;

import java.util.Comparator;

public class Solver {
  private SearchNode currentSearchNode;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    MinPQ<SearchNode> pq = new MinPQ<SearchNode>(1, new SearchNodeCompare());
    currentSearchNode = new SearchNode(initial, null, 0);
    pq.insert(currentSearchNode);

    MinPQ<SearchNode> twinPq = new MinPQ<SearchNode>(1, new SearchNodeCompare());
    SearchNode twinNode = new SearchNode(initial.twin(), null, 0);
    twinPq.insert(twinNode);

    while (!pq.isEmpty() && ! twinPq.isEmpty()) {
      SearchNode node = pq.delMin();
      twinNode = twinPq.delMin();

      if (node.board.isGoal()) {
        currentSearchNode = node;
        break;
      } else if (twinNode.board.isGoal()) {
        currentSearchNode = null;
        break;
      }

      for (Board b : node.board.neighbors()) {
        if (currentSearchNode.prev == null || !b.equals(currentSearchNode.prev.board))
          pq.insert(new SearchNode(b, node, node.moves + 1));
      }
      for (Board b : twinNode.board.neighbors()) {
        if (twinNode.prev == null || !b.equals(twinNode.prev.board))
          twinPq.insert(new SearchNode(b, twinNode, twinNode.moves + 1));
      }
      currentSearchNode = node;
    }

  }

  // is the initial board solvable?
  public boolean isSolvable()  {return currentSearchNode != null;}
  // min number of moves to solve initial board; -1 if no solution
  public int moves() {return isSolvable() ? currentSearchNode.moves : -1;}

  // sequence of boards in a shortest solution; null if no solution
  public Iterable<Board> solution() {
    Stack<Board> path = new Stack<Board>();
    SearchNode curr = currentSearchNode;

    while (curr != null) {
      path.push(curr.board);
      curr = curr.prev;
    }
    return path;
  }

  private static class SearchNodeCompare implements Comparator<SearchNode> {
    @Override public int compare(SearchNode first, SearchNode second) {
      int firstCost = first.board.manhattan() + first.moves;
      int secondCost = second.board.manhattan() + second.moves;
      return firstCost - secondCost;
    }
  }

  private static class SearchNode {
    public Board board;
    public SearchNode prev;
    public int moves;

    SearchNode(Board board, SearchNode prev, int movesToHere) {
      this.board = board;
      this.prev = prev;
      this.moves = movesToHere;
    }

    @Override
    public String toString() {
      return board + "@" + moves;
    }
  }

  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
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
        StdOut.println(board);
    }
  }
}

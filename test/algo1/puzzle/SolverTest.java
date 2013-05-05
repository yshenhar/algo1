package algo1.puzzle;

import org.junit.Test;

import java.util.Iterator;

import static org.fest.assertions.Assertions.assertThat;

public class SolverTest {

  @Test
  public void unsolvableBoard() {
    int[][] start = {{1,2,3},
                     {4,5,6},
                     {8,7,0}};
    Board board = new Board(start);
    Solver solver = new Solver(board);
    assertThat(solver.isSolvable()).as("unsolvable board considered solved").isFalse();
    assertThat(solver.moves()).as("wrong move count for unsolvable board").isEqualTo(-1);
    assertThat(solver.solution().iterator().hasNext()).as("unsolvable board should have an empty path").isFalse();
  }

  @Test
  public void solveBoardIn4Steps() {
    int[][] start = {{0,1,3},
                     {4,2,5},
                     {7,8,6}};
    Board board = new Board(start);
    Solver solver = new Solver(board);
    assertThat(solver.isSolvable()).as("solver failed to solve " + board).isTrue();
    assertThat(solver.moves()).as("incorrect number of moves to solve " + board).isEqualTo(4);

    Iterator<Board> path = solver.solution().iterator();
    int[][][] expectedBoards = {
        {{0, 1, 3},
         {4, 2, 5},
         {7, 8, 6}},
        {{1, 0, 3},
         {4, 2, 5},
         {7, 8, 6}},
        {{1, 2, 3},
         {4, 0, 5},
         {7, 8, 6}},
        {{1, 2, 3},
         {4, 5, 0},
         {7, 8, 6}},
        {{1, 2, 3},
         {4, 5, 6},
         {7, 8, 0}}
    };
    int idx = 0;
    while(path.hasNext()) {
      assertThat(path.next()).as("wrong solution board at step " + (idx + 1)).isEqualTo(new Board(expectedBoards[idx++]));
    }
  }
}

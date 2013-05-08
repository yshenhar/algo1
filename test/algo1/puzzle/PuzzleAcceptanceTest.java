package algo1.puzzle;

import org.junit.Test;
import stdlib.In;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class PuzzleAcceptanceTest {
  private static final String SOLVER_TEST_FILES = "/Users/yehoram/IdeaProjects/algo1/src/algo1/puzzle/testing";

  @Test
  public void solverAcceptanceTests() {
    File testDir = new File(SOLVER_TEST_FILES);
    for (File testFile : testDir.listFiles()) {
      System.out.println("solving " + testFile.getName() + "...");

      Board initial = boardFrom(testFile);
      Solver solver = new Solver(initial);
      assertThat(solver.moves())
          .as("wrong solution for test " + testFile.getName() + " initial board " + initial)
          .isEqualTo(expectedMoves(testFile));
    }
  }

  private int expectedMoves(File testFile) {
    return Integer.parseInt(
        testFile.getName().substring(testFile.getName().length() - 6, testFile.getName().length()-4));
  }

  private Board boardFrom(File testFile) {
    In in = new In(testFile);
    int N = in.readInt();
    int[][] tiles = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        tiles[i][j] = in.readInt();
      }
    }

    return new Board(tiles);
  }
}

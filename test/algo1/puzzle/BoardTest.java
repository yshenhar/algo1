package algo1.puzzle;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BoardTest {

  @Test
  public void completedBoardWillBeGoalBoard() {
    int[][] goal = {{1,2,3}, {4,5,6}, {7,8,0}};
    Board board = new Board(goal);
    assertThat(board.isGoal()).as("completed board not considered goal").isTrue();
  }
  @Test
  public void incompleteBoardWillNotBeGoalBoard() {
    int[][] notGoal = {{1,2,3}, {4,5,6}, {8,7,0}};
    Board board = new Board(notGoal);
    assertThat(board.isGoal()).as("incomplete board considered goal").isFalse();
  }
  @Test
  public void hammingFunctionWithTwoBlockMisplaced() {
    int[][] start = {{2,1,3}, {4,5,6}, {7,8,0}};
    Board board = new Board(start);
    assertThat(board.hamming()).as("wrong hamming value with two misplaced block").isEqualTo(2);
  }
  @Test
  public void hammingFunctionWithAllBlockMisplacedWillCalcMaxValue() {
    int[][] start = {{3,1,2}, {6,4,5}, {0,7,8}};
    Board board = new Board(start);
    assertThat(board.hamming()).as("wrong hamming value with all misplaced blocks").isEqualTo(8);
  }
  @Test
  public void manhattanFunctionWithTwoMisplacedBlocks() {
    int[][] start = {{2,1,3}, {4,5,6}, {7,8,0}};
    Board board = new Board(start);
    assertThat(board.manhattan()).as("wrong manhattan value with two misplaced block").isEqualTo(2);
  }
  @Test
  public void manhattanFunctionWithMisplacedBlocks() {
    int[][] start = {{8,1,3}, {4,0,2}, {7,6,5}};
    Board board = new Board(start);
    assertThat(board.manhattan()).as("wrong manhattan value with misplaced block").isEqualTo(10);
  }
  @Test
  public void boardsEquality() {
    int[][] start1 = {{1,2,3}, {4,5,6}, {7,8,0}};
    int[][] start2 = {{2,1,3}, {4,5,6}, {7,8,0}};
    Board board1 = new Board(start1);
    Board board2 = new Board(start1);
    Board board3 = new Board(start2);
    assertThat(board1.equals(board1)).as("board not equal to self").isTrue();
    assertThat(board1.equals(board2)).as("board not equal to same").isTrue();
    assertThat(board1.equals(board3)).as("board equal to different board").isFalse();
  }

 /* @Test
  public void boardWithTwoNeighbors() {
    int[][] start = {{0,1,2}, {3,4,5}, {7,8,6}};
    Board board = new Board(start);
    int[][][] expectedBlocks = { {{1,0,2}, {3,4,5}, {7,8,6}},
                                    {{3,1,2}, {0,4,5}, {7,8,6}} };
    Iterator<Board> neighbors = board.neighbors().iterator();
    Board[] boards = {new Board(expectedBlocks[0]), new Board(expectedBlocks[1])};

    while (neighbors.hasNext()) {
      System.out.println(neighbors.next());
    }
  }

  @Test
  public void boardWithFourNeighbors() {
    int[][] start = {{1,2,3}, {4,0,5}, {7,8,6}};
    Board board = new Board(start);
    Iterator<Board> neighbors = board.neighbors().iterator();

    while (neighbors.hasNext()) {
      System.out.println(neighbors.next());
    }
  }*/
}



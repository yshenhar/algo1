package algo1.puzzle;

import algs4.Queue;
import stdlib.StdRandom;

import java.util.Arrays;

import static java.lang.Math.abs;


public class Board {
  private int[][] blocks;
  private int dimension;

  // (where blocks[i][j] = block in row i, column j)
  public Board(int[][] blocks) {
    int idx = 0;
    dimension = blocks.length;
    this.blocks = blocks;
  }

  public int dimension() {
    return dimension;
  }

  public int hamming()  {
    int hamming = 0;
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        if (!blank(i, j) && blocks[i][j] != puzzleValue(i, j))
          hamming++;
    return hamming;
  }

  public int manhattan() {
    int manhattan = 0;
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        manhattan += blank(i, j) ? 0 : manhattanDistance(blocks[i][j], i, j);
    return manhattan;
  }

  private int manhattanDistance(int value, int row, int col) {
    Block puzzleBlock = puzzleBlockFor(value);
    return abs(puzzleBlock.row - row) + abs(puzzleBlock.col - col);
  }

  private Block puzzleBlockFor(int value) {
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        if (puzzleValue(i, j) == value)
          return new Block(i, j);

    throw new RuntimeException("did not find puzzle location for " + value);
  }

  public boolean isGoal() {
    return hamming() == 0;
  }
  private boolean blank(int row, int col) {
    return blocks[row][col] == 0;
  }
  private int puzzleValue(int row, int col) {
    int val = row * dimension + col + 1;
    return val < 9 ? val : 0;
  }

  public Board twin() {
    Board twin = new Board(this.blocks);
    boolean exchange = false;
    while (!exchange) {
      int i = StdRandom.uniform(dimension);
      int j = StdRandom.uniform(dimension);
      int ej = j;
      if (j == dimension - 1) {
        --ej;
      } else {
        ++ej;
      }
      if (blocks[i][j] != 0 && blocks[i][ej] != 0) {
        int temp = twin.blocks[i][j];
        twin.blocks[i][j] = blocks[i][ej];
        twin.blocks[i][ej] = temp;
        exchange = true;
      }
    }
    return twin;
  }
  public boolean equals(Object that) {
    if (that == this) return true;
    if (that.getClass() == this.getClass()) {
      Board b = (Board) that;
      return Arrays.equals(this.blocks, b.blocks);
    }
    return false;
  }

  public Iterable<Board> neighbors() {
    Block blank = findBlank();
    Queue<Board> boards = new Queue<Board>();
    Queue<Block> neighbors = neighborsFor(blank);
    for (Block b : neighbors) {
      boards.enqueue(move(blank, b));
    }
    return boards;
  }

  private Board move(Block blank, Block b) {
    int[][] newBlocks = copyBlocks();
    newBlocks[blank.row][blank.col] = blocks[b.row][b.col];
    newBlocks[b.row][b.col] = 0;
    return new Board(newBlocks);
  }

  private int[][] copyBlocks() {
    int[][] newBlocks = new int[dimension][dimension];
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        newBlocks[i][j] = blocks[i][j];
    return newBlocks;
  }

  private Block findBlank() {
    for (int i = 0; i < dimension; i++)
      for (int j = 0; j < dimension; j++)
        if (blank(i, j))
          return new Block(i, j);
    throw new RuntimeException("Invalid board - no blank block:\n" + this);
  }

  private Queue<Block> neighborsFor(Block blank) {
    Queue<Block> neighbors = new Queue<Block>();

    if (blank.col > 0) neighbors.enqueue(new Block(blank.row, blank.col - 1));
    if (blank.col < (dimension - 1)) neighbors.enqueue(new Block(blank.row, blank.col + 1));
    if (blank.row > 0) neighbors.enqueue(new Block(blank.row - 1, blank.col));
    if (blank.row < (dimension -1)) neighbors.enqueue(new Block(blank.row + 1, blank.col));
    return neighbors;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        sb.append(blocks[i][j]).append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  private static class Block {
    public final int row;
    public final int col;
    public Block(int row, int col) {
      this.row = row;
      this.col = col;
    }

  }
}
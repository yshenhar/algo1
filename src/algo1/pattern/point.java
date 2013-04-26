package algo1.pattern;

import stdlib.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER;

  private class SlopeOrderComparator implements Comparator<Point> {
    private Point base;
    public SlopeOrderComparator(Point base) {
      this.base = base;
    }
    @Override
    public int compare(Point point, Point point2) {
      return (int) (base.slopeTo(point) - base.slopeTo(point2));
    }
  };

  private final int x;                              // x coordinate
  private final int y;                              // y coordinate

  // create the point (x, y)
  public Point(int x, int y) {
        /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
    SLOPE_ORDER = new SlopeOrderComparator(this);
  }

  // plot this point to standard drawing
  public void draw() {
        /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
        /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  // slope between this point and that point
  public double slopeTo(Point that) {
    if (this.y == that.y && this.x == that.x) return Double.NEGATIVE_INFINITY;
    if (this.y == that.y) return 0.0;
    if (this.x == that.x) return Double.POSITIVE_INFINITY;

    return (double)(that.y - this.y) / (double)(that.x - this.x);
  }

  // is this point lexicographically smaller than that one?
  // comparing y-coordinates and breaking ties by x-coordinates
  public int compareTo(Point that) {
    if (this.x == that.x && this.y == that.y) return 0;
    if (this.y < that.y || (this.y == that.y && this.x < that.x)) return -1;
    return 1;
  }

  // return string representation of this point
  public String toString() {
        /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  // unit test
  public static void main(String[] args) {
        /* YOUR CODE HERE */
  }
}
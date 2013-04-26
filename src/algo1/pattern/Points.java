package algo1.pattern;

import stdlib.In;
import stdlib.StdDraw;
import stdlib.StdOut;

import java.util.Arrays;

public class Points {
  protected static void drawLine(Point p1, Point p2, Point p3, Point p4) {
    Point[] points = new Point[4];
    points[0] = p1;
    points[1] = p2;
    points[2] = p3;
    points[3] = p4;
    Arrays.sort(points);
    StdOut.print(points[0] + " -> " + points[1] + " -> " + points[2]
        + " -> " + points[3]);
    StdOut.println();
    points[0].draw();
    points[1].draw();
    points[2].draw();
    points[3].draw();
    points[0].drawTo(points[3]);
  }

  protected static Point[] loadPoints(String[] args) {
    if (args.length < 1)
      return null;
    String filename = args[0];
    In input = new In(filename);
    int N = input.readInt();
    if (N < 4)
      return null;
    Point[] points = new Point[N];
    for (int i = 0; i < N; ++i) {
      int x = input.readInt();
      int y = input.readInt();
      points[i] = new Point(x, y);
    }
    return points;
  }

  protected static void initCanvas() {
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
  }
}

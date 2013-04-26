package algo1.pattern;

import stdlib.StdOut;

import java.util.Arrays;

public class Fast extends Points {

  private static void drawLineSeg(Point[] segPoints) {
    String output = new String();
    int len = segPoints.length;
    for (int i = 0; i < len; ++i) {
      output += segPoints[i].toString();
      if (i != len - 1) {
        output += " -> ";
      }
      segPoints[i].draw();
    }
    StdOut.println(output);
    segPoints[0].drawTo(segPoints[len - 1]);
  }

  private static void outputSeg(Point oP, Point[] sortedPoints,
                                int startIndex, int adj) {
    Point[] segPoints = new Point[adj + 1];
    for (int i = 0; i < adj; ++i) {
      segPoints[i] = sortedPoints[startIndex + i];
    }
    segPoints[adj] = oP;
    Arrays.sort(segPoints);
    if (oP.compareTo(segPoints[0]) == 0)
      drawLineSeg(segPoints);
  }

  private static void checkSequence(Point p, Point[] sortedPoints) {
    double lastSlope = p.slopeTo(sortedPoints[1]);
    int adj = 1;
    int start = 1;
    for (int i = 2; i < sortedPoints.length; i++) {
      double curSlope = p.slopeTo(sortedPoints[i]);
      if (curSlope == lastSlope) {
        adj++;
      } else if (p.compareTo(sortedPoints[i]) == 0) {
      ++adj;
      } else if (adj >= 3) {
        // output seg
        outputSeg(p, sortedPoints, start, adj);
        adj = 1;
        lastSlope = curSlope;
        start = i;
      } else {
        adj = 1;
        lastSlope = curSlope;
        start = i;
      }
    }
  }
  public static void main(String[] args) {
    Point[] points = loadPoints(args);
    if (points == null) return;
    initCanvas();


    for(Point p : points) {
      Point[] slopes = points.clone();
      Arrays.sort(slopes, p.SLOPE_ORDER);
      checkSequence(p, slopes);
    }
  }
}


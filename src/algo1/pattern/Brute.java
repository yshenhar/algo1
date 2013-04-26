package algo1.pattern;

public class Brute extends Points {
  private static boolean colinear(Point p1, Point p2, Point p3, Point p4) {
    double slope1 = p1.slopeTo(p2);
    double slope2 = p1.slopeTo(p3);
    double slope3 = p1.slopeTo(p4);
    if (slope1 == slope2 && slope2 == slope3) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Point[] points = loadPoints(args);
    if (points == null) return;

    initCanvas();

    for (int i = 0; i < points.length; i++) {
      for (int j = i+1; j < points.length; j++) {
        for (int k = j+1; k < points.length; k++) {
          for (int l = k+1; l < points.length; l++) {
            if (colinear(points[i], points[j], points[k], points[l])) {
              drawLine(points[i], points[j], points[k], points[l]);
            }
          }
        }
      }
    }
  }

}

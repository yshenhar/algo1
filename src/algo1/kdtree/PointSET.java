package algo1.kdtree;

import algs4.Point2D;
import algs4.Queue;
import algs4.RedBlackBST;
import stdlib.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class PointSET {

  TreeSet<Point2D> points;

  public PointSET() {
    points = new TreeSet<Point2D>();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size() {
    return points.size();
  }

  public void insert(Point2D p) {
    points.add(p);
  }

  public boolean contains(Point2D p) {
    return points.contains(p);
  }

  public void draw() {
    for (Point2D p : points) {
      p.draw();
    }
  }

  // all points in the set that are inside the rectangle
  public Iterable<Point2D> range(RectHV rect) {
    List<Point2D> inRange = new ArrayList<Point2D>();
    for (Point2D p : points) {
      if (rect.contains(p)) {
        inRange.add(p);
      }
    }
    return inRange;
  }

  // a nearest neighbor in the set to p; null if set is empty
  public Point2D nearest(Point2D p) {
    Point2D nearest = null;
    double minDist = Double.MAX_VALUE;
    for (Point2D current : points) {
      double dist = p.distanceTo(current);
      if (dist < minDist) {
        minDist = dist;
        nearest = current;
      }
    }
    return nearest;
  }

  public static void main(String [] args){
    StdDraw.setXscale(0, 1);
    StdDraw.setYscale(0, 1);
//        StdDraw.show(0);

    Point2D p1 = new Point2D(0, 0);
    Point2D p2 = new Point2D(0.1, 0.4);
    Point2D p3 = new Point2D(0.6, 0.5);
    PointSET pointSet = new PointSET();
    pointSet.insert(p1);
    pointSet.insert(p2);
    pointSet.insert(p3);
    pointSet.draw();


    RectHV rec = new RectHV(0.4, 0.3, 0.8, 0.6);
    rec.draw();

//        StdDraw.show(0);

    System.out.println(pointSet.range(rec));
    System.out.println(pointSet.nearest(new Point2D(0.5, 0.4)));
  }
}

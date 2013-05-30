package algo1.kdtree;

import algs4.Point2D;
import algs4.Stack;
import stdlib.StdDraw;

public class KDTree {

  public KDTree() {

  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public int size() {
    return getSize(root);
  }

  public void insert(Point2D p) {
    root = insert(root, p, HORIZONTAL, null);
  }

  public Node insert(Node node, Point2D p, boolean type, Node parent) {
    if (node == null) {
      RectHV r;
      if (parent == null) {
        r = new RectHV(0.0, 0.0, 1.0, 1.0);
      } else {
        r = parent.rect;
      }
      return new Node(p, r, type);
    }

    if (node.compareTo(p) < 0) {
      node.left = insert(node.left, p, !type, node);
    } else {
      node.right = insert(node.right, p, !type, node);
    }

    node.size = 1 + getSize(node.left) + getSize(node.right);
    return node;
  }

  private int getSize(Node n) {
    return n != null ? n.size : 0;
  }

  public boolean contains(Point2D p) {
    return search(root, p) != null;
  }

  private Node search(Node node, Point2D p) {
    if (node == null) {
      return null;
    }

    if (node.compareTo(p) > 0) {
      return search(node.left, p);
    } else if (node.compareTo(p) < 0) {
      return search(node.right, p);
    }

    return node;
  }

  public void draw() {
    // draw canvas border
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.line(0, 0, 1, 0);
    StdDraw.line(1, 0, 1, 1);
    StdDraw.line(1, 1, 0, 1);
    StdDraw.line(0, 1, 0, 0);

    draw(root, null);
  }

  private void draw(Node node, Node parent) {
    if (node == null) {
      return;
    }

    StdDraw.setPenColor(StdDraw.BLACK);
    node.value.draw();

    double x = node.value.x();
    double y = node.value.y();

    if (node.type == HORIZONTAL) {
      StdDraw.setPenColor(StdDraw.RED);

      StdDraw.line(x, node.rect.ymin(), x, node.rect.ymax());

    } else {
      StdDraw.setPenColor(StdDraw.BLUE);

      StdDraw.line(node.rect.xmin(), y, node.rect.xmax(), y);
    }

    draw(node.left, node);
    draw(node.right, node);
  }

  public Iterable<Point2D> range(RectHV rect) {
    Stack<Point2D> stack = new Stack<Point2D>();

    range(root, rect, stack);

    return stack;
  }

  private void range(Node node, RectHV rect, Stack<Point2D> stack) {

    if (node == null) {
      return;
    }

    if (node.compareTo(rect) > 0) {
      range(node.left, rect, stack);
    } else if (node.compareTo(rect) < 0) {
      range(node.right, rect, stack);
    } else {

      // both

      if (rect.contains(node.value)) {
        stack.push(node.value);
      }

      range(node.left, rect, stack);
      range(node.right, rect, stack);
    }
  }

  private Point2D minPoint = null;
  private double minDist = 0.0;
  private Node root;

  private static final boolean HORIZONTAL = true;
  private static final boolean VERTICAL = false;

  private class Node {
    private final boolean type;
    private final Point2D value;
    private final RectHV rect;
    private Node left, right;
    private int size;

    public Node(Point2D p, RectHV rect, boolean type) {
      this.value = p;
      this.rect = rect;
      this.type = type;
      this.size = 1;
    }

    public int compare(double p1, double p2) {
      if (p1 > p2) {
        return 1;
      } else if (p1 < p2) {
        return -1;
      } else {
        return 0;
      }
    }

    public int compareTo(Point2D point) {
      if (this.type == HORIZONTAL) {
        int cmp = compare(this.value.x(), point.x());
        if (cmp != 0) {
          return cmp;
        }
        return compare(this.value.y(), point.y());
      }

      int cmp = compare(this.value.y(), point.y());
      if (cmp != 0) {
        return cmp;
      }
      return compare(this.value.x(), point.x());
    }

    public int compareTo(RectHV r) {

      double x = this.value.x();
      double y = this.value.y();

      if (this.type == HORIZONTAL) {
        if (x > r.xmax()) {
          // left
          return 1;
        }
        if (x < r.xmin()) {
          // right
          return -1;
        }
      } else {
        if (y > r.ymax()) {
          // bottom
          return 1;
        }
        if (y < r.ymin()) {
          // top
          return -1;
        }
      }

      // interset
      return 0;
    }

    public Point2D nearest(Point2D p) {
      minPoint = root.value;
      minDist = minPoint.distanceSquaredTo(p);

      searchNearest(root, p);
      return minPoint;
    }

    private void searchNearest(Node node, Point2D p) {
      double dist = node.value.distanceSquaredTo(p);

      if (minDist > dist) {
        minPoint = node.value;
        minDist = dist;
      }

      if (node.left != null && node.right != null) {
        double left, right;

        left = node.left.rect.distanceSquaredTo(p);
        right = node.right.rect.distanceSquaredTo(p);

        if (left < right) {
          searchNearest(node.left, p);

          if (right < minDist) {
            searchNearest(node.right, p);
          }

        } else {
          searchNearest(node.right, p);

          if (left < minDist) {
            searchNearest(node.left, p);
          }
        }

        return;
      }

      if (node.left != null) {
        if (node.left.rect.distanceSquaredTo(p) < minDist) {
          searchNearest(node.left, p);
        }
      }

      if (node.right != null) {
        if (node.right.rect.distanceSquaredTo(p) < minDist) {
          searchNearest(node.right, p);
        }
      }

      return;
    }
  }
}

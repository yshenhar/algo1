package algo1.util;

import algs4.Queue;

public class BredthFirstTreeTraversal {

  static class Node {
    public Node left;
    public Node right;
    private String data;

    public Node(String data) {
      this.data = data;
    }

    public void withChildern(Node left, Node right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return data;
    }
  }

  public static void breadthFirst(Node root) {
    Queue<Node> q = new Queue<Node>();
    q.enqueue(root);

    while (!q.isEmpty()) {
      Node curr = q.dequeue();
      System.out.println(curr);
      if (curr.left != null)
        q.enqueue(curr.left);
      if (curr.right != null)
        q.enqueue(curr.right);
    }
  }

  public static void depthFirst(Node node) {
    System.out.println(node);
    if (node.left != null)
      depthFirst(node.left);
    if (node.right != null)
      depthFirst(node.right);
  }

  public static void main(String...args) {
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    a.withChildern(b, c);
    Node d = new Node("d");
    Node e = new Node("e");
    b.withChildern(d, e);
    Node f = new Node("f");
    Node g = new Node("g");
    c.withChildern(f, g);

    System.out.println("Breadth First");
    breadthFirst(a);
    System.out.println("Depth First");
    depthFirst(a);
  }
}

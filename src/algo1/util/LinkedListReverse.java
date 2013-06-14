package algo1.util;

public class LinkedListReverse {

  public static Node reverse(Node node) {
    if (node == null) return null;
    if (node.isTail()) return node;

    Node next = node.next;
    node.disconnect();
    Node reversed = reverse(next);
    next.next = node;
    return reversed;
  }

  public static class Node<T> {
    private T data;
    private Node next;
    public Node (T data, Node next) {
      this.data = data;
      this.next = next;
    }

    public void linkTo(Node next) {
      this.next = next;
    }
    public void disconnect() {
      this.next = null;
    }
    public boolean isTail() {return this.next == null;}
    public Node<T> next() {return this.next;}

    public String toString() {
      return data.toString() + " -> " + this.next;
    }
  }

  public static void main(String[] args) {
    Node<Integer> head = null;
    Node<Integer> prev = null;
    for (int i = 1; i < 4; i++) {
      Node<Integer> curr = new Node<Integer>(i, null);
      if (head == null) head = curr;
      if (prev != null) prev.linkTo(curr);
      prev = curr;
    }
    System.out.println("List:" + head);
    Node rev = reverse(head);
    System.out.println("Reversed: " + rev);
  }

//  private static void print(String msg, Node head) {
//    Node n = head;
//    System.out.println(msg);
//    while(!n.isTail()) {
//      System.out.println(n);
//      n = n.next();
//    }
//  }
}
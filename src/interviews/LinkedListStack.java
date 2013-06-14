package interviews;

public class LinkedListStack<T> {
  private Node head;
  private int size;

  class Node {
    T data;
    Node next;
    public Node(T data) {
      this.data = data;
    }
  }

  public LinkedListStack() {
    head = null;
    size = 0;
  }

  public int size() {return size;}
  public boolean empty() {return size() == 0;}

  public void push(T elem) {
    size++;
    Node n = new Node(elem);
    if (empty()) {
      head = n;
    } else {
      n.next = head;
      head = n;
    }
  }

  public T pop() {
    if (empty()) throw new RuntimeException("can't pop from an empty stack");

    Node ret = head;
    head = ret.next;
    size--;
    return ret.data;
  }

  public T peek() {
    return head.data;
  }

  public void clear() {
    while (!empty()) {
      pop();
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node n = head;
    while(n != null) {
      sb.append(n.data).append(" -> ");
      n = n.next;
    }
    return sb.toString();
  }

  public static void main(String...args) {
    LinkedListStack<String> stack = new LinkedListStack<String>();
    stack.push("D");
    stack.push("C");
    stack.push("B");
    stack.push("A");
    System.out.println(stack);
    System.out.println(stack.peek());
    System.out.println(stack);

    stack.pop();
    System.out.println(stack);

    stack.clear();
    System.out.println("Empty? " + stack.empty());
  }
}

package algo1.dequeue;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

  public Deque() {
    first = last = null;
    size = 0;
  }

  public boolean isEmpty() {
    return first == null && last == null;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) throw new NullPointerException();

    boolean empty = isEmpty();
    Node currentHead = first;
    first = new Node(item, currentHead, null);
    if (empty) {
      last = first;
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null) throw new NullPointerException();
    boolean empty = isEmpty();
    Node currentTail = last;
    last = new Node(item, null, currentTail);
    if (empty) {
      first = last;
    }
    size++;
  }

  public Item removeFirst() {
    if (isEmpty())
      throw new UnsupportedOperationException("underflow");

    Item result = first.item;
    first = first.next;
    if (first == null) {
      last = null;
    } else {
      first.prev = null;
    }
    size--;
    return result;
  }

  public Item removeLast() {
    if (isEmpty())
      throw new UnsupportedOperationException("underflow");
    Item result = last.item;
    last = last.prev;
    if (last == null) {
      first = null;
    } else {
      last.next = null;
    }
    size--;
    return result;
  }

  public Iterator<Item> iterator() {
    return new Iterator<Item>() {

      @Override
      public boolean hasNext() {
        return !isEmpty();
      }

      @Override
      public Item next() {
        return removeFirst();
      }

      @Override
      public void remove() {throw new UnsupportedOperationException("remove");}
    };
  }

  private Node first;
  private Node last;
  private int size;

  private class Node {
    public Item item;
    public Node next;
    public Node prev;

    public Node(Item item, Node next, Node prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }
}
package algo1.dequeue;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

  public RandomizedQueue() {
    items = (Item[]) new Object[2];
    first = last = 0;
  }
  public boolean isEmpty()  {
    return size() <= 0;
  }
  public int size() {
    return last - first;
  }
  public void enqueue(Item item) {
    // todo handle null, resize
    items[last++] = item;
  }
  public Item dequeue() {return null;}
  public Item sample()  {return null;}
  public Iterator<Item> iterator() {return null;}

  private Item[] items;
  private int first;
  private int last;
}
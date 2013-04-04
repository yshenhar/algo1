package algo1.dequeue;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class DequeueTest {

  @Before
  public void setup() {
    deque = new Deque<String>();
  }
  @Test
  public void empty_deque() {
    assertThat(deque.isEmpty(), is(true));
    assertThat(deque.size(), is(0));
  }

  @Test
  public void add_first_will_queue_item() {
    deque.addFirst("ABC");
    assertThat(deque.isEmpty(), is(false));
    assertThat(deque.size(), is(1));
  }

  @Test
  public void add_last_will_queue_item() {
    deque.addLast("ABC");
    assertThat(deque.isEmpty(), is(false));
    assertThat(deque.size(), is(1));
  }

  @Test
  public void add_first_and_last_will_queue_items() {
    deque.addFirst("ABC");
    deque.addLast("DEF");
    assertThat(deque.isEmpty(), is(false));
    assertThat(deque.size(), is(2));
  }

  @Test
  public void remove_first_from_single_will_return_first_item() {
    deque.addFirst("ABC");
    assertThat(deque.removeFirst(), is("ABC"));
    assertThat(deque.size(), is(0));
    assertThat(deque.isEmpty(), is(true));
  }

  @Test
  public void first_from_multiple_items_will_resize() {
    deque.addFirst("One");
    deque.addFirst("Two");
    assertThat(deque.removeFirst(), is("Two"));
    assertThat(deque.size(), is(1));
    assertThat(deque.isEmpty(), is(false));
  }

  @Test
  public void remove_last_from_single_will_return_last_item() {
    deque.addFirst("ABC");
    assertThat(deque.removeLast(), is("ABC"));
    assertThat(deque.size(), is(0));
    assertThat(deque.isEmpty(), is(true));
  }

  @Test
  public void multiple_add_remove_first() {
    deque.addFirst("A");
    deque.addFirst("B");
    deque.addFirst("C");

    assertThat(deque.removeFirst(), is("C"));
    assertThat(deque.removeFirst(), is("B"));
    assertThat(deque.removeFirst(), is("A"));
    assertThat(deque.size(), is(0));
    assertThat(deque.isEmpty(), is(true));

  }

  @Test
  public void multiple_add_remove_last() {
    deque.addLast("A");
    deque.addLast("B");
    deque.addLast("C");

    assertThat(deque.removeLast(), is("C"));
    assertThat(deque.removeLast(), is("B"));
    assertThat(deque.removeLast(), is("A"));
    assertThat(deque.size(), is(0));
    assertThat(deque.isEmpty(), is(true));

  }

  @Test
  public void multiple_add_remove() {
    deque.addFirst("B");
    deque.addFirst("A");
    deque.addLast("C");

    assertThat(deque.removeFirst(), is("A"));
    assertThat(deque.size(), is(2));
    assertThat(deque.removeLast(), is("C"));
    assertThat(deque.size(), is(1));
    assertThat(deque.removeLast(), is("B"));
    assertThat(deque.size(), is(0));
    assertThat(deque.isEmpty(), is(true));
  }

  @Test
  public void iterator_over_empty_deque() {
    Iterator<String> iterator = deque.iterator();
    assertThat(iterator.hasNext(), is(false));
  }

  @Test
  public void iterator_over_single_item_deque() {
    deque.addFirst("A");
    Iterator<String> iterator = deque.iterator();

    assertThat(iterator.hasNext(), is(true));
    assertThat(iterator.next(), is("A"));
  }

  @Test
  public void iterator_over_multi_item_deque() {
    deque.addFirst("A");
    deque.addFirst("B");
    Iterator<String> iterator = deque.iterator();

    assertThat(iterator.next(), is("B"));
    assertThat(iterator.next(), is("A"));
  }

  @Test(expected = NullPointerException.class)
  public void adding_first_null_item_will_throw() {
    deque.addFirst(null);
  }

  @Test(expected = NullPointerException.class)
  public void adding_last_null_item_will_throw() {
    deque.addLast(null);
  }

  private Deque<String> deque;
}

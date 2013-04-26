package algo1.dequeue;

import algs4.Merge;
import org.junit.Test;
import stdlib.StdRandom;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;


public class RandomizedQueueTest {

  @Test
  public void new_queue_is_empty() {
    RandomizedQueue<String> q = new RandomizedQueue<String>();
    assertThat(q.size()).as("wrong size for empty queue").isEqualTo(0);
    assertThat(q.isEmpty()).as("new queue was not empty").isTrue();
  }
  @Test
  public void enque_item_will_make_queue_non_empty() {
    RandomizedQueue<String> q = new RandomizedQueue<String>();
    q.enqueue("A");
    assertThat(q.size()).as("wrong size for empty queue").isEqualTo(1);
    assertThat(q.isEmpty()).as("adding item to queue did not make it non-empty").isFalse();
  }


}

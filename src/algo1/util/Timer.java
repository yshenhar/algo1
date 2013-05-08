package algo1.util;

public class Timer {
  private long start;
  private long stop;

  public void start() {
    start = System.currentTimeMillis();
  }

  public void stop() {
    stop = System.currentTimeMillis();
  }

  public double elapsed() {
    return ((double) (start - stop)) / 1000;
  }
}

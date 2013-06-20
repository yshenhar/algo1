package algo1.util;

import algs4.Queue;
import algs4.SET;
import stdlib.In;
import stdlib.StdOut;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
  public void crawl(String source) {
    Queue<String> queue = new Queue<String>();
    Set<String> discovered = new HashSet<String>();

    discovered.addAll(ignored());
    queue.enqueue(source);
    discovered.add(source);

    int visited = 1;
    while (!queue.isEmpty()) {
      String v = queue.dequeue();
      StdOut.printf("%d. visiting %s (q size = %d, discovered size = %d)\n", visited++, v, queue.size(), discovered.size());
      String input = readSite(v);

      if (input != null) {
        String regex = "http://(\\w+\\.)*(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
          String w = matcher.group();
          if (!discovered.contains(w)) {
            discovered.add(w);
            queue.enqueue(w);
          }
        }
      }
    }
  }

  private String readSite(String v) {
    try {
      In in = new In(v);
      return in.readAll();
    } catch (Exception e) {
      StdOut.printf("failed to read site %s\n", v);
    }
    return "";
  }

  private List<String> ignored() {
    String[] ignored = {
      "http://purchase.tickets.com"
    };
    return Arrays.asList(ignored);
  }


  public static void main(String args[]){
    Crawler crawler = new Crawler();
    crawler.crawl("http://www.princeton.edu");
  }
}

package algo1.wordnet;

import stdlib.In;
import stdlib.StdOut;

import java.io.File;

public class Outcast {
  private WordNet wordnet;

  // constructor takes a WordNet object
  public Outcast(WordNet wordnet) {
    this.wordnet = wordnet;
  }

  // given an array of WordNet nouns, return an outcast
  public String outcast(String[] nouns) {
    int maxDist = -1;
    String outcast = null;
    int[][] distances = new int[nouns.length][nouns.length];

    for (int i = 0; i < nouns.length; i++) {
      for (int j = 0; j < nouns.length; j++) {
        distances[i][j] = wordnet.distance(nouns[i], nouns[j]);
      }
    }
    print(nouns, distances);

    return outcast;
  }

  private void print(String[] nouns, int[][] distances) {
    for (int i = 0; i < nouns.length; i++) {System.out.printf("\t%s", nouns[i]);}
    System.out.println();
    for (int i = 0; i < nouns.length; i++) {
      System.out.printf("%s\t", nouns[i]);
      for (int j = 0; j < nouns.length; j++) {
        System.out.printf("%d\t", distances[i][j]);
      }
      System.out.println();
    }
  }

  private static final String PATH = "src/algo1/wordnet/";

  // for unit testing of this class (such as the one below)
  public static void main(String[] args) {

    WordNet wordnet = new WordNet(PATH + args[0], PATH + args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
      String[] nouns = In.readStrings(PATH + args[t]);
      StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
  }
}

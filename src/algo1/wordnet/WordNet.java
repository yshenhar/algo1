package algo1.wordnet;

import algs4.Bag;
import algs4.Digraph;
import stdlib.In;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordNet {

  private SAP sap;
  private Map<Integer, String> id2synset;
  private Map<String, Bag<Integer>> noun2ids;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    id2synset = new HashMap<Integer, String>();
    noun2ids = new HashMap<String, Bag<Integer>>();
    buildSynstes(synsets);

    sap = new SAP(createHypernyms(hypernyms));
  }

  private Digraph createHypernyms(String hypernyms) {
    In in = new In(hypernyms);
    Digraph g = new Digraph(id2synset.size());
    while (!in.isEmpty()) {
      String[] line = in.readLine().split(",");
      int v = Integer.parseInt(line[0]);
      for (int i = 1; i < line.length; i++) {
        int w = Integer.parseInt(line[i]);
        g.addEdge(v, w);
      }
    }

    return g;
  }

  private void buildSynstes(String synsets) {
    In in = new In(synsets);

    while (!in.isEmpty()) {
      String[] line = in.readLine().split(",");
      int id = Integer.parseInt(line[0]);
      id2synset.put(id, line[1]);
      for (String noun : line[1].split(" ")) {
        Bag<Integer> bag = noun2ids.get(id);
        if (bag == null) {
          bag = new Bag<Integer>();
          noun2ids.put(noun, bag);
        }
        bag.add(id);
      }
    }
  }

  // the set of nouns (no duplicates), returned as an Iterable
  public Iterable<String> nouns() {
    return noun2ids.keySet();
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    return noun2ids.containsKey(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    return sap.length(id(nounA), id(nounB));
  }

  // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
// in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    return id2synset.get(sap.ancestor(id(nounA), id(nounB)));
  }

  private Bag<Integer> id(String noun) {return noun2ids.get(noun);}

  // for unit testing of this class
  public static void main(String[] args) {
    String synsets = args[0];
    String hypernyms = args[1];

    WordNet wordnet = new WordNet(synsets, hypernyms);
    int hid = wordnet.noun2ids.get("horse").iterator().next();
    int zid = wordnet.noun2ids.get("zebra").iterator().next();

    Iterable<Integer> path = wordnet.sap.min(hid, zid).pathTo;
    for (int p : path) {
      System.out.printf("%s (%d) -> ", wordnet.id2synset.get(p), p);
    }
    System.out.println();

    path = wordnet.sap.min(zid, hid).pathTo;
    for (int p : path) {
      System.out.printf("%s (%d) -> ", wordnet.id2synset.get(p), p);
    }
    System.out.println();
    System.out.println(wordnet.distance("horse", "zebra"));
    System.out.println(wordnet.distance("zebra", "horse"));
  }
}

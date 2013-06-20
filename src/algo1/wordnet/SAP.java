package algo1.wordnet;

import algs4.BreadthFirstDirectedPaths;
import algs4.Digraph;
import stdlib.In;
import stdlib.StdIn;
import stdlib.StdOut;

import java.util.Iterator;

public class SAP {
  private Digraph graph;
  static class Path {
    public int distance = -1;
    public int ancestor = -1;
    public Iterable<Integer> pathTo = null;

//    Path(int source, int distance, int ancestor, Iterable<Integer> pathTo) {
//      this.source = source;
//      this.distance = distance;
//      this.ancestor = ancestor;
//      this.pathTo = pathTo;
//    }
  }
  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    graph = new Digraph(G);
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    BreadthFirstDirectedPaths pv = new BreadthFirstDirectedPaths(graph, v);
    BreadthFirstDirectedPaths pw = new BreadthFirstDirectedPaths(graph, w);

    int minNode =  min(pv, pw).ancestor;
    return minNode < 0 ? minNode : pv.distTo(minNode) + pw.distTo(minNode);
   }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    return min(v, w).ancestor;
  }

  Path min(int v, int w) {
    return min(new BreadthFirstDirectedPaths(graph, v), new BreadthFirstDirectedPaths(graph, w));
  }

  Path min(BreadthFirstDirectedPaths pv, BreadthFirstDirectedPaths pw) {
    Path path = new Path();

    int minNode = -1;
    int minDist = -1;

    for (int node = 0; node <  graph.V() - 1; node++) {
      if (pv.hasPathTo(node) && pw.hasPathTo(node)) {
        int dist = pv.distTo(node) + pw.distTo(node);
        if (minDist < 0 || dist < minDist){
          path.ancestor = node;
          path.distance = dist;
          path.pathTo = pv.pathTo(node);

          minDist = dist;
          minNode = node;
        }
      }
    }

    return path;
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    int minDist = -1;

    for (int vnode : v) {
      for (int wnode : w) {
        int dist = length(vnode, wnode);
        if (minDist < 0 || dist < minDist) {
          minDist = dist;
        }
      }
    }

    return minDist;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    int minDist = -1;
    int minAncestor = -1;

    for (int vnode : v) {
      for (int wnode : w) {
        int dist = length(vnode, wnode);
        if (minDist < 0 || dist < minDist) {
          minDist = dist;
          minAncestor = ancestor(vnode, wnode);
        }
      }
    }

    return minAncestor;
  }

  // for unit testing of this class (such as the one below)
  public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    SAP sap = new SAP(G);
    while (!StdIn.isEmpty()) {
      int v = StdIn.readInt();
      int w = StdIn.readInt();
      int length   = sap.length(v, w);
      int ancestor = sap.ancestor(v, w);
      StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }
  }
}

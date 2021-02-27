package coursework;

import java.util.HashMap;
import java.util.Map;

public class HamiltonCircuit {

  public static void main(String[] args) {
    boolean[][] adj1 = {
        {false, true, true, false, false},
        {true, false, true, false, false},
        {true, true, false, true, true},
        {false, false, true, false, true},
        {false, false, true, true, false},
    };

    boolean[][] adj2 = {
        {false, true, true, true, false},
        {true, false, false, true, true},
        {true, false, false, true, false},
        {false, true, true, false, true},
        {false, true, false, true, false},
    };

    // This is not going to be used cuz the graph is unweighted; I'm too lazy to make another class
    // for unweighted graph lol
    double[][] weight = {
        {0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0},
        {0.0, 0.0, 0.0, 0.0, 0.0},
    };

    Graph graph1 = new Graph(adj1, weight);
    Graph graph2 = new Graph(adj2, weight);

    System.out.println(HamiltonCircuitWithBellmanHeldKarpAlgorithm(graph1));
    System.out.println(HamiltonCircuitWithBellmanHeldKarpAlgorithm(graph2));
  }

  // The graph is undirected, unweighted and simple.
  public static boolean HamiltonCircuitWithBellmanHeldKarpAlgorithm(Graph graph) {
    int size = graph.getSize() - 1;  // We use the largest node as the start
    boolean[] set = new boolean[size];  // Contains integer from 0 to (size - 2) representing nodes
    // For example, {true, false, false, true} represent the set {0, 3}
    long subsetIndex = 0;
    // A natural correspondence between a set of natural numbers < n and a binary n-digit number
    // Since I am using long here, the maximum number of nodes allowed is 64
    // I could've changed it to BigInteger, however this is of no real value since for a graph with
    // that many nodes, the algorithm is already impractical.
    Map<Pair, Boolean> h = new HashMap<>();
    // The H function; Pair takes a long representing subset and an int representing node

    // Initialisation
    for (int i = 0; i < size; i++) {
      h.put(new Pair(subsetIndex, i), graph.getAdjacencyMatrix()[size][i]);
    }

    while (nextSet(set)) {
      subsetIndex++;
      // subsetIndex is updated correspondingly with nextSet. For example, when the set becomes
      // {true, false, false, true} or {0, 3}, subsetIndex = 2^0 + 2^3 = 9.
      for (int i = 0; i < size; i++) {
        if (set[i]) {
          continue;
        }

        Pair key = new Pair(subsetIndex, i);
        h.put(key, false);
        for (int j = 0; j < size; j++) {
          if (set[j]) {
            long setWithoutJ = subsetIndex - (1L << j);
            // This number represents the current subset removing j
            boolean value =
                h.get(key)
                    || (h.get(new Pair(setWithoutJ, j))
                        && graph.getAdjacencyMatrix()[i][j]);
            h.put(key, value);
          }
        }
      }
    }

    for (int i = 0; i < size; i++) {
      if (h.get(new Pair(subsetIndex - (1L << i), i)) && graph.getAdjacencyMatrix()[i][size]) {
        return true;
      }
    }

    return false;
  }

  // Generates the "next" subset; when the given set is full (e.g. all is true), returns false;
  // Guaranteed to go through all possible subset
  private static boolean nextSet(boolean[] set) {
    for (int i = 0; i < set.length; i++) {
      if (!set[i]) {
        set[i] = true;
        return true;
      }
      set[i] = false;
    }
    return false;
  }
}

class Pair {
  final long setIndex;
  final int nodeIndex;

  public Pair(long setIndex, int nodeIndex) {
    this.setIndex = setIndex;
    this.nodeIndex = nodeIndex;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Pair) {
      return ((Pair) other).setIndex == setIndex && ((Pair) other).nodeIndex == nodeIndex;
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Long.valueOf(setIndex).hashCode();
  }

  @Override
  public String toString() {
    return setIndex + ": " + nodeIndex;
  }
}

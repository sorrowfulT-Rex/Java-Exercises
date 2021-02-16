package coursework;

import java.util.Arrays;

public class WidestPath {

  // The main function demonstrates the algorithm on a particular graph.
  // I've included a photo of that graph in the PDF.
  public static void main(String[] args) {
    boolean[][] adj = {
      {false, true, false, false, false, false},
      {false, false, true, false, false, false},
      {false, false, false, true, true, false},
      {false, false, false, false, true, false},
      {false, false, false, false, false, false},
      {false, true, false, true, true, false},
    };

    double[][] weight = {
      {0.0, 5.0, 0.0, 0.0, 0.0, 0.0},
      {0.0, 0.0, 50.0, 0.0, 0.0, 0.0},
      {0.0, 0.0, 0.0, 20.0, 10.0, 0.0},
      {5.0, 0.0, 0.0, 0.0, 60.0, 0.0},
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
      {0.0, 10.0, 0.0, 30.0, 100.0, 0.0}
    };

    Graph graph = new Graph(adj, weight);

    var res = WidestPathWithFloydsAlgorithm(graph);

    for (int i = 0; i < graph.getSize(); i++) {
      for (int j = 0; j < graph.getSize(); j++) {
        System.out.println("Bandwidth from " + (i + 1) + " to " + (j + 1) + " is " + res[i][j]);
      }
    }
  }

  public static double[][] WidestPathWithFloydsAlgorithm(Graph graph) {
    double[][] res = graph.getWeightMatrix().clone();
    // Initialisation
    for (int i = 0; i < graph.getSize(); i++) {
      for (int j = 0; j < graph.getSize(); j++) {
        if (graph.getAdjacencyMatrix()[i][j]) {
          res[i][j] = graph.getWeightMatrix()[i][j];
        } else {
          res[i][j] = 0;
        }
      }
    }

    // Algorithm
    for (int i = 0; i < graph.getSize(); i++) {
      for (int j = 0; j < graph.getSize(); j++) {
        for (int k = 0; k < graph.getSize(); k++) {
          res[i][j] = Math.max(res[i][j], Math.min(res[i][k], res[k][j]));
        }
      }
    }

    return res;
  }
}

class Graph {

  private final boolean[][] adjacencyMatrix; // A[i][j] is true iff there is a directed path i -> j
  private final double[][] weightMatrix; // W[i][j] is the weight of the path i -> j

  public Graph(boolean[][] adjacencyMatrix, double[][] weightMatrix) {
    if (adjacencyMatrix.length == 0 || weightMatrix.length == 0) {
      throw new IllegalArgumentException("Invalid Graph!");
    }

    if (adjacencyMatrix.length != adjacencyMatrix[0].length
        || weightMatrix.length != weightMatrix[0].length
        || adjacencyMatrix.length != weightMatrix.length) {
      throw new IllegalArgumentException("Invalid Graph!");
    }

    this.adjacencyMatrix = adjacencyMatrix;
    this.weightMatrix = weightMatrix;
  }

  public boolean[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }

  public double[][] getWeightMatrix() {
    return weightMatrix;
  }

  public int getSize() {
    return adjacencyMatrix.length;
  }
}

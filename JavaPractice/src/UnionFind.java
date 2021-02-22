import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind<S> {

  private final List<S> nodes;
  private final Map<S, S> parents;
  private final Map<S, Integer> depth;

  public UnionFind(List<S> nodes) {
    this(nodes, new HashMap<>());
  }

  public UnionFind(List<S> nodes, Map<S, S> parents) {
    this.nodes = nodes;
    this.parents = parents;
    this.depth = new HashMap<>();

    for (var node : nodes) {
      if (parent(node) == null) {
        parents.put(node, node);
      }
    }

    for (var node : nodes) {
      calDepth(node);
    }
  }

  public static void main(String[] args) {

    UnionFind<Integer> intUF =
        new UnionFind<>(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    intUF.union(2, 3);
    intUF.union(3, 5);
    intUF.union(4, 1);
    intUF.union(9, 8);
    intUF.union(1, 1);
    System.out.println(intUF);
  }

  public S find(S node) {
    var rep = node;
    if (rep.equals(parent(rep))) {
      return rep;
    }

    var root = find(parent(node));
    parents.put(node, root);
    return root;
  }

  public void union(S node1, S node2) {
    if (find(node1).equals(find(node2))) {
      return;
    }

    if (depth.get(node1) < depth.get(node2)) {
      union(node2, node1);
      return;
    }

    parents.put(node2, node1);
  }

  private S parent(S node) {
    return parents.get(node);
  }

  private int calDepth(S node) {
    if (depth.containsKey(node)) {
      return depth.get(node);
    }
    if (node.equals(parent(node))) {
      depth.put(node, 0);
      return 0;
    }
    depth.put(node, 1 + calDepth(parent(node)));
    return depth.get(node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (var n : nodes) {
      sb.append(n.toString()).append(": ").append(find(n).toString()).append(", ");
    }

    return sb.substring(0, sb.length() - 2);
  }
}

import java.util.*;

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
      calDepth(node, 0);
    }
  }

  public static void main(String[] args) {

    UnionFind<Integer> intUF =
        new UnionFind<>(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    intUF.union(2, 1);
    intUF.union(4, 3);
    intUF.union(2, 4);
    intUF.union(3, 1);
    intUF.union(7, 9);
    System.out.println(intUF);
    System.out.println(intUF.getReps());
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
    var rep1 = find(node1);
    var rep2 = find(node2);
    if (rep1.equals(rep2)) {
      return;
    }

    if (depth.get(rep1) < depth.get(rep2)) {
      union(rep2, rep1);
      return;
    }
    depth.put(rep1, Math.max(depth.get(rep1), depth.get(rep2) + 1));
    depth.remove(rep2);
    parents.put(node2, node1);
  }

  public Set<S> getReps() {
    return depth.keySet();
  }

  private S parent(S node) {
    return parents.get(node);
  }

  private void calDepth(S node, int acc) {
    if (node.equals(parent(node))) {
      if (depth.getOrDefault(node, 0) <= acc) {
        depth.put(node, acc);
      }
      return;
    }

    calDepth(parent(node), acc + 1);
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

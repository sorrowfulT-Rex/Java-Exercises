// e6fd: Bit sets
/*
 * An n-bit binary number can be used to represent a set of integers in the range [0,n-1].
 * This can be achieved by setting bit i of the number to 1 if and only if i belongs to the set.
 * Such a representation of a set is called a bit set.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface BitSet<S> {

  S getSet();

  // Add x to the bit set
  // Throw a runtime exception if x is not in range
  void add(int x);

  // If x belongs to the bit set, remove it
  void remove(int x);

  // Return true iff x belongs to the bit set
  boolean contains(int x);

  // Update the bit set to contain only those values
  // also present in s
  void intersectWith(BitSet<S> s);

  // Return the maximum value that the bit set
  // can represent
  int maxStorableValue();

  List<Integer> elements();
}

class BitSet8 implements BitSet<Byte> {
  public static final int SIZE = 8;
  private byte set = 0;

  @Override
  public Byte getSet() {
    return set;
  }

  @Override
  public void add(int x) {
    if (x < 0 || x >= SIZE) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }

    set = (byte) (set | (1 << x));
  }

  @Override
  public void remove(int x) {
    if (x < 0 || x >= SIZE) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }

    set = (byte) (set & ~(1 << x));
  }

  @Override
  public boolean contains(int x) {
    return ((1 << x) & set) != 0;
  }

  @Override
  public void intersectWith(BitSet<Byte> s) {
    set = (byte) (set & s.getSet());
  }

  @Override
  public int maxStorableValue() {
    return SIZE - 1;
  }

  @Override
  public List<Integer> elements() {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < SIZE; i++) {
      if (contains(i)) {
        res.add(i);
      }
    }

    return res;
  }
}

class BitSet64 implements BitSet<Long> {
  public static final int SIZE = 64;
  private long set = 0;

  public BitSet64() {
    this(0);
  }

  public BitSet64(long set) {
    this.set = set;
  }

  @Override
  public Long getSet() {
    return set;
  }

  @Override
  public void add(int x) {
    if (x < 0 || x >= SIZE) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }

    set = set | (1L << x);
  }

  @Override
  public void remove(int x) {
    if (x < 0 || x >= SIZE) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }

    set = set & ~(1L << x);
  }

  @Override
  public boolean contains(int x) {
    return ((1L << x) & set) != 0;
  }

  @Override
  public void intersectWith(BitSet<Long> s) {
    set = set & s.getSet();
  }

  @Override
  public int maxStorableValue() {
    return SIZE - 1;
  }

  @Override
  public List<Integer> elements() {
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < SIZE; i++) {
      if (contains(i)) {
        res.add(i);
      }
    }

    return res;
  }
}

class BitSetArray implements BitSet<List<Long>> {
  private final int size;
  private final List<Long> set;

  public BitSetArray(int size) {
    if (size < BitSet64.SIZE) {
      size = BitSet64.SIZE;
    }

    this.size = size;
    this.set = new ArrayList<>();
    for (int i = 0; i < (int) Math.ceil((double) size / BitSet64.SIZE); i++) {
      set.add(0L);
    }
  }

  public int getSection(int x) {
    return x / BitSet64.SIZE;
  }

  @Override
  public List<Long> getSet() {
    return set;
  }

  @Override
  public void add(int x) {
    try {
      var section = new BitSet64(set.get(getSection(x)));
      section.add(x % BitSet64.SIZE);
      set.set(getSection(x), section.getSet());
    } catch (Exception e) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }
  }

  @Override
  public void remove(int x) {
    try {
      var section = new BitSet64(set.get(getSection(x)));
      section.remove(x % BitSet64.SIZE);
      set.set(getSection(x), section.getSet());
    } catch (Exception e) {
      throw new IllegalArgumentException("The element is beyond the storage capability!");
    }
  }

  @Override
  public boolean contains(int x) {
    if (x < 0 || x > maxStorableValue()) {
      return false;
    }

    return new BitSet64(set.get(getSection(x))).contains(x % BitSet64.SIZE);
  }

  @Override
  public void intersectWith(BitSet<List<Long>> s) {
    int sections = Math.min(set.size(), s.getSet().size());

    for (int i = 0; i < sections; i++) {
      var intersection = new BitSet64(set.get(i));
      intersection.intersectWith(new BitSet64(s.getSet().get(i)));
      set.set(i, intersection.getSet());
    }
  }

  @Override
  public int maxStorableValue() {
    return size - 1;
  }

  @Override
  public List<Integer> elements() {
    List<Integer> res = new ArrayList<>();
    int offset = 0;
    for (var seg : set) {
      int finalOffset = offset;
      res.addAll(
          new BitSet64(seg)
              .elements().stream().map(it -> it + finalOffset).collect(Collectors.toList()));

      offset += BitSet64.SIZE;
    }

    return res;
  }
}

public class _e6fd {

  static void test8() {
    BitSet8 set8 = new BitSet8();
    set8.add(1);
    set8.add(3);
    set8.add(5);
    System.out.println(set8.contains(2));
    System.out.println(set8.contains(3));
    System.out.println(set8.contains(5));
    System.out.println(set8.contains(0));
    try {
      set8.add(10);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(set8.elements());
  }

  static void test64() {
    BitSet64 set64 = new BitSet64();
    set64.add(11);
    set64.add(33);
    set64.add(55);
    System.out.println(set64.contains(23));
    System.out.println(set64.contains(53));
    System.out.println(set64.contains(55));
    System.out.println(set64.contains(2432));
    try {
      set64.add(100);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    System.out.println(set64.elements());
  }

  static void test1024() {
    BitSetArray s1 = new BitSetArray(512);
    BitSetArray s2 = new BitSetArray(1024);
    s1.add(101);
    s1.add(202);
    s1.add(303);
    s1.add(404);
    s2.add(101);
    s2.add(212);
    s2.add(998);
    System.out.println(s1.contains(202));
    System.out.println(s1.contains(203));
    System.out.println(s1.contains(998));
    System.out.println(s1.elements());
    s1.intersectWith(s2);
    System.out.println(s1.contains(202));
    System.out.println(s1.contains(101));
    System.out.println(s1.elements());
    System.out.println(s2.elements());
  }

  public static void main(String[] args) {
    test8();
    test64();
    test1024();
  }
}

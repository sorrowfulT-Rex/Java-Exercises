package tutorialquestion;

import java.awt.*;

public class _0f05 {

  public static void main(String[] args) {
	  Point p = new Point(2, 3, 6);
    System.out.println(p.magnitude());
    System.out.println(p);

    Point op = new ColouredPoint(2, 3, 6, Color.BLUE);
    System.out.println(op);
  }
}

class Point {

	private final double x;
	private final double y;
	private final double z;

	public static Point Origin = new Point(0, 0, 0);

	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getZ() {
		return z;
	}

	public double getY() {
		return y;
	}

	public double getX() {
		return x;
	}

	public double distance(Point o) {
		return Math.sqrt(Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2) + Math.pow(z - o.getZ(), 2));
	}

	public double magnitude() {
		return distance(Origin);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
}

class ColouredPoint extends Point {

	private final Color colour;
	public ColouredPoint(double x, double y, double z, Color colour) {
		super(x, y, z);
		this.colour = colour;
	}

	@Override
	public String toString() {
		return super.toString() + "; RGB: " + colour.getRed() + ", " + colour.getGreen() + ", " + colour.getBlue();
	}
}

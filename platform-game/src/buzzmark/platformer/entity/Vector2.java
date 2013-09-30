package buzzmark.platformer.entity;

public class Vector2 {
	
	private int x, y;
	private double xRemainder, yRemainder;
	
	public static Vector2 spawnPosition = new Vector2(19 * 16, 7 * 16);
	
	public Vector2(int x, int y) {
		this(x, y, 0, 0);
	}
	
	public Vector2(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public Vector2(int x, int y, double xRemainder, double yRemainder) {
		this.x = x;
		this.y = y;
		this.xRemainder = xRemainder;
		this.yRemainder = yRemainder;
	}
	
	public int getXInt() {
		return x;
	}
	
	public int getYInt() {
		return y;
	}
	
	public double getX() {
		return x + xRemainder;
	}
	
	public void setX(double x) {
		this.x = (int) x;
		xRemainder = x - this.x;
	}
	
	public double getY() {
		return y + yRemainder;
	}
	
	public void setY(double y) {
		this.y = (int) y;
		yRemainder = y - this.y;
	}
	
	public void addToX(double amount) {
		xRemainder += amount;
		x += (int) xRemainder;
		xRemainder -= (int) xRemainder;
	}
	
	public void addToY(double amount) {
		yRemainder += amount;
		y += (int) yRemainder;
		yRemainder -= (int) yRemainder;
	}
	
	public void add(Vector2 other) {
		addToX(other.getX());
		addToY(other.getY());
	}
	
	public void sub(Vector2 other) {
		x -= other.getXInt();
		y -= other.getYInt();
	}
	
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}

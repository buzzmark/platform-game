package buzzmark.platformer.entity;

public class CollisionBox {
	
	private Vector2 referencePosition;
	private int xOffset, yOffset;
	private int width, height;
	
	public CollisionBox(int xOffset, int yOffset, int width, int height) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
	}
	
	public void init(Vector2 refPos) {
		referencePosition = refPos;
	}
	
	public int getXLeft() {
		return referencePosition.getXInt() + xOffset;
	}
	
	public void setXLeft(double x) {
		referencePosition.setX(x - xOffset);
	}
	
	public int getXRight() {
		return referencePosition.getXInt() + xOffset + width - 1;
	}
	
	public void setXRight(double x) {
		referencePosition.setX(x - xOffset - width + 1);
	}
	
	public int getYTop() {
		return referencePosition.getYInt() + yOffset;
	}
	
	public void setYTop(double y) {
		referencePosition.setY(y - yOffset);
	}
	
	public int getYBottom() {
		return referencePosition.getYInt() + yOffset + height - 1;
	}
	
	public void setYBottom(double y) {
		referencePosition.setY(y - yOffset - height + 1);
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Vector2 getBoxPosition(){
		return new Vector2(getXLeft(), getYTop());
	}
	
}

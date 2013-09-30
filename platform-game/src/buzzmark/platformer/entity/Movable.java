package buzzmark.platformer.entity;

import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.graphics.Sprite;

public abstract class Movable extends Entity {
	
	public static final double GRAVITY = 0.17;
	
	protected Vector2 velocity;
	protected CollisionBox collisionBox;
	private Sprite cbSprite; //TODO remove when no longer needed
	protected boolean effectedByGravity, isInAir;
	protected boolean canJump; //TODO move to subclass
	protected int dir; // 0 = right, 1 = left
	
	// public Movable(Vector2 pos) {
	// this(pos, null);
	// }
	
	public Movable(Vector2 pos, CollisionBox collisionBox) {
		super(pos);
		velocity = new Vector2(0, 0);
		this.collisionBox = collisionBox;
		effectedByGravity = true;
		canJump = false;
		isInAir = true;
		dir = 0;
		collisionBox.init(getPosition());
		cbSprite = new Sprite(collisionBox.getWidth(), collisionBox.getHeight(), 0xFF0000);
		
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void setVelocity(Vector2 v) {
		velocity = v;
	}
	
	public int getDx() {
		return velocity.getXInt();
	}
	
	public int getDy() {
		return velocity.getYInt();
	}
	
	public void render(Screen screen) {
		screen.renderSprite(collisionBox.getBoxPosition(), cbSprite);
	}
	
	public void update() {
		if (effectedByGravity) {
			velocity.addToY(GRAVITY);
		}
		move();
	}
	
	private void move() {
		double dx = velocity.getX(), dy = velocity.getY();
		
		if (dx != 0.0) {
			double destination = (position.getX() + dx);
			moveX(destination);
			if (dx < 0.0)
				dir = 1;
			else
				dir = 0;
		}
		
		if (dy != 0) {
			double destination = (position.getY() + dy);
			moveY(destination);
		}
	}
	
	private void moveX(double destination) { // check each block in movement range to see if solid
		int xOffset = collisionBox.getXOffset();
		int cbDestination = (int) destination + xOffset;
		double cbDestinationRemainder = destination + xOffset - cbDestination;
		int yTop = collisionBox.getYTop(), yBottom = collisionBox.getYBottom(), xLeft = collisionBox.getXLeft(), xRight = collisionBox.getXRight();
		
		if (velocity.getX() < 0.0) {
			int lDest = (int) destination + xOffset;
			for (int xt = lDest >> 4 << 4; xt < xLeft - 15; xt += 16) { // scan from the destination tile towards the moving object
				for (int yt = yTop >> 4 << 4; yt <= yBottom >> 4 << 4; yt += 16) { // check tiles between yTop & yBottom
					if (level.getTile(xt >> 4, yt >> 4).isSolid() || xt < 0.0) {
						lDest = xt + 16;
						cbDestinationRemainder = 0.0;
						break;
					}
				}
			}
			double result = lDest + cbDestinationRemainder;
			collisionBox.setXLeft(result);
			
		} else if (velocity.getX() > 0.0) {
			int rDest = (int) destination + xOffset + collisionBox.getWidth() - 1;
			for (int xt = rDest >> 4 << 4; xt > xRight; xt -= 16) { // scan from the destination towards the moving object
				for (int yt = yTop >> 4 << 4; yt <= yBottom >> 4 << 4; yt += 16) { // check tiles between yTop & yBottom
					if (level.getTile(xt >> 4, yt >> 4).isSolid()) {
						rDest = xt - 1;
						cbDestinationRemainder = 0.0;
						break;
					}
				}
			}
			double result = rDest + cbDestinationRemainder;
			collisionBox.setXRight(result);
		}
	}
	
	private void moveY(double destination) {
		int yOffset = collisionBox.getYOffset();
		int cbDestination = (int) destination + yOffset;
		double cbDestinationRemainder = destination + yOffset - cbDestination;
		int yTop = collisionBox.getYTop(), yBottom = collisionBox.getYBottom(), xLeft = collisionBox.getXLeft(), xRight = collisionBox.getXRight();
		
		if (velocity.getY() < 0.0) {
			int tDest = (int) destination + yOffset;
			for (int yt = tDest >> 4 << 4; yt < yTop - 15; yt += 16) {
				for (int xt = xLeft >> 4 << 4; xt <= xRight >> 4 << 4; xt += 16) {
					if (level.getTile(xt >> 4, yt >> 4).isSolid() || yt < 0.0) {
						tDest = yt + 16;
						cbDestinationRemainder = 0.0;
						velocity.setY(0.0);
						break;
					}
				}
			}
			double result = tDest + cbDestinationRemainder;
			collisionBox.setYTop(result);
			
		} else if (velocity.getY() > 0.0) {
			int bDest = (int) destination + yOffset + collisionBox.getHeight() - 1;
			for (int yt = bDest >> 4 << 4; yt > yBottom; yt -= 16) {
				for (int xt = xLeft >> 4 << 4; xt <= xRight >> 4 << 4; xt += 16) {
					if (level.getTile(xt >> 4, yt >> 4).isSolid()) {
						bDest = yt - 1;
						cbDestinationRemainder = 0.0;
						velocity.setY(0.0);
						canJump = true;
						isInAir = false;
						break;
					}
				}
			}
			double result = bDest + cbDestinationRemainder;
			if (yBottom != result) {
				isInAir = true;
				collisionBox.setYBottom(result);
			}
		}
	}
	
}

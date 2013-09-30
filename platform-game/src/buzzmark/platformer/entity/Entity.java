package buzzmark.platformer.entity;

import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.level.Level;

public abstract class Entity {
	
	protected Vector2 position;
	protected Level level;
	
	public Entity(Vector2 pos) {
		position = pos;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	public void remove() {
		// TODO remove from level
	}
	
	public int getX() {
		return position.getXInt();
	}
	
	public int getY() {
		return position.getYInt();
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
}

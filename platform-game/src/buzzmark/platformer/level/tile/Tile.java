package buzzmark.platformer.level.tile;

import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.graphics.Sprite;

public class Tile {
	protected int x, y;
	protected Sprite sprite;
	
	public static Tile emptyTile = new EmptyTile(Sprite.empty);
	public static Tile skyTile = new BackgroundTile(Sprite.sky);
	
	public static Tile GrassTile = new GrassTile(Sprite.grass);
	public static Tile DirtTile = new DirtTile(Sprite.dirt);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public boolean isSolid() {
		return true;
	}
}

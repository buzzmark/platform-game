package buzzmark.platformer.graphics;

import buzzmark.platformer.entity.Vector2;
import buzzmark.platformer.level.tile.Tile;

public class Screen {
	
	private int width, height;
	private int[] pixels;
	public int xOffset, yOffset;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	public void renderSprite(Vector2 pos, Sprite sprite) {
		int xp = pos.getXInt() - xOffset;
		int yp = pos.getYInt() - yOffset;
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int color = sprite.getPixels()[x + y * sprite.getWidth()];
				if (color != 0xFFFF00FF)
					pixels[xa + ya * width] = color;
			}
		}
	}
	
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.getSprite().getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.getSprite().getWidth(); x++) {
				int xa = x + xp;
				if (xa < -tile.getSprite().getWidth() || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.getSprite().getPixels()[x + y * tile.getSprite().getWidth()];
			}
		}
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
}

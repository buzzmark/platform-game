package buzzmark.platformer.graphics;

public class Sprite {
	
	private int width, height;
	private int[] pixels;
	
	public static Sprite empty = new Sprite(16, 16, 0, 0, SpriteSheet.tileSheet);
	public static Sprite sky = new Sprite(16, 16, 0x8CEFFF);
	public static Sprite grass = new Sprite(16, 16, 2 * 16, 0, SpriteSheet.tileSheet);
	public static Sprite dirt = new Sprite(16, 16, 1 * 16, 0, SpriteSheet.tileSheet);
	
	public static Sprite player_right = new Sprite(16, 32, 0, 3 * 16, SpriteSheet.tileSheet);
	public static Sprite player_left = new Sprite(16, 32, 1 * 16, 3 * 16, SpriteSheet.tileSheet);
	
	public Sprite(int width, int height, int color) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int i = 0; i < width * height; i++)
			pixels[i] = color;
	}
	
	/**
	 * Create a Sprite using the given SpriteSheet. Coordinates are in pixels with the top left corner as (0,0).
	 */
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		for (int yp = 0; yp < height; yp++) {
			for (int xp = 0; xp < width; xp++) {
				pixels[xp + yp * width] = sheet.getPixels()[(xp + x) + (yp + y) * sheet.getWidth()];
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
}

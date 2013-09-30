package buzzmark.platformer.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	private int width, height;
	private int[] pixels;
	
	public static SpriteSheet tileSheet = new SpriteSheet("/textures/tiles.png");
	public static SpriteSheet playerSheet = new SpriteSheet("/textures/player.png");
	
	public SpriteSheet(String path) {
		this.path = path;
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public String getPath() {
		return path;
	}
}
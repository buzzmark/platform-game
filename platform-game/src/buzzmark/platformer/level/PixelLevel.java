package buzzmark.platformer.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import buzzmark.platformer.graphics.Sprite;
import buzzmark.platformer.level.tile.Tile;

public class PixelLevel extends Level {
	
	protected String path;
	
	public static final int COLOR_BACKGROUND = 0xFFFFFFFF;
	public static final int COLOR_GRASS = 0xFF007F0E;
	public static final int COLOR_DIRT = 0xFF7F3300;
	
	public PixelLevel(String path) {
		this.path = path;
		
		try {
			BufferedImage image = ImageIO.read(PixelLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.emptyTile;
		int tileColor = tiles[x + y * width];
		if (tileColor == COLOR_BACKGROUND)
			return Tile.skyTile;
		if (tileColor == COLOR_GRASS)
			return Tile.GrassTile;
		if (tileColor == COLOR_DIRT)
			return Tile.DirtTile;
		
		return new Tile(new Sprite(16, 16, tiles[x + y * width]));
	}
	
}

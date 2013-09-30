package buzzmark.platformer.level;

import java.util.Random;
import buzzmark.platformer.graphics.Sprite;
import buzzmark.platformer.level.tile.Tile;

public class RandomLevel extends Level {
	
	private static final Random random = new Random();
	
	public RandomLevel(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		for (int y=0;y<height;y++){
			for(int x=0;x<width;x++){
				tiles[x+y*width]=random.nextInt(0xFFFFFF);
			}
		}
	}

	@Override
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return Tile.emptyTile;
		return new Tile(new Sprite(16, 16, tiles[x + y * width]));
	}
	
}

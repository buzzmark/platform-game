package buzzmark.platformer.level;

import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.level.tile.Tile;

public abstract class Level {
	
	protected int width, height;
	protected int[] tiles;
	
	public Level() {
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.getWidth() + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.getHeight() + 16) >> 4;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
				
			}
		}
	}
	
	public abstract Tile getTile(int x, int y);	
}

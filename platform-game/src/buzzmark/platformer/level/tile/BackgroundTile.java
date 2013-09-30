package buzzmark.platformer.level.tile;

import buzzmark.platformer.graphics.Sprite;

public class BackgroundTile extends Tile {

	public BackgroundTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean isSolid(){
		return false;
	}
	
}

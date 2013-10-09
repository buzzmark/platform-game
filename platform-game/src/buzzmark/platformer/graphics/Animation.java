package buzzmark.platformer.graphics;

import buzzmark.platformer.entity.Vector2;

public class Animation {
	
	private int animTimer = 0;
	private Sprite[] sprites;
	private int[] durations;
	private int totalduration = 0;
	
	/**
	 * Creates a new animation. Note that both arrays should be the same length. Make sure to call update whenever the game updates.
	 * 
	 * @param sprites
	 *            An array of sprites in the order of the desired animation
	 * @param durations
	 *            An array of the lengths of time to display each sprite of corresponding index
	 */
	public Animation(Sprite[] sprites, int[] durations) {
		this.sprites = sprites;
		this.durations = durations;
		for (int dur : durations) {
			totalduration += dur;
		}
	}
	
	public void update() {
		if (animTimer < totalduration - 1)
			animTimer++;
		else
			animTimer = 0;
	}
	
	public void reset() {
		animTimer = 0;
	}
	
	public void render(Vector2 pos, Screen screen) {
		for (int i = 0, total = 0; i < sprites.length; i++) {
			total += durations[i];
			if (animTimer < total) {
				screen.renderSprite(pos, sprites[i]);
				break;
			}
			
		}
	}
	
	
}

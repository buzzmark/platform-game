package buzzmark.platformer.entity;

import java.awt.event.KeyEvent;
import buzzmark.platformer.graphics.Animation;
import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.graphics.Sprite;
import buzzmark.platformer.graphics.SpriteSheet;
import buzzmark.platformer.utils.Keyboard;

public class Player extends Movable {
	
	private Keyboard keyboard;
	// private Sprite sprite;
	private Animation animation;
	private Animation runningLeft;
	private Animation runningRight;
	private Animation idle;
	private double movespeed = 1.5;
	private double jumpspeed = -4.0;
	
	public Player(CollisionBox collisionBox, Keyboard keyboard) {
		this(Vector2.spawnPosition, collisionBox, keyboard);
	}
	
	public Player(Vector2 pos, CollisionBox collisionBox, Keyboard keyboard) {
		super(pos, collisionBox);
		position = pos;
		this.keyboard = keyboard;
		Sprite[] playerRunningRight = new Sprite[6];
		for (int i = 0; i < 6; i++) {
			playerRunningRight[i] = new Sprite(16, 32, i * 16, 1 * 32, SpriteSheet.playerSheet);
		}
		int step = 7;
		runningRight = new Animation(playerRunningRight, new int[] { step, step, step, step, step, step });
		runningLeft = runningRight; // TODO
		
		Sprite[] playerIdle = new Sprite[2];
		playerIdle[0] = new Sprite(16, 32, 16, 0, SpriteSheet.playerSheet);
		playerIdle[1] = new Sprite(16, 32, 0, 0, SpriteSheet.playerSheet);
		
		animation = idle = new Animation(playerIdle, new int[] { 40, 40 });
	}
	
	public void jump() {
		velocity.addToY(jumpspeed);
		canJump = false;
	}
	
	@Override
	public void update() {
		if (keyboard.isKeyPressed(KeyEvent.VK_A) || keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			velocity.setX(-movespeed);
			if (animation != runningLeft) {
				animation.reset();
				animation = runningLeft;
			}
		} else if (keyboard.isKeyPressed(KeyEvent.VK_D) || keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			velocity.setX(movespeed);
			if (animation != runningRight) {
				animation.reset();
				animation = runningRight;
			}
		} else {
			velocity.setX(0.0);
			if (animation != idle) {
				animation.reset();
				animation = idle;
			}
		}
		animation.update();
		
		if (keyboard.isKeyPressed(KeyEvent.VK_SPACE) && canJump)
			jump();
		
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		// screen.renderSprite(position, sprite);
		animation.render(position, screen);
		// super.render(screen); // TODO remove later
	}
	
}

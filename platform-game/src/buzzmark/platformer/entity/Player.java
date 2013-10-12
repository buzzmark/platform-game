package buzzmark.platformer.entity;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import buzzmark.platformer.graphics.Animation;
import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.graphics.Sprite;
import buzzmark.platformer.graphics.SpriteSheet;
import buzzmark.platformer.utils.Keyboard;

public class Player extends Movable {
	
	private Keyboard keyboard;
	private Animation currentAnimation;
	private final Animation runLeftAnimation, runRightAnimation;
	private final Animation idleLeftAnimation, idleRightAnimation;
	private final Animation fallLeftAnimation, fallRightAnimation;
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
		Sprite[] playerRunningLeft = new Sprite[6];
		for (int i = 5; i >= 0; i--) {
			playerRunningLeft[5 - i] = new Sprite(16, 32, i * 16, 2 * 32, SpriteSheet.playerSheet);
		}
		
		int[] steps = new int[6];
		Arrays.fill(steps, 7);
		runRightAnimation = new Animation(playerRunningRight, steps);
		runLeftAnimation = new Animation(playerRunningLeft, steps);
		
		Sprite[] playerIdleRight = new Sprite[2];
		playerIdleRight[0] = new Sprite(16, 32, 0 * 16, 0, SpriteSheet.playerSheet);
		playerIdleRight[1] = new Sprite(16, 32, 1 * 16, 0, SpriteSheet.playerSheet);
		idleRightAnimation = new Animation(playerIdleRight, new int[] { 40, 40 });
		
		Sprite[] playerIdleLeft = new Sprite[2];
		playerIdleLeft[0] = new Sprite(16, 32, 5 * 16, 0, SpriteSheet.playerSheet);
		playerIdleLeft[1] = new Sprite(16, 32, 4 * 16, 0, SpriteSheet.playerSheet);
		idleLeftAnimation = new Animation(playerIdleLeft, new int[] { 40, 40 });
		
		Sprite[] playerFallRight = { new Sprite(16, 32, 2 * 16, 0, SpriteSheet.playerSheet) };
		fallRightAnimation = new Animation(playerFallRight, new int[] { 40 });
		
		Sprite[] playerFallLeft = { new Sprite(16, 32, 3 * 16, 0, SpriteSheet.playerSheet) };
		fallLeftAnimation = new Animation(playerFallLeft, new int[] { 40 });
		
		currentAnimation = idleRightAnimation;
	}
	
	public void jump() {
		velocity.addToY(jumpspeed);
		canJump = false;
	}
	
	@Override
	public void update() {
		if (keyboard.isKeyPressed(KeyEvent.VK_A) || keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
			velocity.setX(-movespeed);
			if (!isInAir && currentAnimation != runLeftAnimation) {
				currentAnimation.reset();
				currentAnimation = runLeftAnimation;
			}
		} else if (keyboard.isKeyPressed(KeyEvent.VK_D) || keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
			velocity.setX(movespeed);
			if (!isInAir && currentAnimation != runRightAnimation) {
				currentAnimation.reset();
				currentAnimation = runRightAnimation;
			}
		} else {
			velocity.setX(0.0);
			if (!isInAir && direction == LEFT && currentAnimation != idleLeftAnimation) {
				currentAnimation.reset();
				currentAnimation = idleLeftAnimation;
			} else if (!isInAir && direction == RIGHT && currentAnimation != idleRightAnimation) {
				currentAnimation.reset();
				currentAnimation = idleRightAnimation;
			}
		}
		if (isInAir) {
			if (direction == LEFT && currentAnimation != fallLeftAnimation) {
				currentAnimation.reset();
				currentAnimation = fallLeftAnimation;
			} else if (direction == RIGHT && currentAnimation != fallRightAnimation) {
				currentAnimation.reset();
				currentAnimation = fallRightAnimation;
			}
		}
		currentAnimation.update();
		
		if (keyboard.isKeyPressed(KeyEvent.VK_SPACE) && canJump)
			jump();
		
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		currentAnimation.render(position, screen);
		// super.render(screen); // TODO remove later, renders collision box for testing
	}
	
}

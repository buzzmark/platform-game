package buzzmark.platformer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;
import buzzmark.platformer.entity.CollisionBox;
import buzzmark.platformer.entity.Player;
import buzzmark.platformer.graphics.Screen;
import buzzmark.platformer.level.Level;
import buzzmark.platformer.level.PixelLevel;
import buzzmark.platformer.utils.Keyboard;
import buzzmark.platformer.utils.Mouse;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final double TARGET_UPS = 60.0; // Desired number of game updates per second
	
	private static int width = 320;
	private static int height = width / 16 * 9;
	private static int scale = 4;
	private static String title = "Platform Test Game";
	
	private Thread thread;
	private JFrame frame;
	private Screen screen;
	private Keyboard keyboard;
	private Mouse mouse;
	
	private Level level;
	CollisionBox cb;
	private Player player;
	
	private volatile boolean running = false;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Game() {
		setPreferredSize(new Dimension(width * scale, height * scale));
		
		screen = new Screen(width, height);
		frame = new JFrame();
		
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		// level=new RandomLevel(width, height);
		level = new PixelLevel("/levels/test.png");
		
		cb = new CollisionBox(5, 6, 6, 26);// TODO put in Level.java
		player = new Player(cb, keyboard);// TODO put in Level.java
		player.init(level);// TODO put in Level.java
		
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public void stop() {
		running = false;
		frame.dispose();
		
		try {
//		System.out.println("joining...");
		thread.join();
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		
		//System.exit(0);
	}
	
	public void run() {
		long previousTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int updates = 0;
		int frames = 0;
		requestFocus();
		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / 1000000000.0 * TARGET_UPS; // change in time(in ns) times desired UPS
			previousTime = currentTime;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
	
	private void update() {
		player.update();
		if (keyboard.isKeyPressed(KeyEvent.VK_ESCAPE))
			stop();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		
		int xOffset = player.getX() - screen.getWidth() / 2;
		int yOffset = player.getY() - screen.getHeight() + 4 * 16;
		level.render(xOffset, yOffset, screen);
		player.render(screen);
		// TODO render methods here ^^^
		
		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.getPixels()[i];
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawString("Player: "+player.getPosition().toString(), 50, 50);
		g.drawString("Mouse: "+mouse.toString(), 50, 70);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
	
}

package buzzmark.platformer.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
	
	private boolean[] keysPressed = new boolean[256];
	
	@Override
	public void keyPressed(KeyEvent e) {
		keysPressed[e.getKeyCode() & 255] = true;
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		keysPressed[e.getKeyCode() & 255] = false;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public boolean isKeyPressed(int key){
		return keysPressed[key];
	}
	
}

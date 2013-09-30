package buzzmark.platformer.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	
	private int x = -1, y = -1;
	private boolean[] buttonsPressed = new boolean[32];
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		buttonsPressed[e.getButton() & 32] = true;
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		buttonsPressed[e.getButton() & 32] = false;
		
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
	
}

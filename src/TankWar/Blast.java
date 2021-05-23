package TankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blast {
	private int x, y;
	public static int width = 40;
	public static int height = 40;
	private TankFrame tf;
	private boolean living = false;
	private int step = 0;
	public Blast(int x, int y, TankFrame tf) {
		super();
		this.x = x;
		this.y = y;
		this.tf = tf;
	}
	public void paint(Graphics g) {
		if(!living)   return;
		g.drawImage(ResourceMgr.blasts[step++], x, y, width, height, null);
		if( step >= ResourceMgr.blasts.length ) {
			living = false;
			step = 0;
		}
	} 
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Blast.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Blast.height = height;
	}
	public boolean isLiving() {
		return living;
	}
	public void setLiving(boolean living) {
		this.living = living;
	}
	
}

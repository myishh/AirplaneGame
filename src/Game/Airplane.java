package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import Utility.Constant;
import Utility.GameUtil;

public class Airplane extends GameObject {
	
	boolean left, up, right, down;
	boolean live;
	
	public void draw(Graphics g) {
		if(live) {
			g.drawImage(img, (int)x, (int)y, null);
			move();
		}
	}
	
	public void move() {
		if(left && x >= 0 && x <= (Constant.GAME_WIDTH - width)) x -= speed;
		if(right && x >= 0 && x <= (Constant.GAME_WIDTH - width)) x += speed;
		if(up && y >= 30 && y <= (Constant.GAME_HEIGHT - height)) y-= speed;
		if(down && y >= 30 && y <= (Constant.GAME_HEIGHT - height)) y += speed;
	}
	
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			this.left = true;
			break;
		case 38:
			this.up = true;
			break;
		case 39:
			this.right = true;
			break;
		case 40:
			this.down = true;
			break;
		default:
			break;
		}
	}
	
	public void removeDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case 37:
			this.left = false;
			break;
		case 38:
			this.up = false;
			break;
		case 39:
			this.right = false;
			break;
		case 40:
			this.down = false;
			break;
		default:
			break;
		}
	}
	
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	//Constructor
	public Airplane(String path, double x, double y) {
		this.img = GameUtil.getImage(path);
		this.x = x;
		this.y = y;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.speed = 10;
		this.live = true;
	}
	
	//don't forget the blank constructor
	public Airplane() {
		
	}
	

}

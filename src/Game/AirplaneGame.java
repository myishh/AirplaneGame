package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import Utility.GameUtil;
import Utility.MyFrame;

/**
 * Airplane Game
 * @author Myishh
 *
 */

public class AirplaneGame extends MyFrame{
	
	Image bg = GameUtil.getImage("images/bg.jpg");
	
	Airplane player = new Airplane("images/planeGreen.png", 200, 200);
	
	//Use arraylsit to hold all bullets, then load the bulletList
	//Can not load bulletList in paint(), should load it in lanuchFrame();	 
	ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	Date startTime, endTime;
	Explode explode;
	
	//override the paint(), which is located in the Window class
	//due to the call back, paint() would be called automatically by the system.	
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		player.draw(g);

		//pick up each bullet from bulletList and draw them
		for(int i = 0; i < bulletList.size(); i++) {
			Bullet b = bulletList.get(i);
			b.draw(g);
			
			//test if plane collide with each of those bullets
			if(b.getRect().intersects(player.getRect())) {
//				System.out.println("Collision happening");
				player.setLive(false);	//player die
				
				if(explode == null) {
					endTime = new Date();
					explode = new Explode(player.x, player.y);
				}				
				explode.draw(g);
				break;
			}
		}
		
		if(!player.isLive()) {
			printInfo("Game Over", 100, Color.white, 160, 450, g);

			//calculate the life time of plane
			int period = (int)(endTime.getTime() - startTime.getTime())/1000;
			printInfo("Life Time: " + period + " seconds", 40, Color.white, 250, 520, g);
			printInfo("Press R to restart the game", 40, Color.green, 175, 640, g);
			
			//calculate the player's level
			switch(period/10) {
			case 0:
				printInfo("Your Level: Noob", 40, Color.white, 280, 580, g);
				break;
			case 1:
				printInfo("Your Level: Rookie", 40, Color.white, 280, 580, g);				
				break;
			case 2:
				printInfo("Your Level: Normal", 40, Color.white, 280, 580, g);				
				break;
			case 3:
				printInfo("Your Level: Expert", 40, Color.white, 280, 580, g);
				break;
			default:
				break;
			}
		}
		
		
	}
	
	public void printInfo(String info, int size, Color color, int x, int y, Graphics g) {
		Color pc = g.getColor();
		g.setColor(color);
		g.setFont(new Font("微软雅黑", Font.BOLD, size));
		g.drawString(info, x, y);
		
		g.setColor(pc);
	}
	
	/*
	-Monitor the keyboard to control the plane. It is defined as an inner class to use 
	the fields of out class.
	-After this step, we have to register it into the lanuchFrame() to use this class.
	How to register? Answer is, override the launchFrame() in AirplaneGame and add 
	classaddKeyListener(new KeyMonitor()) and . See the launchFrame() below.	
	 */
	class keyMonitor extends KeyAdapter{
		/*
		 * left: 37; up: 38; right: 39; down: 40; 
		 * enter: 10; R: 82;
		 */
		@Override
		public void keyPressed(KeyEvent e) {
//			System.out.println("Pressed: " + e.getKeyCode());
			player.addDirection(e);
			if(e.getKeyCode() == 82) new AirplaneGame().launchFrame();
		}

		@Override
		public void keyReleased(KeyEvent e) {
//			System.out.println("Released: " + e.getKeyCode());
			player.removeDirection(e);
		}
		
	}
	
	
	/*
	 override the launchFrame() in MyFrame because we can't add keyListener in MyFrame
	 directly. If so, all child class would obtain the key monitor, which is not good.
	 */
	@Override
	public void launchFrame() {
		//call the launchFrame in the parent class MyFrame
		super.launchFrame();
		
		//override the addListener() in Component.class to add the key monitor
		addKeyListener(new keyMonitor());
		
		//create a bunch of bullets
		for(int i = 0; i < 30; i++) {
			bulletList.add(new Bullet());
		}
		
		startTime = new Date();
		
	}



	public static void main(String[] args) {
		new AirplaneGame().launchFrame();
	}
	
	

}

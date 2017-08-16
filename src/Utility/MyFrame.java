package Utility;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Parent class
 * @author Myishh
 *
 */

public class MyFrame extends Frame{	//GUI program: awt, swing
	
//	Image bg = GameUtil.getImage("images/bg.jpg");
//	Image player = GameUtil.getImage("images/planeGreen.png");
//	private double x = 450, y = 450, r = 150;	//location of images
//	private double degree;	//[0, 2pi]
//	private double speed = 5;
	
	/*
	 * load window
	 */
	public void launchFrame() {
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		setLocation(50, 50);
		setVisible(true);
		
		//to close the window, add window listener and override window adapter
		addWindowListener(new WindowAdapter() {
			//windowAdapter is an inner class, we override it
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//start the paintThread. start() is a function in the Thread class
		new PaintThread().start();	//after declare the inner class, need to start thread
	}
	
	
	//override the paint(), which is located in the Window class
	//due to the call back, paint() would be called automatically by the system.
//	@Override
//	public void paint(Graphics g) {
//		g.drawImage(bg, 0, 0, null);
//		g.drawImage(player,(int)x, (int)y, null);
//		x = 450 + r * Math.cos(degree);
//		y = 450 + r * Math.sin(degree);
//		degree += Math.PI/100;
//
//	}
	
	//declare an inner class called PaintThread
	class PaintThread extends Thread {


		
		//override the run() of Thread class
		@Override
		public void run() {
			while(true) {
				repaint();	//repaint() is a funtion located in Component class
				try {
					Thread.sleep(30);	//1s == 1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//make the flash disappear by using the double cache
	//no need to understand this code, just remember and use it
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null)
			offScreenImage = this.createImage(900, 900);
		Graphics gOff = offScreenImage.getGraphics();
		
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);	
	}


//	public static void main(String[] args) {
//		MyFrame fr = new MyFrame();
//		fr.launchFrame();
//	}
	

}

package Game;

import java.awt.Image;
import Utility.GameUtil;
import java.awt.Graphics;

public class Explode {
	
	double x, y;
	//make the imgs static, let all members share the explode imgs
	static Image[] imgs = new Image[16];	
	static {
		for(int i = 0; i < 16; i++) {
			imgs[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
			/*
			when the first time to load the img, this img can not be shown out.
			to present the img, we could call some method of this img, such as getWidth()
			 */
			imgs[i].getWidth(null);
		}
	}
	int cnt;

	public void draw(Graphics g) {
		if(cnt < 16) {
			g.drawImage(imgs[cnt], (int)x, (int)y, null);
			cnt++;
		}
		
	}
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

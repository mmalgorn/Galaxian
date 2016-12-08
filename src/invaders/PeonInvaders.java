package invaders;

import java.awt.Point;

public class PeonInvaders extends Invaders {
	public PeonInvaders(Point p, int sp, Invaders.team team){
		this.width = 50;
		this.height = 50;
		this.speed = sp;
		this.life = 25;
		this.tm = team;
		this.setPosition(p);
		switch(team){
			case red :
				this.setImage("red_peon_invaders");
				break;
			case grey :
				this.setImage("grey_peon_invaders");
				break;
			case yellow :
				this.setImage("yellow_peon_invaders");
				break;
		}
	}
	
	/*
	 * 
	 */
	public void getDamage() {
		super.getDamage();
	}
}

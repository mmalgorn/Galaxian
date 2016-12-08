package v0;

import java.awt.Point;

public class PeonInvaders extends Invaders {
	public PeonInvaders(Point p){
		this.width = 50;
		this.height = 50;
		this.speed = 2;
		this.life = 25;
		this.setPosition(p);
		this.setImage("peon_invaders");
	}
	
	/*
	 * 
	 */
	public void getDamage() {
		super.getDamage();
	}
}

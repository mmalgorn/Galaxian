package v0;

import java.awt.Point;

import v0.Element.movement;
/*
 * Vaisseau Invaders pouvant tirer
 */
public class FireInvaders extends Invaders {
	
	public FireInvaders(Point p){
		this.width = 50;
		this.height = 50;
		this.speed = 5;
		this.life = 50;
		this.damageAmount = 25;
		this.setPosition(p);
		this.setImage("./img/invaders.png");
	}
	
	public void fire(){
		Point p = new Point((int)this.getX(),(int)(this.getY()+(this.height/2)));
		Missile m = new Missile(p,movement.TOP,true);
	}
	
}

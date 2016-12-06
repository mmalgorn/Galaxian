package v0;

import java.awt.Point;

import v0.Element.movement;
/*
 * Vaisseau invaders avec deux fois plus de vie
 */
public class ShieldInvaders extends Invaders {
	
	public ShieldInvaders(Point p){
		this.width = 50;
		this.height = 50;
		this.speed = 2;
		this.life = 100;
		this.setPosition(p);
		this.setImage("./img/shield_invaders.png");
	}
	
	public void getDamage() {
		super.getDamage();
		if (life <= 50)	this.setImage("./img/invaders.png");
	}
}

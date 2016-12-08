package invaders;

import java.awt.Point;
import java.util.Random;

import projectile.Missile;
import ressources.Element.movement;
/*
 * Vaisseau Invaders pouvant tirer
 */
public class FireInvaders extends Invaders {
	
	private Random randomGenerator;
	
	public FireInvaders(Point p){
		this.width = 50;
		this.height = 50;
		this.speed = 2;
		this.life = 50;
		this.damageAmount = 25;
		this.setPosition(p);
		this.setImage("fire_invaders");
	}

	/*
	 * Fonction de tir qui instancie un nouveau missile 
	 * dirigé vers le bas
	 */
	public void fire(){
		randomGenerator = new Random();
		if(randomGenerator.nextInt(200)<1){

			Point p = new Point((int)this.getX(),(int)(this.getY()+(this.height/2)));
			Missile m = new Missile(p,movement.BOTTOM,true);
		}
	}

}

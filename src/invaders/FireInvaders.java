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
	
	public FireInvaders(Point p , int sp, Invaders.team team){
		this.width = 50;
		this.height = 50;
		this.speed = sp;
		this.life = 50;
		this.damageAmount = 25;
		this.tm = team;
		this.setPosition(p);
		switch(team){
			case red :
				this.setImage("red_fire_invaders");
				break;
			case grey :
				this.setImage("grey_fire_invaders");
				break;
			case yellow :
				this.setImage("yellow_fire_invaders");
				break;
		}
		
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

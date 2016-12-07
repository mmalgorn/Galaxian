package v0;

import java.awt.Point;
import java.util.Random;

import v0.Element.movement;

public class BossInvaders extends Invaders{

	
	public BossInvaders(Point p){
		this.width = 300;
		this.height = 200;
		this.speed = 2;
		this.life = 5000;
		this.damageAmount = 25;
		this.setPosition(p);
		this.setImage("./img/mechant27.png");
	}
	
	
	public void fire(){
		Random randomGenerator = new Random();
		if(randomGenerator.nextInt(10)<1){
			
			Point p = new Point((int)this.getX()+randomGenerator.nextInt(300),(int)(this.getY()+randomGenerator.nextInt(200)));
			Missile m = new Missile(p,movement.BOTTOM,true);
		}
	}
	
	
	
	
	
}

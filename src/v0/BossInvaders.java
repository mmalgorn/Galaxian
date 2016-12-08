package v0;

import java.awt.Point;
import java.util.Random;

import v0.Element.movement;

public class BossInvaders extends Invaders{

	private int tmpLaser=0;

	public BossInvaders(Point p, String name){
		this.width = 300;
		this.height = 200;
		this.speed = 2;
		this.life = 5000;
		this.damageAmount = 25;
		this.setPosition(p);
		switch(name){
		case "red" : 
			this.setImage("./img/boss_invaders_red.png");
			break;
		case "grey" : 
			this.setImage("./img/boss_invaders_grey.png");
			break;
		case "yellow" : 
			this.setImage("./img/boss_invaders_yellow.png");
			break;
		}
	}


	public void fire(){
		Random randomGenerator = new Random();
		if(randomGenerator.nextInt(20)<1){

			Point p = new Point((int)this.getX()+randomGenerator.nextInt(300),(int)(this.getY()+randomGenerator.nextInt(200)));
			//Missile m = new Missile(p,movement.BOTTOM,true);
		}
		if(tmpLaser==0){
			if(randomGenerator.nextInt(30)<1){

				Point p2 = new Point((int)(this.getX()+width/2-9),(int)(this.getY()+height-50));
				Laser l = new Laser(p2,this.speed,true);
				tmpLaser=30;
			}
		}else tmpLaser--;
	}





}

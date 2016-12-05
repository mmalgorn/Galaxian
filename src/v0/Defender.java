package v0;

import java.awt.Point;
/*
 * Defender est la classe repr�sentant le vaisseau qui sera controler par le joueur
 */
public class Defender extends Element{
	
	private int life = 100;
	
	public Defender(Point p){
		this.width = 75;
		this.height = 75;
		this.speed = 15;
		this.setPosition(p);
		this.setImage("./img/mechant03.png");
	}
	
	public void fire(){
		Point p = new Point((int)this.getX(),(int)(this.getY()-(this.height/2)));
		Missile m = new Missile(p,movement.TOP);
	}

	public boolean isDefender(){ return true;}
	
}
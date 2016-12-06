package v0;

import java.awt.Point;
/*
 * Defender est la classe représentant le vaisseau qui sera controler par le joueur
 */
public class Defender extends Element{
	
	private int life = 100;
	
	public Defender(Point p){
		this.width = 75;
		this.height = 75;
		this.speed = 15;
		this.setPosition(p);
		this.setImage("./img/vaisseau.png");
	}
	
	public void fire(){
		Point p = new Point((int)(this.getX()+this.width/2),(int)(this.getY()));
		Missile m = new Missile(p,movement.TOP,false);
	}

	public boolean isDefender(){ return true;}
	
}

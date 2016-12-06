package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Missile extends Element {
	
	static List<Missile> missiles = new ArrayList<Missile>();
	
	private movement direction;
	private boolean missileEnnemy;
	
	public Missile(Point p,movement m,boolean me){
		missiles.add(this);
		direction = m;
		this.setPosition(p);
		this.height = 10;
		this.width = 5;
		this.speed = 5;
		this.setImage("./img/missile.png");
		missileEnnemy = me;
		this.move();
	}
	
	public void move(){

		move(direction);
		
			
		// tester si collision avec tout les elements 	
	}
	
	public void destroy() {
		missiles.remove(this);
		
	}
	
	public boolean isMissileEnnemy(){return this.missileEnnemy;}
}

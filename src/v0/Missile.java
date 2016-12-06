package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Missile extends Element {
	
	static List<Missile> missiles = new ArrayList<Missile>();
	
	private movement direction;
	private boolean missileEnnemy;
	
	public Missile(Point p, movement m, boolean me){
		missiles.add(this);
		direction = m;
		this.setPosition(p);
		this.height = 30;
		this.width = 15;
		this.speed = 8;
		this.setImage("./img/missile.png");
		missileEnnemy = me;
	}
	
	public void move(){

		move(direction);
		//else this.destroy();
			
		// tester si collision avec tout les elements 	
	}
	
	public void destroy() {
		missiles.remove(this);
	}
	
	public boolean isMissileEnnemy() { return this.missileEnnemy; }
	
	@Override
	public String toString() {
		return direction + " " + missileEnnemy;
	}
}

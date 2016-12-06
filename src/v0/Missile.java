package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Missile extends Element {
	
	static List<Missile> missiles = new ArrayList<Missile>();
	
	private movement direction;
	private boolean missileEnnemy;
	
	public Missile(Point p,movement m,boolean me){
		missiles.add(this);
		direction = m;
		this.setPosition(p);
		this.height = 100;
		this.width = 20;
		this.speed = 5;
		this.setImage("./img/missile.png");
		missileEnnemy = me;
		this.move();
	}
	
	public void move(){
		
			
		if(this.getY()>0) move(direction);
		else this.destroy();
			
		// tester si collision avec tout les elements 
		
		
	}
	
	public void destroy() {
		missiles.remove(this);
		
	}
	
	public boolean isMissileEnnemy(){return this.missileEnnemy;}
}

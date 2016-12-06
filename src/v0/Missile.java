package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Missile extends Element {
	
	static List<Missile> missiles = new ArrayList<Missile>();
	
	private movement direction;
	
	public Missile(Point p,movement m){
		missiles.add(this);
		direction = m;
		this.setPosition(p);
		this.height = 2;
		this.width = 10;
		this.speed = 5;
		this.setImage("./img/missile.png");
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
}

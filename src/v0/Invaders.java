package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import v0.Element.movement;

public abstract class Invaders extends Element {
	
	static List<Invaders> invaders = new ArrayList<Invaders>();
	protected int life;
	protected int damageAmount;
	
	public Invaders() {
		invaders.add(this);
	}
	
	public void moveLeft() {
		this.move(movement.LEFT);
	}
	
	public void moveRight() {
		this.move(movement.RIGHT);
	}
	
	public void moveBottom() {
		this.move(movement.BOTTOM);
	}
	
	public void getDamage() {
		life -= 25;
		if(life <= 0) destroy();
	}
	
	public void destroy() {
		invaders.remove(this);
	}
	
}
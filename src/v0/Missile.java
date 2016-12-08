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
		this.height = 30;
		this.width = 15;
		this.speed = 8;
		if(me) this.setImage("missile");
		else this.setImage("missile_defender");
		missileEnnemy = me;
		this.move();
	}
	
	/*
	 * Déplace la position du missile en fonction de sa direction
	 */
	public void move(){
		if(this.getY()>0 && this.getY()<600) move(direction);
		// tester si collision avec tout les elements 	
	}
	
	/*
	 * Permet de détruire un missile en le déréférençant par
	 * suppression dans la liste missiles
	 */
	public void destroy() {
		missiles.remove(this);
	}
	
	/*
	 * Renvoie vrai si le missile est ennemi ou faux sinon
	 */
	public boolean isMissileEnnemy() { return this.missileEnnemy; }
}

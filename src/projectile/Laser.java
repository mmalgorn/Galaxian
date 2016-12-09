package projectile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import defender.Defender;
import ressources.Element;

public class Laser extends Element {
	
	public static List<Laser> lasers = new ArrayList<Laser>();
	public int destroy=4;

	private boolean laserEnnemy;
	public Laser(Point p,double sp,boolean me){
		lasers.add(this);
		this.setPosition(p);
		this.height = 500;
		this.width = 20;
		this.speed = sp;
		if(me) this.setImage("laser");
		else this.setImage("laser_defender");
		laserEnnemy = me;
		
	}
	
	
	
	/*
	 * Permet de détruire un missile en le déréférençant par
	 * suppression dans la liste missiles
	 */
	public void destroy() {
		isDest=true;
	}
	
	
	public void remove(){
		if (isDest) destroy--;
		if (destroy==0)	lasers.remove(this);
		
	}
	
	
	/*
	 * Renvoie vrai si le missile est ennemi ou faux sinon
	 */
	public boolean isMissileEnnemy() { return this.laserEnnemy; }
	
	public boolean isLaser() {		return true;}

	public void move(Point position) {
		this.setPosition(new Point((int)(position.getX()+Defender.def.width/2-5),-50));	
	}
}

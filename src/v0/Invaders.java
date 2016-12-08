package v0;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import v0.Element.movement;

public abstract class Invaders extends Element {
	
	static List<Invaders> invaders = new ArrayList<Invaders>();
	protected int life;
	protected int damageAmount;
	private Random rand;
	
	public Invaders() {
		invaders.add(this);
	}
	
	/*
	 * Permet de déplacer l'invader vers la gauche
	 */
	public void moveLeft() {
		this.move(movement.LEFT);
	}
	
	/*
	 * Permet de déplacer l'invader vers la droite
	 */
	public void moveRight() {
		this.move(movement.RIGHT);
	}
	
	/*
	 * Permet de déplacer l'invader vers la gauche
	 */
	public void moveBottom() {
		this.move(movement.BOTTOM);
	}
	
	/*
	 * Fonction appelée lorsque le vaisseau reçoit des dégats
	 */
	public void getDamage() {
		life -= 25;
		if(life <= 0) destroy();
	}
	
	/*
	 * Permet de détruire un vaisseau en le déréférençant
	 * par suppression dans la liste invaders
	 */
	public void destroy() {
		rand = new Random();
		Point p = new Point((int)this.getX(),(int)this.getY());
		if(rand.nextInt(10)==1)new BonusBouclier(p);
		else if(rand.nextInt(10)==2)new BonusLaser(p);
		else if(rand.nextInt(10)==3)new BonusCoeur(p);
		Space.score+=100;
		invaders.remove(this);
		Sound snd = new Sound("./sound/explosion.wav");
		snd.play();
		snd.interrupt();
		
	}
	
	public void fire() {}
}
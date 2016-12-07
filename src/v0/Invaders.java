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
	
	/*
	 * Permet de d�placer l'invader vers la gauche
	 */
	public void moveLeft() {
		this.move(movement.LEFT);
	}
	
	/*
	 * Permet de d�placer l'invader vers la droite
	 */
	public void moveRight() {
		this.move(movement.RIGHT);
	}
	
	/*
	 * Permet de d�placer l'invader vers la gauche
	 */
	public void moveBottom() {
		this.move(movement.BOTTOM);
	}
	
	/*
	 * Fonction appel�e lorsque le vaisseau re�oit des d�gats
	 */
	public void getDamage() {
		life -= 25;
		if(life <= 0) destroy();
	}
	
	/*
	 * Permet de d�truire un vaisseau en le d�r�f�ren�ant
	 * par suppression dans la liste invaders
	 */
	public void destroy() {
		invaders.remove(this);
		Sound snd = new Sound("./sound/explosion.wav");
		snd.play();
	}
	
	public void fire() {}
}
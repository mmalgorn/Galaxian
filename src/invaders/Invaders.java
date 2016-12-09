package invaders;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.BonusBouclier;
import game.BonusCoeur;
import game.BonusFireRate;
import game.BonusLaser;
import game.Space;
import ressources.Element;
import ressources.Sound;

public abstract class Invaders extends Element {
	
	public enum team{
		red,grey,yellow;
	}
	public static List<Invaders> invaders = new ArrayList<Invaders>();
	protected int life;
	protected int damageAmount;
	protected team tm;
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
		int randInt = rand.nextInt(20);
		Point p = new Point((int)this.getX(),(int)this.getY());
		if(randInt == 1) new BonusBouclier(p);
		else if(randInt == 2) new BonusLaser(p);
		else if(randInt == 3) new BonusCoeur(p);
		else if(randInt == 4) new BonusFireRate(p);
		Space.score += 100;
		invaders.remove(this);
		Sound snd = Sound.soundMap.get("explosion");
		snd.play();
		snd.interrupt();
		
	}
	
	public void fire() {}
}
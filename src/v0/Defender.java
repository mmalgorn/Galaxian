package v0;

import java.awt.Point;
/*
 * Defender est la classe représentant le vaisseau controlé par le joueur
 */
public class Defender extends Element{
	final String full_heart = "./img/full_heart.png";
	final String empty_heart = "./img/empty_heart.png";
	static Defender def;
	
	private int life = 100;
	
	public Defender(Point p){
		this.width = 75;
		this.height = 75;
		this.speed = 15;
		this.setPosition(p);
		this.setImage("./img/vaisseau.png");
		def = this;
	}
	
	/*
	 * Retourne la vie du joueur
	 */
	public int getLife(){
		return this.life;
	}
	
	/*
	 * Fonction de tir qui instancie un nouveau missile se dirigeant vers le haut
	 */
	public void fire(){
		Point p = new Point((int)(this.getX()+this.width/2),(int)(this.getY()));
		new Missile(p, movement.TOP, false);
	}
	
	/*
	 * Fonction appelée lorsque le vaisseau reçoit des dégats
	 */
	public void getDamage() {
		life -= 25;
	}
}

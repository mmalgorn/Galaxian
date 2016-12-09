package defender;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import game.ImagePanel;
import projectile.Laser;
import projectile.Missile;
import ressources.Element;
import ressources.Sound;
import ressources.Sprites;

/*
 * Defender est la classe représentant le vaisseau controlé par le joueur
 */
public class Defender extends Element {

	final ImagePanel full_heart = Sprites.spritesMap.get("full_heart");
	final ImagePanel empty_heart = Sprites.spritesMap.get("empty_heart");
	final ImagePanel laser = Sprites.spritesMap.get("bonus_laser");
	final ImagePanel fireRate = Sprites.spritesMap.get("bonus_fire_rate");
	public static Defender def;
	private int nbFireRate = 0;
	private int nbLaser = 0;
	private int inv = 0;
	private int life = 100;
	private int niveau;
	private int nbHeart = 4;
	private boolean shield;

	public Defender(Point p) {
		this.width = 75;
		this.height = 75;
		this.speed = 15;
		this.shield = false;
		this.setPosition(p);
		this.setImage("vaisseau1");
		this.niveau = 1;
		def = this;
	}

	/*
	 * Retourne la vie du joueur
	 */
	public int getLife() {
		return this.life;
	}

	public void moveLeft() {
		if (position.getX() > 10) move(movement.LEFT);
	}

	public void moveRight() {
		if (position.getX() < 590) move(movement.RIGHT);
	}

	public void drawLife(Graphics g) {
		for (int i = nbHeart; i > 0; i--) {
			if (life >= i * 25) {
				full_heart.paintComponent(g, 5 + (i * 21), 5, 20, 20);
				full_heart.paint(g);
			} else {
				empty_heart.paintComponent(g, 5 + (i * 21), 5, 20, 20);
				empty_heart.paint(g);
			}
		}
	}

	public void drawLaser(Graphics g) {
		Font f = new Font("Arial", Font.BOLD, 25);
		g.setFont(f);

		laser.paintComponent(g, 625, 530, 20, 20);
		g.drawString(Integer.toString(nbLaser), 650, 550);

	}

	public void drawFireRate(Graphics g) {
		Font f = new Font("Arial", Font.BOLD, 25);
		g.setFont(f);

		fireRate.paintComponent(g, 565, 530, 20, 20);
		g.drawString(Integer.toString(nbFireRate), 590, 550);

	}

	/*
	 * Fonction de tir qui instancie un nouveau missile se dirigeant vers le
	 * haut
	 */
	public void fire() {

		if (nbFireRate > 0) {
			Point p1 = new Point((int) (this.getX()), (int) (this.getY()));
			Point p2 = new Point((int) (this.getX() + this.width), (int) (this.getY()));
			new Missile(p1, movement.TOPLEFT, false);
			new Missile(p2, movement.TOPRIGHT, false);
			nbFireRate--;
		}
		Point p = new Point((int) (this.getX() + this.width / 2), (int) (this.getY()));
		new Missile(p, movement.TOP, false);
	}

	public void fireLaser() {
		if (nbLaser > 0) {
			Point p = new Point((int) (this.getX() + this.width / 2), 0);
			new Laser(p, 5, false);
			nbLaser--;

			Sound snd = Sound.soundMap.get("laser");
			snd.play();
			snd.interrupt();
		}
	}

	/*
	 * Fonction appelée lorsque le vaisseau reçoit des dégats
	 */
	public void getDamage() {
		if (inv == 0) {
			inv = 10;
			if (shield) {
				shield = false;
				this.setImage("vaisseau" + this.niveau);
			} else {
				life -= 25;
			}
		}
	}

	public void heal() {
		if (life <= (nbHeart - 1) * 25) life += 25;
		else if (life < nbHeart * 25) life = nbHeart * 25;
	}

	public int getNiveau() {
		return this.niveau;
	}

	@Override
	public void evolve() {
		if (this.niveau < 3) {
			this.niveau++;
			this.nbHeart++;
			this.life += 25;
			if (shield) this.setImage("vaisseau" + this.niveau + "_shield");
			else this.setImage("vaisseau" + this.niveau);
		}

	}

	public void getlaser() {
		nbLaser = Math.min(99, nbLaser + 2);

	}

	public void addFireRate(int amount) {
		nbFireRate = Math.min(99, nbFireRate + amount);
	}

	public void addShield() {
		shield = true;
		this.setImage("vaisseau" + this.niveau + "_shield");
	}

	public void inv() {
		if (inv > 0) inv--;
	}

}

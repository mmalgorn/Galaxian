package ressources;

import java.awt.Graphics;
import java.awt.Point;

import game.ImagePanel;

/*
 * La classe Element sera hériter par tout les vaisseaux et regroupe donc les fonctionnalité principales 
 * utiliser par chacun.
 * 
 * Nous avons décidé d'utiliser une zone nommé "hitbox" qui entourera chaque element afin de faciliter la détéction de collision
 */
public abstract class Element {

	public enum movement {
		LEFT, RIGHT, TOP, BOTTOM, TOPLEFT, TOPRIGHT;
	}

	protected double speed;
	public double width;
	public double height;
	protected Point position;
	private ImagePanel img;
	public boolean isDest = false;

	/*
	 * Retourne la position de l'élément
	 */
	public Point getPosition() {
		return this.position;
	}

	/*
	 * Met à jour la position de l'élément
	 */
	public void setPosition(Point p) {
		this.position = p;
	}

	/*
	 * Donne la position en x
	 */
	public double getX() {
		return position.getX();
	}

	/*
	 * Donne la position en y
	 */
	public double getY() {
		return position.getY();
	}

	/*
	 * Donne la largeur de l'élément
	 */
	public double getWidth() {
		return width;
	}

	/*
	 * Donne la hauteur de l'élément
	 */
	public double getHeight() {
		return height;
	}

	/*
	 * Déplace l'élément en fonction d'un mouvement
	 */
	public void move(movement m) {
		double x = 0, y = 0;
		switch (m) {
		case LEFT:
			x = (-speed);
			break;
		case RIGHT:
			x = speed;
			break;
		case TOP:
			y = (-speed);
			break;
		case BOTTOM:
			y = speed;
			break;
		case TOPLEFT:
			x = (-speed);
			y = (-speed);
			break;
		case TOPRIGHT:
			x = speed;
			y = (-speed);
			break;
		}
		position.setLocation(position.getX() + x, position.getY() + y);
	}

	/*
	 * Renvoie le chemin de l'image utilisée par l'élément
	 */
	public ImagePanel getImage() {
		return this.img;
	}

	/*
	 * Modifie l'image utilisée par l'élément
	 */
	public void setImage(String img) {
		this.img = Sprites.spritesMap.get(img);
	}

	/*
	 * Indique si les points de collisions de l'élément courant sont compris
	 * dans ceux de l'élément e
	 */

	/*
	 * Indique s'il y a collision entre l'élément courant et l'élément e
	 */
	public boolean collideWith(Element e) {
		if (e.getX() < this.getX() + this.width && e.getX() + e.width > this.getX()
				&& e.getY() < this.getY() + this.height && e.height + e.getY() > this.getY()) {
			// collision detected!
			return true;
		}
		return false;
	}

	/*
	 * Permet de d'afficher l'élément
	 */
	public void drawOn(Graphics g) {
		img.paintComponent(g, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public void evolve() {

	}

	public boolean isMissileEnnemy() {

		return false;
	}

	public boolean isLaser() {
		return false;
	}

}

package v0;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import v0.Element.movement;

/*
 * La classe Element sera h�riter par tout les vaisseaux et regroupe donc les fonctionnalit� principales 
 * utiliser par chacun.
 * 
 * Nous avons d�cid� d'utiliser une zone nomm� "hitbox" qui entourera chaque element afin de faciliter la d�t�ction de collision
 */
public abstract class Element {
	
	public enum movement { LEFT,RIGHT,TOP,BOTTOM; }
	protected double speed;
	protected double width;
	protected double height;
	private Point position;
	private String urlImage;
	private ImagePanel img;
	
	
	/*
	 * Retourne la position de l'�l�ment
	 */
	public Point getPosition() { return this.position; }
	
	/*
	 * Met � jour la position de l'�l�ment
	 */
	public void setPosition(Point p) { this.position = p; }
	
	/*
	 * Donne la position en x
	 */
	public double getX() { return position.getX(); }
	
	/*
	 * Donne la position en y
	 */
	public double getY() { return position.getY(); }
	
	/*
	 * Donne la largeur de l'�l�ment
	 */
	public double getWidth() { return width; }
	
	/*
	 * Donne la hauteur de l'�l�ment
	 */
	public double getHeight() { return height; }
	
	/*
	 * D�place l'�l�ment en fonction d'un mouvement
	 */
	public void move(movement m) {
		double x = 0, y = 0;
		switch(m){
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
		}
		position.setLocation(position.getX() + x,position.getY() + y);
	}
	
	/*
	 * Renvoie le chemin de l'image utilis�e par l'�l�ment
	 */
	public String getImage() { return urlImage; }
	
	/*
	 * Modifie l'image utilis�e par l'�l�ment
	 */
	public void setImage(String img) { urlImage = img; }
	
	/*
	 * Indique si les points de collisions de l'�l�ment courant 
	 * sont compris dans ceux de l'�l�ment e
	 */
	public boolean collide(Element e) {
		Point[] tmp = {
			new Point((int) e.getX() , (int) e.getY()),
			new Point((int) (e.getX() + e.getWidth()), (int) e.getY() ),
			new Point((int) e.getX(), (int) (e.getY() + e.getHeight())),
			new Point((int) (e.getX() + (e.getWidth())), (int) (e.getY() + (e.getHeight())))
 		};
 		for(int i = 0; i < 4; i++) {
 			if(!(tmp[i].getX() >= position.getX()  && tmp[i].getX() <= position.getX() + width)) continue;
 			if(!(tmp[i].getY() >= position.getY() && tmp[i].getY() <= position.getY() + height)) continue;
 			return true;
 		}
 		return false;
	}
	
	/*
	 * Indique s'il y a collision entre l'�l�ment courant et l'�l�ment e
	 */
	public boolean collideWith(Element e) {
		return collide(e) || e.collide(this);
	}
	
	/*
	 * Permet de d'afficher l'�l�ment
	 */
	public void drawOn(Graphics g) {
		try {
			img = new ImagePanel(this.urlImage);
			img.paintComponent(g,this.getX(),this.getY(),this.getWidth(),this.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void evolve(){
		
	}

}	

package v0;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;

import v0.Element.movement;

/*
 * La classe Element sera hériter par tout les vaisseaux et regroupe donc les fonctionnalité principales 
 * utiliser par chacun.
 * 
 * Nous avons décidé d'utiliser une zone nommé "hitbox" qui entourera chaque element afin de faciliter la détéction de collision
 */
public abstract class Element {
	
	public enum movement { LEFT,RIGHT,TOP,BOTTOM; }
	protected double speed;
	protected double width;
	protected double height;
	private Point position;
	private String urlImage;
	
	public Point getPosition() { return this.position; }
	
	public void setPosition(Point p) { this.position = p; }
	
	public double getX() { return position.getX(); }
	
	public double getY() { return position.getY(); }
	
	public double getWidth() { return width; }
	
	public double getHeight() { return height; }
	
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
	
	public String getImage() { return urlImage; }
	
	public void setImage(String img) { urlImage = img; }
	
	public boolean isDefender(){ return false;}
	
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
 			System.out.println("Collision");
 			return true;
 		}
 		return false;
	}
	
	public boolean collideWith(Element e) {
		return collide(e) || e.collide(this);
	}
	
	public void drawOn(Graphics g) {
		try {
			ImagePanel img = new ImagePanel(this.urlImage);
			img.paintComponent(g,this.getX(),this.getY(),this.getWidth(),this.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fire(){
	
	}
}	

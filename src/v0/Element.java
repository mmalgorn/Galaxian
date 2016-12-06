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
		
		double[] interval = {e.getX(),e.getX()+e.width,e.getY(),e.getY()+e.height};
		double[] intervalThis = {this.getX(),this.getX()+width,this.getY(),this.getY()+height};		
		
		//Test si le premier intervalle est dans le second
		for(int i=0;i<2;i++){
			
			if(interval[0]<=intervalThis[i]&&interval[1]>=intervalThis[i]){
				for(int j=0;j<2;j++){
					if(interval[2]<=intervalThis[j+2]&&interval[3]>=intervalThis[j+2]) {System.out.println("colision");return true;}
				}
			}
		}
		
		for(int i=0;i<2;i++){
			if(intervalThis[0]<=interval[i]&&intervalThis[1]>=interval[i]){
				
				for(int j=0;j<2;j++){
					if(intervalThis[4]<=interval[j+2]&&intervalThis[3]>=interval[j+2]) {System.out.println("colision"); return true;}
				}
			}
		}
		
		return false;
		
		
		
	}
	
	public boolean isInside(Point p,double x1,double x2,double y1,double y2){
		double x=p.getX(),y=p.getY();
		if((x>=x1&&x<=x2)&&(y>=y1&&y<=y2)) return true;
		return false;
		
		
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

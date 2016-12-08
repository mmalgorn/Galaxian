package v0;

import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
/*
 * Defender est la classe représentant le vaisseau controlé par le joueur
 */
public class Defender extends Element{
	final String full_heart = "./img/full_heart.png";
	final String empty_heart = "./img/empty_heart.png";
	static Defender def;
	private ImagePanel ip;
	private boolean haveLaser=false;
	
	
	private int life = 100;
	private int niveau;
	private int nbHeart = 4;
	public Defender(Point p){
		this.width = 75;
		this.height = 75;
		this.speed = 15;
		this.setPosition(p);
		this.setImage("./img/vaisseau1.png");
		this.niveau=1;
		def = this;
	}
	
	/*
	 * Retourne la vie du joueur
	 */
	public int getLife(){
		return this.life;
	}
	public void drawLife(Graphics g){
		try{
			ip = new ImagePanel(full_heart);
			for (int i = nbHeart ; i > 0; i--){
				if(life >= i*25){
					ip.setImage(full_heart);
					ip.paintComponent(g, 5+(i*21), 5, 20, 20);
					ip.paint(g);
				}else{
					ip.setImage(empty_heart);
					ip.paintComponent(g, 5+(i*21), 5, 20, 20);
					ip.paint(g);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Fonction de tir qui instancie un nouveau missile se dirigeant vers le haut
	 */
	public void fire(){
	
		if(!haveLaser){		
			Point p = new Point((int)(this.getX()+this.width/2),(int)(this.getY()));
		new Missile(p, movement.TOP, false);
		}else {
			Point p = new Point((int)(this.getX()+this.width/2),0);
			new Laser(p,5,false);			
		}
		
	}
	
	/*
	 * Fonction appelée lorsque le vaisseau reçoit des dégats
	 */
	public void getDamage() {
		life -= 25;
	}
	public void heal() {
		if(life<=(nbHeart-1)*25)life += 25;
		else if(life<nbHeart*25)life = nbHeart*25;
	}
	public int getNiveau() {
		return this.niveau;
	}
	public void evolve(){
		if(this.niveau<3){
			this.niveau++;
			this.nbHeart++;
			this.life+=25;
			this.setImage("./img/vaisseau"+this.niveau+".png");
		}
		
	}

	public void getlaser() {
		// TODO Auto-generated method stub
		haveLaser=true;
		
	}

}

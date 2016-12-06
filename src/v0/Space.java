package v0;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import v0.Element.movement;


public class Space extends JComponent implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Set <Element> contents = Collections.synchronizedSet(new HashSet<Element>());
	boolean moveLeft = false;
	boolean moveRight = false;
	ThreadVaisseau tv;
	movement moveAdv = movement.RIGHT;

	void addElement(Element anElement) {
		contents.add(anElement);
	}

	void removeElement(Element anElement) {
		contents.remove(anElement);
	}

	public void paint(Graphics g) {
		super.paint(g);
		drawBackground(g);
		Iterator<Element> itor = this.elementIterator();
		Iterator<Missile> missItor = Missile.missiles.iterator();
		while (itor.hasNext()) {
			itor.next().drawOn(g);
		}
		while (missItor.hasNext()){
			missItor.next().drawOn(g);
		}
	}
	
	public void start() {
		this.start(30, 30, 700, 600);
	}
	
	Iterator<Element> elementIterator() { 
		ArrayList<Element> cpy = new ArrayList<Element>(contents);
		Iterator<Element> itor = cpy.iterator();
		return itor;
	}
	
	
	public void start (int x, int y, int width, int height) {
		JFrame window = new JFrame();
		window.setBounds(x, y, width, height);
		window.setTitle("Galaxian : Le meilleur jeu de Space Invaders de TOUTE la galaxie !!!!!!!!!!");
		window.setIconImage(new ImageIcon("./img/vaisseau_icon.png").getImage());
		window.getContentPane().add(this);
		window.setResizable(false);
		window.setVisible(true);
		window.addKeyListener(this);
		new GestFenetre(window);
		Universe.addSpace(this);
	}
	
	public void drawBackground(Graphics g){
		try {
			ImagePanel imgp = new ImagePanel("./img/background.jpg");
			imgp.paintComponent(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void moveElements(){
		Iterator<Element> iter = elementIterator();
		Iterator<Missile> missItor = Missile.missiles.iterator();
//		while(iter.hasNext()){
//			Element m = iter.next();
//			if(m instanceof Missile){
//				if(((Missile)m).isMissileEnnemy())m.move(movement.TOP);
//				else m.move(movement.BOTTOM);
//			}
//			if(!(m instanceof Defender)){
//				//m.move(movement.RIGHT);
//			}
//		}
		while (missItor.hasNext()){
			Missile m = missItor.next();
			if((!(this.isCol(m) || (m.getY()<=0 || m.getY()>=600)))) m.move();
			else m.destroy();
		}
		
		moveEnemys(iter);
		
		
	}
	/*
	 * Deplacement Ennemis
	 */
	public void moveEnemys(Iterator<Element> iter){
		boolean isOnBorder = false;
		while(iter.hasNext()){
			Element e = iter.next();
			if(!(e.isDefender())){
				if(e.getX() <= 0){
					moveAdv = movement.RIGHT;
					isOnBorder = true;
				}
				if ((e.getX() + e.width) >= 700){
					moveAdv = movement.LEFT;
					isOnBorder = true;
				}
				if(e.getY() <= 0 || (e.getY()+e.height) >= 450){
					System.out.println("GAME OVER");
					System.exit(0);
				}
			}
			
		}
		iter = elementIterator();
		while(iter.hasNext()){
			Element e = iter.next();
			if(!(e.isDefender())){
				if(isOnBorder){
					e.move(movement.BOTTOM);
					e.move(movement.BOTTOM);
					e.move(moveAdv);
				}
				if(!isOnBorder){
					e.move(moveAdv);
				}
			}
		}
	}
	public Element searchDefender(){
		Element elem;
		Iterator<Element> iter = elementIterator();
		while(iter.hasNext()){
			elem = iter.next();
			if(elem.isDefender())return elem;
		}
		return null;
	}

	public boolean moveDirLeft(){return moveLeft;}
	public boolean moveDirRight(){return moveRight;}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		Element elem = searchDefender();
		if((!moveLeft) && (e.getKeyCode()==KeyEvent.VK_LEFT )){
			if(tv != null)tv.arret();
			tv = new ThreadVaisseau(elem,"left");
			tv.start();
			moveLeft = true;
		}else if((!moveRight) && (e.getKeyCode()==KeyEvent.VK_RIGHT)){
			if(tv != null)tv.arret();
			tv = new ThreadVaisseau(elem,"right");
			tv.start();
			moveRight = true;
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE)
			new Missile((new Point((int)(elem.getX()+elem.width/2),(int)elem.getY())),movement.TOP,false);
		
	}
	public boolean isCol(Missile m){
		
		Iterator<Element> iter = elementIterator();
		
		while (iter.hasNext()) {
			if(m.collide(iter.next())) {
				
				if(m.isMissileEnnemy()){
					if(!iter.next().isDefender()) return true;
				}else{
					if(iter.next().isDefender()) return true;
				}
				}
			
			else {
				
			}
		}
		return false;			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Element elem = searchDefender();
		if(moveLeft && e.getKeyCode()==KeyEvent.VK_LEFT){
			moveLeft = false;
			if(tv.getDir().equals("left"))tv.arret();
		}
		if(moveRight && e.getKeyCode()==KeyEvent.VK_RIGHT){
			moveRight = false;
			if(tv.getDir().equals("right"))tv.arret();
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	
}
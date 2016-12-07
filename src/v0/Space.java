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
	boolean fire = false;
	ThreadVaisseau tv;
	movement moveAdv = movement.RIGHT;
	Sound snd;

	void addElement(Element anElement) {
		contents.add(anElement);
	}

	void removeElement(Element anElement) {
		contents.remove(anElement);
	}

	public void paint(Graphics g) {
		moveElements();
		super.paint(g);
		drawBackground(g);
		Defender.def.drawOn(g);
		Iterator<Invaders> inv = Invaders.invaders.iterator();
		while (inv.hasNext()) inv.next().drawOn(g);
		Iterator<Missile> mis = Missile.missiles.iterator();
		while (mis.hasNext()) {
			Missile m = mis.next();
			m.drawOn(g);
		}
	}

	public void start() {
		this.start(30, 30, 700, 600);
	}

	Iterator<Element> elementIterator() {
		ArrayList<Element> e = new ArrayList<Element>(Invaders.invaders);
		e.add(Defender.def);
		return e.iterator();
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

		for(int i = Missile.missiles.size()-1; i >= 0; i--) {
			Missile m = Missile.missiles.get(i);
			m.move();
			if((!(this.isCol(m)||(m.getY()<=0||m.getY()>=600)))) m.move();
			else m.destroy();
		}

		moveEnemys(Invaders.invaders.iterator());
	}

	/*
	 * Deplacement Ennemis
	 */
	public void moveEnemys(Iterator<Invaders> iter){
		boolean isOnBorder = false;
		while(iter.hasNext()){
			Invaders inv = iter.next();
			inv.fire();
			if(inv.getX() <= 0){
				moveAdv = movement.RIGHT;
				isOnBorder = true;
			}
			if ((inv.getX() + inv.width) >= 700){
				moveAdv = movement.LEFT;
				isOnBorder = true;
			}
			if(inv.getY() <= 0 || (inv.getY()+inv.height) >= 450){
				System.out.println("GAME OVER");
				System.exit(0);
			}
		}
		iter = Invaders.invaders.iterator();
		while(iter.hasNext()){
			Element inv = iter.next();
			if(isOnBorder){
				inv.move(movement.BOTTOM);
				inv.move(movement.BOTTOM);
				inv.move(moveAdv);
			}
			if(!isOnBorder) inv.move(moveAdv);
		}
	}

	public boolean moveDirLeft() { return moveLeft; }
	public boolean moveDirRight() { return moveRight; }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if((!moveLeft) && (e.getKeyCode()==KeyEvent.VK_LEFT )){
			if(tv != null)tv.arret();
			tv = new ThreadVaisseau(Defender.def,"left");
			tv.start();
			moveLeft = true;
		}else if((!moveRight) && (e.getKeyCode()==KeyEvent.VK_RIGHT)){
			if(tv != null)tv.arret();
			tv = new ThreadVaisseau(Defender.def,"right");
			tv.start();
			moveRight = true;
		}else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(!fire){
				Defender.def.fire();
				snd = new Sound("./sound/fire.wav");
				snd.play();
			}
			fire = true;
		}

	}

	public boolean isCol(Missile m) {
		if (m.isMissileEnnemy()){
			if(m.collideWith(Defender.def)){
				Defender.def.getDamage();
				if(Defender.def.getLife()<=0) gameOver();
				return true;

			}
		}else{
			Iterator<Invaders> it = Invaders.invaders.iterator();
			while(it.hasNext()) {
				Invaders inv = it.next();
				if (m.collideWith(inv)) {
					inv.getDamage();
					if (Invaders.invaders.size() == 0) win();
					return true;
				};
			}
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(moveLeft && e.getKeyCode() == KeyEvent.VK_LEFT){
			moveLeft = false;
			if(tv.getDir().equals("left")) tv.arret();
		}
		if(moveRight && e.getKeyCode() == KeyEvent.VK_RIGHT){
			moveRight = false;
			if(tv.getDir().equals("right")) tv.arret();
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) fire = false; 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void win() {
		System.out.println("YOUPI !");
	}

	public void gameOver(){
		System.out.println("GameOver");
		Defender.def.setImage("./img/explosion.png");
		
	}


}
package v0;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import v0.Element.movement;


public class Space extends JComponent implements KeyListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Map<String, Integer> scoreTable = new TreeMap<String, Integer>();
	Set <Element> contents = Collections.synchronizedSet(new HashSet<Element>());
	boolean moveLeft = false;
	boolean moveRight = false;
	boolean fire = false;
	boolean menu = true;
	boolean gameOver = false;
	boolean gameOver2 = false;
	boolean boutonClik = false;
	boolean attente = true;
	String typeBouton;
	ThreadVaisseau tv;
	movement moveAdv = movement.RIGHT;
	Sound snd;

	static int score;
	String username = "";
	int cursor = 0;
	void addElement(Element anElement) {
		contents.add(anElement);
	}

	void removeElement(Element anElement) {
		contents.remove(anElement);
	}

	public void paint(Graphics g) {

		if(!gameOver)moveElements();
		super.paint(g);
		if(menu) {
			drawMenu(g);
			if(boutonClik)
				drawBoutonClik(g);
		} else {
			drawBackground(g);
			paintLife(g);
			Defender.def.drawOn(g);
			Iterator<Invaders> inv = Invaders.invaders.iterator();
			while (inv.hasNext()) inv.next().drawOn(g);
			Iterator<Missile> mis = Missile.missiles.iterator();
			while (mis.hasNext()) {
				Missile m = mis.next();
				m.drawOn(g);
			}
		}
		if(gameOver2){
			if(attente){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attente = false;
			}
			drawGameOver(g);
		}
		if(gameOver)gameOver2 = true;
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
		window.addMouseListener(this);
		new GestFenetre(window);
		Universe.addSpace(this);
		readScores();
	}

	
	public void drawMenu(Graphics g){
		try {
			ImagePanel imgFond = new ImagePanel("./img/background.jpg");
			ImagePanel imgTitre = new ImagePanel("./img/titre.png");
			ImagePanel imgBJ = new ImagePanel("./img/boutonJouer.png");
			ImagePanel imgBQ = new ImagePanel("./img/boutonQuitter.png");
			imgFond.paintComponent(g);
			imgTitre.paintComponent(g, 125, 100, 450, 100);
			imgBJ.paintComponent(g, 225, 250, 250, 75);
			imgBQ.paintComponent(g, 225, 350, 250, 75);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawScorePanel(Graphics g){
		drawBackground(g);
		g.setColor(Color.WHITE);
		Font f = new Font("Arial", Font.BOLD, 30);
		Font f2 = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		drawStringCenter("SCORES", 350, 50, g);
		g.setFont(f2);
		int i = 0;
		for(Entry<String, Integer> e : scoreTable.entrySet()) {
			g.drawString(e.getKey(), 150, 100+(30*i));
			drawStringRight(e.getValue().toString(), 550, 100+(30*i), g);
			i++;
		}
		g.drawString(username, 150, 100+(30*i));
		drawStringRight(""+score, 550, 100+(30*i), g);
	}
	
	public void drawStringCenter(String s, int x, int y, Graphics g) {
		int shift = g.getFontMetrics().stringWidth(s)/2;
		g.drawString(s, x-shift, y);
	}
	
	public void drawStringRight(String s, int x, int y, Graphics g) {
		int shift = g.getFontMetrics().stringWidth(s);
		g.drawString(s, x-shift, y);
	}
	
	public void drawBoutonClik(Graphics g){
		try {
			if(typeBouton.equals("jouer")){
				ImagePanel imgBJ = new ImagePanel("./img/boutonJouerClick.png");
				imgBJ.paintComponent(g, 225, 250, 250, 75);
			}else if(typeBouton.equals("quitter")) {
				ImagePanel imgBQ = new ImagePanel("./img/boutonQuitterClick.png");
				imgBQ.paintComponent(g, 225, 350, 250, 75);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void drawGameOver(Graphics g){
		ImagePanel imgFond;
		try {
			imgFond = new ImagePanel("./img/background.jpg");
			ImagePanel imgGameOver = new ImagePanel("./img/gameover.png");
			imgFond.paintComponent(g);
			imgGameOver.paintComponent(g, 75, 100, 550, 100);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		moveMissiles();
		moveEnemys();
		DefenderEvolve();
	}

	// Deplacement des missiles a chaque tours
	public void moveMissiles(){
		for(int i = Missile.missiles.size()-1; i >= 0; i--) {
			Missile m = Missile.missiles.get(i);
			m.move();
			if((!(this.isCol(m)||(m.getY()<=0||m.getY()>=600)))) m.move();
			else m.destroy();
		}
	}
	// Deplacement des Ennemis
	public void moveEnemys(){
		Iterator<Invaders> iter = Invaders.invaders.iterator();
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
	private void DefenderEvolve() {
		if(Space.score>1000&&Defender.def.getNiveau()==1){
			Defender.def.evolve();
		}else{
			if(Space.score>2000&&Defender.def.getNiveau()==2){
				Defender.def.evolve();
			}
		}
		
	}
	// Affichage de la vie
	public void paintLife(Graphics g){
		Defender.def.drawLife(g);
		
	}
	public boolean moveDirLeft() { return moveLeft; }
	public boolean moveDirRight() { return moveRight; }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (!moveLeft) {
					if(tv != null)tv.arret();
					tv = new ThreadVaisseau(Defender.def,"left");
					tv.start();
					moveLeft = true;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (!moveRight) {
					if(tv != null)tv.arret();
					tv = new ThreadVaisseau(Defender.def,"right");
					tv.start();
					moveRight = true;
				}
				break;
			case KeyEvent.VK_SPACE:
				if(!fire){
					Defender.def.fire();
					snd = new Sound("./sound/fire.wav");
					snd.play();
				}
				fire = true;
				break;
			case KeyEvent.VK_ENTER:
				addScore(username, score);
				writeScores();
				gameOver = false;
				menu = true;
				break;
			case KeyEvent.VK_BACK_SPACE:
				if (gameOver) username = username.substring(0, Math.max(0, username.length() - 1));
				break;
			default:
				if (gameOver) {
					char c = e.getKeyChar();
					if((c >= 97 && c <= 122) || (c >= 48 && c <= 57)) {
						username += c;
						username = username.toUpperCase().substring(0, Math.min(3, username.length()));
					}
				}
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
					if (Invaders.invaders.size() == 0) Game.win();
					return true;
				};
			}
		}
		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!gameOver){
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void gameOver(){
		gameOver = true;
		Defender.def.setImage("./img/explosion.png");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void addScore(String username, int score) {
		if (scoreTable.size() < 10) {
			scoreTable.put(username, score);
			return;
		}
		int min = Integer.MAX_VALUE;
		Entry<String, Integer> minEntry = null;
		for(Entry<String, Integer> s : scoreTable.entrySet()) {
			if(s.getValue() < min) {
				min = s.getValue();
				minEntry = s;
			}
		}
		if (score > min) {
			scoreTable.remove(minEntry);
			scoreTable.put(username, score);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(menu){
			if(e.getX()>225 && e.getX()<425){
				if(e.getY()>250 && e.getY()<325){
					//Jouer
					boutonClik = true;
					typeBouton = "jouer";
				}else if(e.getY()>350 && e.getY()<425){
					//Quitter
					boutonClik = true;
					typeBouton = "quitter";
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(menu){
			if(e.getX()>225 && e.getX()<425){
				if(e.getY()>250 && e.getY()<325){
					//Jouer
					menu = false;
				}else if(e.getY()>350 && e.getY()<425){
					//Quitter
					System.exit(0);
				}
			}
		}
		boutonClik = false;
	}

	public static void writeScores() {
		try {
			FileOutputStream fOut = new FileOutputStream("./data/scores.dat");
			ObjectOutputStream oOs = new ObjectOutputStream(fOut);
			oOs.writeObject(scoreTable);
			oOs.close();			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void readScores() {
		try {
			FileInputStream fIn = new FileInputStream("./data/scores.dat");
		    ObjectInputStream oIs = new ObjectInputStream(fIn);
		    scoreTable = (TreeMap<String, Integer>) oIs.readObject();
		    oIs.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
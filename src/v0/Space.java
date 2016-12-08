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
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import v0.Element.movement;


public class Space extends JComponent implements KeyListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImagePanel imgFond,imgTitre,imgGameOver,imgBJ,imgBJC,imgBQ,imgBQC,imgBR,imgBRC,imgBM,imgBMC;
	boolean moveLeft = false;
	boolean moveRight = false;
	boolean fire = false;
	boolean menu = true;
	boolean gameOver = false;
	boolean gameOver2 = false;
	boolean attente = true;
	boolean boutonClik = false;
	boolean leaderboard = false;
	String typeBouton;
	ThreadVaisseau tv;
	movement moveAdv = movement.RIGHT;
	Sound snd;
	
	Iterator<Invaders> inv;
	Iterator<Missile> mis;
	Iterator<Laser> las;
	Iterator<Bonus> bon;

	static int score;
	String username = "";
	int cursor = 0;

	//Affichage du rendu graphique du jeu
	public void paint(Graphics g) {
		super.paint(g);
		if(menu) {
			drawMenu(g);
			if(boutonClik)drawBoutonClik(g);
		} else {
			if(!gameOver)moveElements();
			drawBackground(g);
			paintLife(g);
			drawScore(g);
			Defender.def.drawOn(g);
			inv = Invaders.invaders.iterator();
			while (inv.hasNext()) inv.next().drawOn(g);
			mis = Missile.missiles.iterator();
			while (mis.hasNext())mis.next().drawOn(g);
			las = Laser.lasers.iterator();
			while (las.hasNext())las.next().drawOn(g);
			bon = Bonus.bonus.iterator();
			while (bon.hasNext())bon.next().drawOn(g);
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
				leaderboard = true;
			}
			moveLeft = false;
			moveRight = false;
			drawGameOver(g);
			drawScorePanel(g);
		}
		if(gameOver) gameOver2 = true;
	}
	
	//Renvoie vrai si l''utilisateur est sur l'écran de game over
	public boolean getGameOver(){return this.gameOver2;}

	public void start() {
		imgFond = Sprites.spritesMap.get("background");
		imgTitre = Sprites.spritesMap.get("titre");
		imgGameOver = Sprites.spritesMap.get("gameOver");
		imgBJ = Sprites.spritesMap.get("boutonJouer");
		imgBJC = Sprites.spritesMap.get("boutonJouerClick");
		imgBQ =Sprites.spritesMap.get("boutonQuitter");
		imgBQC = Sprites.spritesMap.get("boutonQuitterClick");
		imgBR = Sprites.spritesMap.get("boutonRejouer");
		imgBRC = Sprites.spritesMap.get("/boutonRejouerClick");
		imgBM = Sprites.spritesMap.get("boutonMenu");
		imgBMC = Sprites.spritesMap.get("boutonMenuClick");
		this.start(30, 30, 700, 600);
	}
	
	//Renvoie un iterator sur la liste des envahisseurs
	Iterator<Element> elementIterator() {
		ArrayList<Element> e = new ArrayList<Element>(Invaders.invaders);
		e.add(Defender.def);
		return e.iterator();
	}


	//Créé la fenêtre du jeu
	public void start (int x, int y, int width, int height) {
		JFrame window = new JFrame();
		window.setBounds(x, y, width, height);
		window.setTitle("Galaxian");
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

	
	//Affiche le menu principale
	public void drawMenu(Graphics g){
		drawBackground(g);
		imgTitre.paintComponent(g, 125, 100, 450, 100);
		imgBJ.paintComponent(g, 225, 250, 250, 75);
		imgBQ.paintComponent(g, 225, 350, 250, 75);
	}
	
	public void drawScorePanel(Graphics g){
		g.setColor(Color.WHITE);
		Font f = new Font("Arial", Font.BOLD, 30);
		Font f2 = new Font("Arial", Font.PLAIN, 20);
		g.setFont(f);
		drawStringCenter("SCORES", 350, 150, g);
		g.setFont(f2);
		int i = 0;
		for(Score s : Score.scoreTable) {
			g.drawString(s.getUsername(), 150, 170+(30*i));
			drawStringRight(s.getScore().toString(), 550, 170+(30*i), g);
			i++;
		}
		if(leaderboard) {
			g.drawString(username, 150, 190+(30*i));
			drawStringRight("" + score, 550, 190+(30*i), g);			
		}
	}
	
	public void drawScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		drawStringRight("" + score, 660, 25, g);
	}
	
	public void drawStringCenter(String s, int x, int y, Graphics g) {
		int shift = g.getFontMetrics().stringWidth(s)/2;
		g.drawString(s, x-shift, y);
	}
	
	public void drawStringRight(String s, int x, int y, Graphics g) {
		int shift = g.getFontMetrics().stringWidth(s);
		g.drawString(s, x-shift, y);
	}
	
	//Affiche un bouton avec une auréole jaune au moment du clique
	public void drawBoutonClik(Graphics g){
		if(typeBouton.equals("jouer")){
			imgBJC.paintComponent(g, 225, 250, 250, 75);
		}else if(typeBouton.equals("rejouer")) {
			imgBRC.paintComponent(g, 75, 450, 250, 75);
		}else if(typeBouton.equals("menu")) {
			imgBMC.paintComponent(g, 350, 450, 250, 75);
		}else if(typeBouton.equals("quitter")) {
			imgBQC.paintComponent(g, 225, 350, 250, 75);
		}

	}
	
	//Affiche l'écran de game over
	public void drawGameOver(Graphics g){
		drawBackground(g);
		imgGameOver.paintComponent(g, 75, 30, 550, 100);
		imgBR.paintComponent(g, 75, 450, 250, 75);
		imgBM.paintComponent(g, 350, 450, 250, 75);
	}

	//Affiche l'image de fond du jeu
	public void drawBackground(Graphics g){
		imgFond.paintComponent(g);
	}

	//Met en mouvements les éléments du jeu(missiles, ennemies et le vaisseau principale)
	public void moveElements(){
		moveMissiles();
		moveBonus();
		moveEnemys();
		moveLaser();
		DefenderEvolve();
	}

	// Deplacement des missiles a chaque tours
	public void moveMissiles() {
		for(int i = Missile.missiles.size()-1; i >= 0; i--) {
			if(Missile.missiles.size() > 0){
				Missile m = Missile.missiles.get(i);
				m.move();
				if((!(this.isCol(m)||(m.getY()<=0||m.getY()>=600)))) m.move();
				else m.destroy();
			}
		}
	}
	
	// Deplacement des bonus a chaque tours
	public void moveBonus(){
		for(int i = Bonus.bonus.size()-1; i >= 0; i--) {
			Bonus b = Bonus.bonus.get(i);
			b.move();
			if((!(this.isColBonus(b)||(b.getY()<=0||b.getY()>=600)))) b.move();
			else b.destroy();
		}
	}
	
	public void moveLaser(){
		for(int i = Laser.lasers.size()-1; i >= 0; i--) {
			Laser l = Laser.lasers.get(i);
			 l.move(moveAdv);
			if(this.isCol(l)) l.destroy();
			else l.destroyTemp();
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
				gameOver = true;
				Defender.def.setImage("explosion");
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
	
	//Evolution du vaisseau principale en fonction du score
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

	@Override
	//Fonction de gestion des évènements des appuie sur une touche du clavier
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
			//Flèche gauche du clavier
			case KeyEvent.VK_LEFT:
				if (!moveLeft) {
					if(tv != null){
						tv.arret();
					}
					tv = new ThreadVaisseau(Defender.def,"left");
					tv.start();
					moveLeft = true;
				}
				break;
				
			//Flèche droite du clavier
			case KeyEvent.VK_RIGHT:
				if (!moveRight) {
					if(tv != null)tv.arret();
					tv = new ThreadVaisseau(Defender.def,"right");
					tv.start();
					moveRight = true;
				}
				break;
				
			//Barre espace du clavier
			case KeyEvent.VK_SPACE:
				if(!fire){
					Defender.def.fire();
					snd = new Sound("./sound/fire.wav");
					snd.play();
					snd.interrupt();
				}
				fire = true;
				break;
				
			//Touche entrée du clavier
			case KeyEvent.VK_ENTER:
				if(leaderboard) {
					new Score(score, username);
					writeScores();
					leaderboard = false;					
				}
				break;
				
			//Touche effacer du clavier
			case KeyEvent.VK_BACK_SPACE:
				if (leaderboard) username = username.substring(0, Math.max(0, username.length() - 1));
				break;
				
			//Toute les autres touches du clavier
			default:
				if (leaderboard) {
					char c = e.getKeyChar();
					if((c >= 97 && c <= 122) || (c >= 48 && c <= 57)) {
						username += c;
						username = username.toUpperCase().substring(0, Math.min(3, username.length()));
					}
					break;
				}
			}
	}

	//Renvoie vrai si il y a une collision avec le missile en paramètre
	public boolean isCol(Element m) {
		boolean isLaser=false;
	
		if (m.isMissileEnnemy()){
			if(m.collideWith(Defender.def)){
				Defender.def.getDamage();
				if(Defender.def.getLife()<=0){
					gameOver = true;
					Defender.def.setImage("explosion");
				}
				return true;

			}
		}else{
			Iterator<Invaders> it = Invaders.invaders.iterator();
			while(it.hasNext()) {
				Invaders inv = it.next();
				if (m.collideWith(inv)) {
					inv.getDamage();
					if (Invaders.invaders.size() == 0) Game.win();
					if(m.isLaser()) isLaser=true;
					else return true;
				};
			}
		}
		return isLaser;
	}

	public boolean isColBonus(Bonus m) {
		if(m.collideWith(Defender.def)){
			m.action();
			return true;
		}
		return false;
	}
	
	@Override
	//Fonction de gestion des évènements de la relache sur une touche du clavier
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(!gameOver && moveLeft) {
				moveLeft = false;
				if(tv.getDir().equals("left")) tv.arret();
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(!gameOver && moveRight) {
				moveRight = false;
				if(tv.getDir().equals("right")) tv.arret();					
			}
			break;
		case KeyEvent.VK_SPACE:
			if(!gameOver) fire = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
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

	@Override
	//Fonction de gestion des évènements des appuies sur un clic de la souris
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
		}else if(gameOver2){
			if(e.getY()>450 && e.getY()<525){
				if(e.getX()>75 && e.getX()<325){
					//Rejouer
					boutonClik = true;
					typeBouton = "rejouer";
				}else if(e.getX()>350 && e.getX()<600){
					//Menu
					boutonClik = true;
					typeBouton = "menu";
				}
			}
		}
	}

	@Override
	//Fonction de gestion des évènements des relaches sur un clic de la souris
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
		}else if(gameOver2){
			if(e.getY()>450 && e.getY()<525){
				if(e.getX()>75 && e.getX()<325){
					//Rejouer
					gameOver = false;
					gameOver2 = false;
					attente = true;
					Game.resetGame();
				}else if(e.getX()>350 && e.getX()<600){
					//Menu
					menu = true;
					gameOver = false;
					gameOver2 = false;
					attente = true;
					Game.resetGame();
				}
			}
		}
		boutonClik = false;
	}

	public static void writeScores() {
		try {
			FileOutputStream fOut = new FileOutputStream("./data/scores.dat");
			ObjectOutputStream oOs = new ObjectOutputStream(fOut);
			oOs.writeObject(Score.scoreTable);
			oOs.close();			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void readScores() {
		try {
			FileInputStream fIn = new FileInputStream("./data/scores.dat");
		    ObjectInputStream oIs = new ObjectInputStream(fIn);
		    Score.scoreTable = (ArrayList<Score>) oIs.readObject();
		    oIs.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
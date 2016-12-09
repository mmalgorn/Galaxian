package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import defender.Defender;
import invaders.Invaders;
import projectile.Laser;
import projectile.Missile;
import ressources.Element;
import ressources.Element.movement;
import game.ThreadVaisseau;
import ressources.Sound;
import ressources.Sprites;

/*
 * La classe "Space" nous permet de dessiner et deplacer chaques elements.
 *  De plus elle permet de g�rer les actions sur les touches du clavier et les actions de la souris.
 */
public class Space extends JComponent implements KeyListener,MouseListener{
	
	public enum State {
		menu,inGame,leaderboard;
	}
	
	private static final long serialVersionUID = 1L;
	ImagePanel imgFond,imgTitre,imgGameOver,imgBJ,imgBJC,imgBS,imgBSC,imgBQ,imgBQC,imgBR,imgBRC,imgBM,imgBMC;
	boolean moveLeft = false;
	boolean moveRight = false;
	boolean fire = false;
	State state = State.menu;
	boolean gameOver = false;
	boolean gameOver2 = false;
	boolean attente = true;
	boolean boutonClik = false;
	boolean scorePan = false;
	boolean firstStart = true;
	boolean wait = true;
	boolean pause = false;
	String typeBouton;
	ThreadVaisseau tv;
	movement moveAdv = movement.RIGHT;
	Sound snd;
	
	Iterator<Invaders> inv;
	Iterator<Missile> mis;
	Iterator<Laser> las;
	Iterator<Bonus> bon;

	public static int score;
	public static String username = "";
	int cursor = 0;

	
	
	//Renvoie vrai si l''utilisateur est sur l'�cran de game over
	public boolean getGameOver(){return this.gameOver2;}

	public void start() {
		imgFond = Sprites.spritesMap.get("background");
		imgTitre = Sprites.spritesMap.get("titre");
		imgGameOver = Sprites.spritesMap.get("gameOver");
		imgBJ = Sprites.spritesMap.get("boutonJouer");
		imgBJC = Sprites.spritesMap.get("boutonJouerClick");
		imgBS = Sprites.spritesMap.get("boutonScores");
		imgBSC = Sprites.spritesMap.get("boutonScoresClick");
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

	//Cr�� la fen�tre du jeu
	public void start (int x, int y, int width, int height) {
		tv = new ThreadVaisseau();
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
		Score.readScores();
	}

	/*
	 * Fonctions dessinant chacuns de nos Elements
	 */
	
	//Affichage du rendu graphique du jeu
	public void paint(Graphics g) {
		super.paint(g);
			
		switch(state) {
		case menu:
			drawMenu(g);
			if(boutonClik)drawBoutonClik(g);
			break;
		case leaderboard:
			drawBackground(g);
			drawScorePanel(g);
			if(boutonClik)drawBoutonClik(g);
			break;
		case inGame:
			if(!gameOver && !pause)moveElements();
			drawBackground(g);
			paintLife(g);
			drawScore(g);
			Defender.def.drawLaser(g);
			Defender.def.drawFireRate(g);
			Defender.def.drawOn(g);
			Defender.def.inv();
			inv = Invaders.invaders.iterator();
			while (inv.hasNext())inv.next().drawOn(g);
			mis = Missile.missiles.iterator();
			while (mis.hasNext())mis.next().drawOn(g);
			las = Laser.lasers.iterator();
			while (las.hasNext())las.next().drawOn(g);
			bon = Bonus.bonus.iterator();
			while (bon.hasNext())bon.next().drawOn(g);
			break;
		}
		if(gameOver2){
			if(attente){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				attente = false;
				scorePan = true;
			}
			moveLeft = false;
			moveRight = false;
			drawGameOver(g);
			drawScorePanel(g);
		}
		if(gameOver) gameOver2 = true;
		if(pause){
			drawPause(g);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//Affiche le menu principale
	public void drawMenu(Graphics g){
		drawBackground(g);
		imgTitre.paintComponent(g, 125, 100, 450, 100);
		imgBJ.paintComponent(g, 225, 200, 250, 75);
		imgBS.paintComponent(g,225,300,250,75);
		imgBQ.paintComponent(g, 225, 400, 250, 75);
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
		if(state == State.leaderboard) {
			imgBM.paintComponent(g, 225, 450, 250, 75);
		} else if(scorePan) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.drawString(username, 150, 170+(30*i));
			drawStringRight("" + score, 550, 170+(30*i), g);			
		}
	}
	
	public void drawPause(Graphics g){
		Font f = new Font("Arial", Font.BOLD, 60);
		g.setFont(f);
		drawStringCenter("PAUSE", 350, 250, g);
		f = new Font("Arial",Font.ITALIC,30);
		g.setFont(f);
		char larrow = 	37;
		char rarrow = 39;
		drawStringCenter("Move : LEFT and RIGHT",350,310,g );
		drawStringCenter("fire : SPACE ",350,350,g );
		drawStringCenter("Laser :  C",350,390,g );
		
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
	
	//Affiche un bouton avec une aur�ole jaune au moment du clique
	public void drawBoutonClik(Graphics g){
		if(typeBouton.equals("jouer")){
			imgBJC.paintComponent(g, 225, 200, 250, 75);
		}else if(typeBouton.equals("rejouer")) {
			imgBRC.paintComponent(g, 75, 450, 250, 75);
		}else if(typeBouton.equals("scores")) {
			imgBSC.paintComponent(g,225,300,250,75);
		}else if(typeBouton.equals("menu")) {
			imgBMC.paintComponent(g, 350, 450, 250, 75);
		}else if(typeBouton.equals("quitter")) {
			imgBQC.paintComponent(g, 225, 400, 250, 75);
		}else if(typeBouton.equals("menu_leaderboard")) {
			imgBMC.paintComponent(g, 225, 450, 250, 75);
		}

	}
	
	//Affiche l'�cran de game over
	public void drawGameOver(Graphics g){
		drawBackground(g);
		imgGameOver.paintComponent(g, 75, 30, 550, 100);
		imgBR.paintComponent(g, 75, 450, 250, 75);
		imgBM.paintComponent(g, 350, 450, 250, 75);
	}

	//Affiche l'image de fond du jeu
	public void drawBackground(Graphics g) {
		imgFond.paintComponent(g);
	}
	
	// Affichage de la vie
	public void paintLife(Graphics g){
		Defender.def.drawLife(g);
		
	}
	
	/*
	 * Fonctions g�rant les mouvements de nos Elements
	 */
	
	//Met en mouvements les �l�ments du jeu(missiles, ennemies et le vaisseau principale)
	public void moveElements() {
		moveMissiles();
		moveBonus();
		moveEnemys();
		moveLaser();
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
	public void moveBonus() {
		for(int i = Bonus.bonus.size()-1; i >= 0; i--) {
			Bonus b = Bonus.bonus.get(i);
			b.move();
			if((!(this.isColBonus(b)||(b.getY()<=0||b.getY()>=600)))) b.move();
			else b.destroy();
		}
	}
	
	public void moveLaser() {
		for(int i = Laser.lasers.size()-1; i >= 0; i--) {
			Laser l = Laser.lasers.get(i);
			
			if(l.isMissileEnnemy())  l.move(moveAdv);
			else l.move(Defender.def.getPosition());
			l.remove();
			if(this.isCol(l)) l.destroy();
			else l.destroy();
			
		}
	}
	
	// Deplacement des Ennemis
	public void moveEnemys() {
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

	/*
	 * Fonctions permettant de d�t�cter des collisions entre les elements
	 */
	
	//Renvoie vrai si il y a une collision avec le missile en param�tre
	public boolean isCol(Element m) {
		boolean isLaser = false;
	
		if (m.isMissileEnnemy()){
			if(m.collideWith(Defender.def)){
				if(!m.isDest) Defender.def.getDamage();
				
				
				if(Defender.def.getLife()<=0){
					gameOver = true;
					Defender.def.setImage("explosion");
				}
				return true;

			}
		}else{
			for(int i = Invaders.invaders.size() - 1; i >= 0; i--) {			
				Invaders inv = Invaders.invaders.get(i);
				if (m.collideWith(inv)) {
					inv.getDamage();
					if (Invaders.invaders.size() == 0)
						try {
							Game.win();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if(m.isLaser()) isLaser=true;
					else return true;
				};
			}
		}
		return isLaser;
	}

	// Renvoie vrai si il y a une collision entre un bonus et le Defender
	public boolean isColBonus(Bonus m) {
		if(m.collideWith(Defender.def)){
			m.action();
			return true;
		}
		return false;
	}
	
	/*
	 * Fonctions permettant de g�rer les actions sur les touches de clavier et la souris
	*/
	
	@Override
	//Fonction de gestion des �v�nements des appuie sur une touche du clavier
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
		
			//Fl�che gauche du clavier
			case KeyEvent.VK_LEFT:
				if (!moveLeft && !pause) {
					tv.arret();
					tv.setDir("left");
					tv.reset();
					moveLeft = true;
				}
				break;
				
			//Fl�che droite du clavier
			case KeyEvent.VK_RIGHT:
				if (!moveRight && !pause) {
					tv.arret();
					tv.setDir("right");
					tv.reset();
					moveRight = true;
				}
				break;
				
			//Barre espace du clavier
			case KeyEvent.VK_SPACE:
				if(!fire && !pause){
					Defender.def.fire();
					snd = Sound.soundMap.get("fire");
					snd.play();
					snd.interrupt();
				}
				fire = true;
				break;
			
			case KeyEvent.VK_C:
				if(!fire && !pause) Defender.def.fireLaser();
				break;
				
			//Touche entr�e du clavier
			case KeyEvent.VK_ENTER:
				if(scorePan && !pause) {
					new Score(score, username);
					Score.writeScores();
					scorePan = false;					
				}
				break;
				
			//Touche effacer du clavier
			case KeyEvent.VK_BACK_SPACE:
				if (scorePan && !pause) username = username.substring(0, Math.max(0, username.length() - 1));
				break;
				
			case KeyEvent.VK_ESCAPE:
				if(pause){
					pause = false;
				}else{
					pause = true;
					tv.arret();
				}
				break;
				
			//Toute les autres touches du clavier
			default:
				if (scorePan && !pause) {
					char c = e.getKeyChar();
					if((c >= 97 && c <= 122) || (c >= 48 && c <= 57)) {
						username += c;
						username = username.toUpperCase().substring(0, Math.min(3, username.length()));
					}
					break;
				}
			}
	}
	
	
	@Override
	//Fonction de gestion des �v�nements de la relache sur une touche du clavier
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(!gameOver && moveLeft) {
					moveLeft = false;
					if(tv.getDir().equals("left"))tv.arret();
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(!gameOver && moveRight) {
					moveRight = false;
					if(tv.getDir().equals("right"))tv.arret();					
				}
				break;
			case KeyEvent.VK_SPACE:
				if(!gameOver) fire = false;
				break;
		}
	}

	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	//Fonction de gestion des �v�nements des appuies sur un clic de la souris
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state == State.menu){
			if(e.getX()>225 && e.getX()<425){
				if(e.getY()>200 && e.getY()<275){
					//Jouer
					boutonClik = true;
					typeBouton = "jouer";
				}else if(e.getY()>300 && e.getY()<375){
					//Scores
					boutonClik = true;
					typeBouton = "scores";
				}else if(e.getY()>400 && e.getY()<475){
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
		} else if(state == State.leaderboard) {
			if((e.getY() > 450 && e.getY() < 525) && (e.getX() > 225 && e.getX() < 475)){
				//Menu
				boutonClik = true;
				typeBouton = "menu_leaderboard";
			}
		}
	}

	@Override
	//Fonction de gestion des �v�nements des relaches sur un clic de la souris
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state == State.menu){
			if(e.getX()>225 && e.getX()<425){
				if(e.getY()>200 && e.getY()<275){
					//Jouer
					state = State.inGame;
					moveLeft = false;
					moveRight = false;
					tv.arret();
					if(firstStart){
						tv.start();
						firstStart = false;
					}
				}else if(e.getY()>300 && e.getY()<375){
					//Scores
					state = State.leaderboard;
				}else if(e.getY()>400 && e.getY()<475){
					//Quitter
					System.exit(0);
				}
			}
		} else if (state == State.leaderboard) {
			if((e.getY() > 450 && e.getY() < 525) && (e.getX() > 225 && e.getX() < 475)){
				//Menu
				state = State.menu;
			}
		} else if (gameOver2){
			if(e.getY()>450 && e.getY()<525){
				if(e.getX()>75 && e.getX()<325){
					//Rejouer
					gameOver = false;
					gameOver2 = false;
					attente = true;
					moveLeft = false;
					moveRight = false;
					tv.arret();
					Game.resetGame();
				}else if(e.getX()>350 && e.getX()<600){
					//Menu
					state = State.menu;
					gameOver = false;
					gameOver2 = false;
					attente = true;
					Game.resetGame();
				}
			}
		}
		boutonClik = false;
	}
}

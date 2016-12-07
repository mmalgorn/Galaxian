package v0;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import v0.Element.movement;

// credits: 
//	random color: http://stackoverflow.com/questions/4246351/creating-random-colour-in-java

public class Game {
	static ArrayList<Element> enemys = new ArrayList<Element>();
	static int nbEnemy = 0 ; 
	static int nbEnemyMax = 45;
	static int i;
	static Sound theme;
	static int nbLvl = 1;
	
	public static void win(){
		nbLvl++;
		System.out.println("You Win !");
		inizialise();
	}
	
	public static void resetGame(){
		new Defender(new Point(350,450));
		inizialise();
	}
	public static void inizialise(){
		nbEnemy = 0;
		Invaders.invaders.clear();
		Missile.missiles.clear();
		for(int i = 0 ; i < (Level.levelMap.get(nbLvl).nbFire);i++ , nbEnemy++){
			new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		int nbEnemyTmp = nbEnemy;
		for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbShield);i++ , nbEnemy++){
			new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		nbEnemyTmp = nbEnemy;
		for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbPeon);i++ , nbEnemy++){
			new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
	}
	
	public static void main(String[] args) throws IOException {
		theme = new Sound("./sound/mainTheme.wav");
		theme.loop();
		Space root = new Space();
		new Defender(new Point(350,450));
		
		inizialise();

		root.start();
		
  		while (true) {
			try {
				Thread.sleep(30);
				if(Invaders.invaders.isEmpty()) win();
				root.repaint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

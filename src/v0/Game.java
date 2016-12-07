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
	static int nbEnemy = 35;
	static int nbEnemyMax = 45;
	static int i;
	static Sound theme;

	public static void win(){
		System.out.println("You WIN !");
		Invaders.invaders.clear();
		Missile.missiles.clear();
		if(nbEnemy+5 <= nbEnemyMax) nbEnemy = nbEnemy +5;
		for(i=0;i<nbEnemy/2;i++){
			new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		for(i=nbEnemy/2;i<nbEnemy;i++){

			new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}

	}

	public static void resetGame(){
		Invaders.invaders.clear();
		Missile.missiles.clear();
		Space.score = 0;
		new Defender(new Point(350,450));
		if(nbEnemy+5 <= nbEnemyMax) nbEnemy = nbEnemy +5;
		for(i=0;i<nbEnemy/2;i++){
			new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		for(i=nbEnemy/2;i<nbEnemy;i++){

			new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
	}

	public static void main(String[] args) throws IOException {
		theme = new Sound("./sound/mainTheme.wav");
		theme.loop();
		Space root = new Space();
		new Defender(new Point(350,450));

		for(i=nbEnemy/2;i<nbEnemy;i++){
			for(i=nbEnemy/2;i<nbEnemy;i++){
				new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
			}
			for(i=0;i<nbEnemy/2;i++){

				new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
			}		

			//new BossInvaders(new Point(100,100));
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
}

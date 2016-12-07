package v0;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import v0.Element.movement;

// credits: 
//	random color: http://stackoverflow.com/questions/4246351/creating-random-colour-in-java

public class Game {
<<<<<<< HEAD
	static ArrayList<Element> enemys = new ArrayList<Element>();
	static int nbEnemy = 35;
	static int nbEnemyMax = 45;
	static int i;
	public static void win(){
		System.out.println("You WIN !");
		if(nbEnemy+5 <= nbEnemyMax) nbEnemy = nbEnemy +5;
=======

	public static void main(String[] args) throws IOException {
		Space root = new Space();
		int i,nbEnnemy = 35;
>>>>>>> branch 'master' of https://github.com/mmalgorn/Galaxian.git
		
<<<<<<< HEAD
		
		for(i=nbEnemy/2;i<nbEnemy;i++){
=======
		new Defender(new Point(350,450));
		for(i=0;i<nbEnnemy/2;i++){
>>>>>>> branch 'master' of https://github.com/mmalgorn/Galaxian.git
			new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		for(i=0;i<nbEnemy/2;i++){
			new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
<<<<<<< HEAD
		
	}
	public static void main(String[] args) throws IOException {
		Space root = new Space();
		new Defender(new Point(350,450));
		
		for(i=nbEnemy/2;i<nbEnemy;i++){
			new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		for(i=0;i<nbEnemy/2;i++){
			
			new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		
		
=======
>>>>>>> branch 'master' of https://github.com/mmalgorn/Galaxian.git
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

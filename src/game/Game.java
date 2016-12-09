package game;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import defender.Defender;
import invaders.BossInvaders;
import invaders.FireInvaders;
import invaders.Invaders;
import invaders.PeonInvaders;
import invaders.ShieldInvaders;
import projectile.Laser;
import projectile.Missile;
import ressources.Element;
import ressources.Level;
import ressources.Sound;

// credits: 
//	random color: http://stackoverflow.com/questions/4246351/creating-random-colour-in-java

public class Game {
	static ArrayList<Element> enemys = new ArrayList<Element>();
	static int nbEnemy = 0 ; 
	static int nbEnemyMax = 45;
	static int i;
	static int dif = 0;
	public static Sound theme = Sound.soundMap.get("theme");
	static int nbLvl = 0;
	
	public static void win() throws InterruptedException{
		if(nbLvl+1 >= 15){
			nbLvl = 0;
			dif = dif +2;
		}else{
			nbLvl++;
		}
		System.out.println("You Win !");
		inizialise();
	}
	
	public static void resetGame(){
		new Defender(new Point(350,450));
		Bonus.bonus.clear();
		nbLvl = 0;
		Space.score = 0;
		Bonus.bonus.clear();
		inizialise();
		Space.username = "";
	}
	
	public static void inizialise(){
		nbEnemy = 0;
		Invaders.invaders.clear();
		Missile.missiles.clear();
		Laser.lasers.clear();
		if(nbLvl < 5){
			if(!(Level.levelMap.get(nbLvl).boss.equals("null"))){
				new BossInvaders(new Point(100,100),Level.levelMap.get(nbLvl).boss,2+dif);
			}else{
				for(int i = 0 ; i < (Level.levelMap.get(nbLvl).nbFire);i++ , nbEnemy++){
					new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),2+dif,Invaders.team.red);
				}
				int nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbShield);i++ , nbEnemy++){
					new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),2+dif,Invaders.team.red);
				}
				nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbPeon);i++ , nbEnemy++){
					new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),2+dif,Invaders.team.red);
				}		
			}
		}else if (nbLvl <10){
			if(!(Level.levelMap.get(nbLvl).boss.equals("null"))){
				new BossInvaders(new Point(100,100),Level.levelMap.get(nbLvl).boss,4+dif);
			}else{
				for(int i = 0 ; i < (Level.levelMap.get(nbLvl).nbFire);i++ , nbEnemy++){
					new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),4+dif,Invaders.team.yellow);
				}
				int nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbShield);i++ , nbEnemy++){
					new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),4+dif,Invaders.team.yellow);
				}
				nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbPeon);i++ , nbEnemy++){
					new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),4+dif,Invaders.team.yellow);
				}
			}
		}else if (nbLvl < 15){
			if(!(Level.levelMap.get(nbLvl).boss.equals("null"))){
				new BossInvaders(new Point(100,100),Level.levelMap.get(nbLvl).boss,6+dif);
			}else{
				for(int i = 0 ; i < (Level.levelMap.get(nbLvl).nbFire);i++ , nbEnemy++){
					new FireInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),6+dif,Invaders.team.grey);
				}
				int nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbShield);i++ , nbEnemy++){
					new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),6+dif,Invaders.team.grey);
				}
				nbEnemyTmp = nbEnemy;
				for(int i = nbEnemyTmp ; i < nbEnemyTmp+(Level.levelMap.get(nbLvl).nbPeon);i++ , nbEnemy++){
					new PeonInvaders(new Point(100+((i%12)*50),100+(i/12) * 50),6+dif,Invaders.team.grey);
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
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

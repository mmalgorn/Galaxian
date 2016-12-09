package game;

import defender.Defender;
import ressources.Element;
import ressources.Element.movement;

public class ThreadVaisseau extends Thread{
	String dir;
	boolean arretThread = true;
	
	public ThreadVaisseau(){
	}
	
	public void arret(){
		arretThread = true;
	}

	public void setDir(String d) { dir = d; }
	public String getDir() { return dir; }
	
	public void reset(){
		arretThread = false;
	}
	
	public void run(){
		while(!isInterrupted()){
			while(!arretThread){
				if(dir.equals("left")){
					Defender.def.moveLeft();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(dir.equals("right")){
					Defender.def.moveRight();
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

}


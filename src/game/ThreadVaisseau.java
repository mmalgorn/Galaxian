package game;

import defender.Defender;
import ressources.Element;
import ressources.Element.movement;

public class ThreadVaisseau extends Thread{
	Element elem;
	String dir;
	boolean arretThread = true;
	
	public ThreadVaisseau(){
	}
	
	public void arret(){
		arretThread = true;
	}

	public void setDir(String d){dir = d;}
	public String getDir(){return dir;}
	public void reset(){
		arretThread = false;
	}
	
	public void run(){
		elem = Defender.def;
		while(!isInterrupted()){
			while(!arretThread){
				if(dir.equals("left")){
					if(elem.getX()>10){
						elem.move(movement.LEFT);
						try {
							Thread.sleep(40);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else if(dir.equals("right")){
					if(elem.getX()<590){
						elem.move(movement.RIGHT);
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

}


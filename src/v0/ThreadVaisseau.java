package v0;

import v0.Element.movement;

public class ThreadVaisseau extends Thread{
	Element elem;
	String dir;
	boolean arretThread;
	
	public ThreadVaisseau(Element e,String d){
		this.elem = e;
		this.dir = d;
	}
	
	public void arret(){
		arretThread = true;
	}
	
	public String getDir(){
		return dir;
	}
	
	public void run(){
		arretThread = false;
		if(dir.equals("left"))
			while(!arretThread)
				if(elem.getX()>10){
					elem.move(movement.LEFT);
					try {
						Thread.sleep(40);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		if(dir.equals("right"))
			while(!arretThread)
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

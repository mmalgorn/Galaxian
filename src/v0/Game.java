package v0;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import v0.Element.movement;

// credits: 
//	random color: http://stackoverflow.com/questions/4246351/creating-random-colour-in-java

public class Game {

	public static void main(String[] args) throws IOException {
		Space root = new Space();
		ArrayList<Element> ennemys = new ArrayList<Element>();
		int i,nbEnnemy = 10;

		Element defender =  new Defender(new Point(350,450));
		root.addElement(defender);
		
		for(i=0;i<nbEnnemy;i++){
			ennemys.add(new FireInvaders(new Point(100+(i*50),100)));
			root.addElement(ennemys.get(i));
		}
		root.start();
  		while (true) {
			try {
				Thread.sleep(100);
				Iterator<Element> iter = root.elementIterator();
				
				while(iter.hasNext()){
					
					Element m = iter.next();
					if(m instanceof Missile){
						m.move(movement.TOP);
					}
					
					if(!(m instanceof Defender)){
						//m.move(movement.RIGHT);
					}
					
				}
				root.repaint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

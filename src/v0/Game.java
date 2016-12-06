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
		int i,nbEnnemy = 35;

		new Defender(new Point(350,450));
		
		for(i=0;i<nbEnnemy;i++){
			new ShieldInvaders(new Point(100+((i%12)*50),100+(i/12) * 50));
		}
		root.start();
  		while (true) {
			try {
				Thread.sleep(30);
				root.repaint();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

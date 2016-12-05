package v0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

import v0.Element.movement;


public class Space extends JComponent implements ActionListener,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Set <Element> contents = Collections.synchronizedSet(new HashSet<Element>());
	
	java.awt.Button RightButton;
	java.awt.Button LeftButton;

	void addElement(Element anElement) {
		contents.add(anElement);
	}

	void removeElement(Element anElement) {
		contents.remove(anElement);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Iterator<Element> itor = this.elementIterator();
		while (itor.hasNext()) {
			itor.next().drawOn(g);
		}
	}
	
	public void start() {
		this.start(30, 30, 800, 600);
	}
	
	Iterator<Element> elementIterator() { 
		ArrayList<Element> cpy = new ArrayList<Element>(contents);
		Iterator<Element> itor = cpy.iterator();
		return itor;
	}
	
	
	public void start (int x, int y, int width, int height) {
		JFrame window = new JFrame();
		window.setBounds(x, y, width, height);
		window.setTitle("Galaxian : Le meilleur jeu de Space Invaders de TOUTE la galaxie !!!!!!!!!!");
		window.setIconImage(new ImageIcon("./img/vaisseau_icon.png").getImage());
		window.getContentPane().add(this);
		window.setVisible(true);
		window.addKeyListener(this);
		new GestFenetre(window);
		Universe.addSpace(this);
		
		RightButton = new java.awt.Button("Right");
		LeftButton = new java.awt.Button("Left");
  	    RightButton.addActionListener(this);
  	    LeftButton.addActionListener(this);
		add(RightButton);
		add(LeftButton);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		boolean trouvee = false;
		Element elem = null;
		
		Iterator<Element> iter = elementIterator();
		while(iter.hasNext() && (!trouvee)){
			elem = iter.next();
			if(elem.isDefender())trouvee = true;
		}
		switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT :
				if(elem.getX()>50)elem.move(movement.LEFT);
				break;
				
			case KeyEvent.VK_RIGHT :
				if(elem.getX()<650)elem.move(movement.RIGHT);
				break;
			default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		
		/*if (object.equals(RightButton))
			
		else if (object.equals(LeftButton));*/
			
	}
	
}
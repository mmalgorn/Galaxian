package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class GestFenetre extends WindowAdapter {
	public GestFenetre(JFrame window) {
		window.addWindowListener(this);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
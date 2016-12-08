package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private BufferedImage image;
	
	public ImagePanel(String path) throws IOException {
		this.image = ImageIO.read(new File(path));
	}
	public void paintComponent(Graphics g,double d,double e, double f, double h) {
		super.paintComponent(g);
		g.drawImage(image, (int)d, (int)e, (int)f, (int)h, null); 
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0,null); 
	}
	protected void setImage(String path) throws IOException{
		this.image = ImageIO.read(new File(path));
	}
}
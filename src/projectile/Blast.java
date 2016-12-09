package projectile;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import ressources.Element;

public class Blast extends Element {

	public static List<Blast> blasts = new ArrayList<Blast>();

	private movement direction;

	public Blast(Point p, movement m) {
		blasts.add(this);
		this.setPosition(p);
		this.height = 10;
		this.width = 20;
		this.speed = 10;
		this.direction = m;
		this.setImage("blast");
		this.move();
	}

	public void destroy() {
		blasts.remove(this);
	}

	public void move() {
		move(direction);
		this.width += 12;
		setPosition(new Point((int) getPosition().getX()-6, (int) getPosition().getY()));
	}
}

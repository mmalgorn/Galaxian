package game;

import java.util.ArrayList;
import java.util.List;

import ressources.Element;

public abstract class Bonus extends Element {

	static List<Bonus> bonus = new ArrayList<Bonus>();
	protected String type;

	public Bonus() {
		bonus.add(this);
	}

	public void move() {
		this.move(movement.BOTTOM);
	}

	public void destroy() {
		Space.score += 50;
		bonus.remove(this);
		// Sound snd = new Sound("./sound/explosion.wav");
		// snd.play();
	}

	public void action() {

	}

}

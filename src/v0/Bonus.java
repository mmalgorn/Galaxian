package v0;

import java.util.ArrayList;
import java.util.List;

public abstract class Bonus extends Element{
	
	static List<Bonus> bonus = new ArrayList<Bonus>();

	public Bonus() {
		bonus.add(this);
	}
	
	public void move() {
		this.move(movement.BOTTOM);
	}
	
	public void destroy() {
		Space.score+=50;
		bonus.remove(this);
		//Sound snd = new Sound("./sound/explosion.wav");
		//snd.play();
	}
	
}

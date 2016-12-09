package game;

import java.awt.Point;

import defender.Defender;

public class BonusBlast extends Bonus {
	public BonusBlast(Point p) {
		this.width = 30;
		this.height = 10;
		this.speed = 2;
		this.setPosition(p);
		this.type = "fire_rate";
		this.setImage("blast");
	}

	@Override
	public void action() {
		Defender.def.addBlast(1);
	}
}

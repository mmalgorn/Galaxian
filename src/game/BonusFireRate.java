package game;

import java.awt.Point;

import defender.Defender;

public class BonusFireRate extends Bonus {
	
	public BonusFireRate(Point p) {
		this.width = 30;
		this.height = 30;
		this.speed = 2;
		this.setPosition(p);
		this.type = "fire_rate";
		this.setImage("bonus_fire_rate");
	}
	
	public void action(){
		Defender.def.addFireRate(10);
	}

}

package game;

import java.awt.Point;

import defender.Defender;

public class BonusBouclier extends Bonus {

	public BonusBouclier(Point p) {
		this.width = 30;
		this.height = 30;
		this.speed = 2;
		this.setPosition(p);
		this.type = "bouclier";
		this.setImage("bonus_bouclier");
	}

	@Override
	public void action() {
		Defender.def.addShield();
	}

}

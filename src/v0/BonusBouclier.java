package v0;

import java.awt.Point;

public class BonusBouclier extends Bonus{

	public BonusBouclier(Point p) {
		this.width = 50;
		this.height = 50;
		this.speed = 2;
		this.setPosition(p);
		this.setImage("./img/bonus_bouclier.png");
	}

}

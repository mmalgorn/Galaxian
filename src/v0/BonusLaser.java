package v0;

import java.awt.Point;

public class BonusLaser extends Bonus{

	public BonusLaser(Point p) {
		this.width = 50;
		this.height = 50;
		this.speed = 2;
		this.setPosition(p);
		this.setImage("./img/bonus_laser.png");
	}

}

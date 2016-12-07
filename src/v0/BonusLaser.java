package v0;

import java.awt.Point;

public class BonusLaser extends Bonus{

	public BonusLaser(Point p) {
		this.width = 30;
		this.height = 30;
		this.speed = 2;
		this.setPosition(p);
		this.type = "laser";
		this.setImage("./img/bonus_laser.png");
	}
	
	public void action(){
		//Defender.def.getlaser();
	}

}

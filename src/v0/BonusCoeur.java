package v0;

import java.awt.Point;

public class BonusCoeur extends Bonus{

	public BonusCoeur(Point p) {
		this.width = 30;
		this.height = 30;
		this.speed = 2;
		this.setPosition(p);
		this.type = "coeur";
		this.setImage("full_heart");
	}

	
	public void action(){
		Defender.def.heal();
	}

}

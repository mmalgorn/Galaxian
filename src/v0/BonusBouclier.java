package v0;

import java.awt.Point;

public class BonusBouclier extends Bonus{

	public BonusBouclier(Point p) {
		this.width = 30;
		this.height = 30;
		this.speed = 2;
		this.setPosition(p);
		this.type = "bouclier";
		this.setImage("bonus_bouclier");
	}

	
	public void action(){
		
	}

}

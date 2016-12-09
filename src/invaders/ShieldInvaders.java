package invaders;

import java.awt.Point;

/*
 * Vaisseau invaders avec deux fois plus de vie
 */
public class ShieldInvaders extends Invaders {

	public ShieldInvaders(Point p, int sp, Invaders.team team) {
		this.width = 50;
		this.height = 50;
		this.speed = sp;
		this.life = 100;
		this.tm = team;
		this.setPosition(p);
		switch (team) {
		case red:
			this.setImage("red_shield_invaders");
			break;
		case grey:
			this.setImage("grey_shield_invaders");
			break;
		case yellow:
			this.setImage("yellow_shield_invaders");
			break;
		}
	}

	/*
	 * 
	 */
	@Override
	public void getDamage() {
		super.getDamage();
		if (life <= 50) {
			switch (this.tm) {
			case red:
				this.setImage("red_peon_invaders");
				break;
			case grey:
				this.setImage("grey_peon_invaders");
				break;
			case yellow:
				this.setImage("yellow_peon_invaders");
				break;
			}
		}
	}
}

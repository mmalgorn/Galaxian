package invaders;

import java.awt.Point;
import java.util.Random;

import defender.Defender;
import projectile.Laser;
import projectile.Missile;
import ressources.Sound;

public class BossInvaders extends Invaders {

	private int tmpLaser = 0;

	public BossInvaders(Point p, String name, int sp) {
		this.width = 300;
		this.height = 200;
		this.speed = sp;
		this.life = 1500;
		this.damageAmount = 25;
		this.setPosition(p);
		Sound snd = null;
		switch (name) {
		case "red":
			this.setImage("boss_invaders_red");
			snd = Sound.soundMap.get("boss1");
			break;
		case "grey":
			this.setImage("boss_invaders_grey");
			snd = Sound.soundMap.get("boss2");
			break;
		case "yellow":
			this.setImage("boss_invaders_yellow");
			snd = Sound.soundMap.get("boss3");
			break;
		}

		snd.gainControl(6);
		snd.play();

	}

	@Override
	public void fire() {
		Random randomGenerator = new Random();
		if (randomGenerator.nextInt(30) < 1) {

			Point p = new Point((int) this.getX() + randomGenerator.nextInt(300),
					(int) (this.getY() + randomGenerator.nextInt(200)));
			new Missile(p, movement.BOTTOM, true);
		}
		if (tmpLaser == 0) {
			Point p2 = new Point((int) (this.getX() + width / 2 - 9), (int) (this.getY() + height - 50));
			new Laser(p2, this.speed, true);
			tmpLaser = 30;

		} else tmpLaser--;
	}

	@Override
	public void destroy() {
		super.destroy();
		Defender.def.evolve();
	}

}

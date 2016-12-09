package ressources;
import java.io.IOException;
import java.util.HashMap;

import game.ImagePanel;

public final class Sprites {
	public static HashMap<String,ImagePanel> spritesMap  = new HashMap<String,ImagePanel>();
	static{
		try{
			// fond et menu
				spritesMap.put("background", new ImagePanel("./img/background.jpg"));
				spritesMap.put("boutonJouer", new ImagePanel("./img/boutonJouer.png"));
				spritesMap.put("boutonJouerClick", new ImagePanel("./img/boutonJouerClick.png"));
				spritesMap.put("boutonScores", new ImagePanel("./img/boutonScores.png"));
				spritesMap.put("boutonScoresClick", new ImagePanel("./img/boutonScoresClick.png"));
				spritesMap.put("boutonMenu", new ImagePanel("./img/boutonMenu.png"));
				spritesMap.put("boutonMenuClick", new ImagePanel("./img/boutonMenuClick.png"));
				spritesMap.put("boutonQuitter", new ImagePanel("./img/boutonQuitter.png"));
				spritesMap.put("boutonQuitterClick", new ImagePanel("./img/boutonQuitterClick.png"));
				spritesMap.put("boutonRejouer", new ImagePanel("./img/boutonRejouer.png"));
				spritesMap.put("boutonRejouerClick", new ImagePanel("./img/boutonRejouerClick.png"));
				spritesMap.put("titre", new ImagePanel("./img/titre.png"));
				spritesMap.put("gameOver", new ImagePanel("./img/gameOver.png"));
			//Icone
				spritesMap.put("empty_heart", new ImagePanel("./img/empty_heart.png"));
				spritesMap.put("full_heart", new ImagePanel("./img/full_heart.png"));
				spritesMap.put("bonus_bouclier", new ImagePanel("./img/bonus_bouclier.png"));
				spritesMap.put("bonus_laser", new ImagePanel("./img/bonus_laser.png"));
				spritesMap.put("bonus_fire_rate", new ImagePanel("./img/bonus_fire_rate.png"));
			// projectiles
				spritesMap.put("missile_defender", new ImagePanel("./img/missile_defender.png"));
				spritesMap.put("missile", new ImagePanel("./img/missile.png"));
				spritesMap.put("laser_defender", new ImagePanel("./img/laser_defender.png"));
				spritesMap.put("laser", new ImagePanel("./img/laser.png"));
			//Defender
				spritesMap.put("vaisseau1", new ImagePanel("./img/vaisseau1.png"));
				spritesMap.put("vaisseau2", new ImagePanel("./img/vaisseau2.png"));
				spritesMap.put("vaisseau3", new ImagePanel("./img/vaisseau3.png"));
				spritesMap.put("vaisseau1_shield", new ImagePanel("./img/vaisseau1_shield.png"));
				spritesMap.put("vaisseau2_shield", new ImagePanel("./img/vaisseau2_shield.png"));
				spritesMap.put("vaisseau3_shield", new ImagePanel("./img/vaisseau3_shield.png"));
			//Invaders
				spritesMap.put("red_fire_invaders", new ImagePanel("./img/red_fire_invaders.png"));
				spritesMap.put("red_shield_invaders", new ImagePanel("./img/red_shield_invaders.png"));
				spritesMap.put("red_peon_invaders", new ImagePanel("./img/red_peon_invaders.png"));
				spritesMap.put("yellow_fire_invaders", new ImagePanel("./img/yellow_fire_invaders.png"));
				spritesMap.put("yellow_shield_invaders", new ImagePanel("./img/yellow_shield_invaders.png"));
				spritesMap.put("yellow_peon_invaders", new ImagePanel("./img/yellow_peon_invaders.png"));
				spritesMap.put("grey_fire_invaders", new ImagePanel("./img/grey_fire_invaders.png"));
				spritesMap.put("grey_shield_invaders", new ImagePanel("./img/grey_shield_invaders.png"));
				spritesMap.put("grey_peon_invaders", new ImagePanel("./img/grey_peon_invaders.png"));
			//Boss
				spritesMap.put("boss_invaders_red", new ImagePanel("./img/boss_invaders_red.png"));
				spritesMap.put("boss_invaders_grey", new ImagePanel("./img/boss_invaders_grey.png"));
				spritesMap.put("boss_invaders_yellow", new ImagePanel("./img/boss_invaders_yellow.png"));
			// Autres
				spritesMap.put("explosion", new ImagePanel("./img/explosion.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}

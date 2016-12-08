package ressources;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
 /*
  * Classe permettant d'instancier un nouveau son , afin de pouvoir l'ajouter dans notre partie
  * trouvez sur StackOverflow : http://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-mutiple-sounds-in-a-game
  */
 
public class Sound extends Thread {
	public static HashMap<String,Sound> soundMap  = new HashMap<String,Sound>();
	static{
		soundMap.put("fire", new Sound("./sound/fire.wav"));
		soundMap.put("explosion", new Sound("./sound/explosion.wav"));
		soundMap.put("theme", new Sound("./sound/mainTheme.wav"));
		soundMap.put("laser", new Sound("./sound/laser.wav"));
	}
	private Clip clip;
    public Sound(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
             // load the sound into memory (a Clip)
                clip = AudioSystem.getClip();
                clip.open(sound);
            }
            else {
                throw new RuntimeException("Sound: file not found: " + fileName);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Malformed URL: " + e);
        }
        catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
            throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
        }

    // play, stop, loop the sound clip
    }
    public void play(){
        clip.setFramePosition(0);  // Must always rewind!
        clip.start();
       // clip.destroy();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}

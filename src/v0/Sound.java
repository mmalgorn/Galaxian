package v0;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
 
 
 
public class Sound extends Thread {
    private URL u1;//l'url de ton fichier son
    private static AudioClip s1;//le son créé depuis ton url
 
    public Sound() {
        u1 = this.getClass().getClassLoader().getResource("./sound/fire.wav");
        s1 = Applet.newAudioClip(u1);
    }
    public static void jouer() {
        s1.play();
    }
    public void jouerEnBoucle() {
        s1.loop();
    }
    public void arreter() {
        s1.stop();
    }
}
import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
 public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("Blaster.wav"));
 public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("Force.wav"));
 public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("Starwars.wav"));
}
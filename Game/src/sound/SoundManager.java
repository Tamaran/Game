package sound;

import java.io.IOException;
import java.util.HashMap;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManager {
	
	public static final SoundManager manager = new SoundManager();
	
	private HashMap<String, Sound> data = new HashMap();
	
	public Sound getSound(String name) throws Exception{
		Sound s = data.get(name);
		if(s == null){
			s = new Sound(name);
			data.put(name, s);
		}
		return s;
	}

}

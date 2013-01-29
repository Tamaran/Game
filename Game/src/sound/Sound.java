package sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	private Clip clip;
	
	public Sound(String name) throws Exception{
		File f = new File(Sound.class.getResource(name).getFile());
		AudioInputStream ais = AudioSystem.getAudioInputStream(f);
		clip = AudioSystem.getClip();
		clip.open(ais);
		ais.close();
	}
	
	public void play(){
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop(){
		clip.stop();
	}
}

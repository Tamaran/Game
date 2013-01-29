package graphics.textures;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import util.Logger;

/**
 * Loads Textures and holds them.
 * 
 * @author Tamaran
 *
 */
public class TextureLoader {
	
	public static final TextureLoader loader = new TextureLoader();
	
	private HashMap<File, Texture> map = new HashMap();
	
	/**
	 * Returns the Texture with the given filename. The Texture is loaded if necessary.
	 * 
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public Texture getTexture(File f){
		try{
			Texture t = map.get(f);
			if(t == null){
				long time = System.currentTimeMillis();
				t = new Texture(f);
                                Logger.D("Loading "+f+" finished after "+(System.currentTimeMillis()-time)+"ms");
				map.put(f, t);
			}
			return t;
		}catch(Exception e){
			return null;
		}

	}

}

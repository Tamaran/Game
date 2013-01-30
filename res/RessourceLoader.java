package res;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class RessourceLoader {
	
	public static File load(String s) throws FileNotFoundException{
		URL res = RessourceLoader.class.getResource(s);
		if(res == null)
			throw new FileNotFoundException(s);
		return new File(res.getFile());
	}

}

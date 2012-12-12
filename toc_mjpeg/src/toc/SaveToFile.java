package toc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class SaveToFile {
	static final Logger logger = Logger.getLogger(SaveToFile.class);
	
	public void save(List<Camera> list){
		for(Camera cam : list){
			File file = new File("cam_" + cam.getName() + ".png");
			try {
				logger.warn("Saving frame to file: " + "cam_" + cam.getName() + ".png");
				ImageIO.write(cam.getImage(), "png", file);
			} catch (IOException e) {
				logger.warn("Unable to save file from camera: " + cam.getName() + "\n\n\t\t" + e.toString());
			}
		}
	}
}

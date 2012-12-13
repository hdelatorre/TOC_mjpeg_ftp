package toc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class SaveToFile {
	static final Logger logger = Logger.getLogger(SaveToFile.class);
	
	private String format;
	private Settings settings;
	
	public SaveToFile(){
		settings = Settings.getInstance();
		format = settings.getFileFormat();
	}
	
	public void save(List<Camera> list){
		for(Camera cam : list){
			
			File dir = new File("images");
			File file = new File("images/cam_" + cam.getName() + "." + format);
			try {
				logger.warn("Saving frame to file: " + "cam_" + cam.getName() + "." + format);
				if(!dir.exists()){
					dir.mkdir();
					logger.warn("Creating folder images");
				}
				ImageIO.write(cam.getImage(), format, file);
			} catch (IOException e) {
				logger.warn("Unable to save file from camera: " + cam.getName() + "\n\n\t\t" + e.toString());
			}
		}
	}
}

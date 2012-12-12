package toc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class SaveToFile {
	static final Logger logger = Logger.getLogger(GetJpeg.class);
	
	private ArrayList<Camera> imageList;
	
	public void save(List<Camera> list){
		imageList = (ArrayList<Camera>) list;
		
		for(Camera cam : list){
			File file = new File("cam_" + cam.getName() + ".png");
			try {
				logger.warn("Saving frame to file: " + "cam_" + cam.getName() + ".png");
				ImageIO.write(cam.getImage(), "png", file);
			} catch (IOException e) {
				logger.warn(e.toString());
			}
		}
	}
}

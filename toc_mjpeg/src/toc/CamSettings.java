package toc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class CamSettings {
	
	static final Logger logger = Logger.getLogger(CamSettings.class);
	
	private String prefix = "http://";
	
	//Camera needs to be setup to stream mjpeg on stream3 
	private String suffix;
	private BufferedReader bf;
	private String file;
	private String streamNumber;
	private List<String> camList;
	private Settings settings;
	
	public CamSettings(){
		settings = Settings.getInstance();
		camList = new ArrayList<String>();
		file = settings.getCamList();
		streamNumber = settings.getStreamNumber();
		suffix = "/video" + streamNumber + ".mjpg";
		
		try{
			logger.warn("Loading a camera list file");
			
			bf = new BufferedReader(new FileReader(file));
			String line;
			while((line = bf.readLine()) != null){
				camList.add(prefix + line + suffix);
			}
			logger.warn("Loading cameras urls from a file finished");
		}catch (FileNotFoundException e) {
			
			logger.warn(e.toString());
		}catch (IOException e) {
			
			logger.warn(e.toString());
		}
	}
	
	public List<String> getCamList(){
		return camList;
	}
}

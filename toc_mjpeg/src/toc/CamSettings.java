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
	
	private String prefix;
	private String suffix;
	private BufferedReader bf;
	private String file;
	private String streamNumber;
	private List<String> camList;
	private Settings settings;
	
	
	public CamSettings(){
		
		//Setup 
		settings = Settings.getInstance();
		camList = new ArrayList<String>();
		file = settings.getCamList();
		streamNumber = settings.getStreamNumber();
		suffix = "/video" + streamNumber + ".mjpg";
		prefix = "http://";
		
		try{
			logger.info("Loading a camera list file");
			
			
			//loading list of camras from file
			bf = new BufferedReader(new FileReader(file));
			String line;
			while((line = bf.readLine()) != null){
				camList.add(prefix + line + suffix);
			}
			logger.info("Loading cameras urls from a file finished");
		}catch (FileNotFoundException e) {
			
			logger.error(e.toString());
		}catch (IOException e) {
			
			logger.error(e.toString());
		}
	}
	
	public List<String> getCamList(){
		return camList;
	}
}

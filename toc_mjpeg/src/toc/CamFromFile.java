package toc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;


public class CamFromFile {
	
	static final Logger logger = Logger.getLogger(CamFromFile.class);
	
	private String prefix = "http://";
	
	//Camera needs to be setup to stream mjpeg on stream3 
	private String suffix = "/video3.mjpg";
	private BufferedReader bf;
	private List<String> camList;
	
	public CamFromFile(String file){
		camList = new ArrayList<String>();
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
	
	public List getCamList(){
		return camList;
	}
}

package toc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

public class Settings {
	private static Settings instance = null;
	static final Logger logger = Logger.getLogger(Settings.class);
	
	private BufferedReader bf;
	private StringBuffer sb;
	private String cam_list;
	private String snapshot_time;
	private String streamNumber;
	
	protected Settings(){}
	
	public static Settings getInstance(){
		if(instance == null){
			
			instance = new Settings();
			instance.loadSettings();
			instance.closeStream();
		}
		return instance;	
	}
	
	
	public void loadSettings(){
		loadFile();
		setCamFile();
		setTime();
		setStreamNumber();
	}
	
	private void loadFile(){
		logger.warn("Loading Settings file");
		sb = new StringBuffer();
		try {
			bf = new BufferedReader(new FileReader("settings.txt"));
			String line;
			while((line = bf.readLine()) != null){
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			logger.warn(e.toString());
		}catch (IOException e) {
			logger.warn(e.toString());
		}
	}
	

	
	private void setCamFile(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("camera_list");
		int end  = strBuff.indexOf(";", start + 11);
		if(start > -1){
			String tmp = strBuff.substring(start + 11, end);
			cam_list = tmp.trim();
			logger.warn("Camera list loaded from \"" + tmp + "\"");
		}
		
		if(cam_list == null)
				logger.warn("Wrong settings file");

	}
	
	private void setTime(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("ttime");
		int end  = strBuff.indexOf(";", start + 5);
		if(start > -1){
			String tmp = strBuff.substring(start + 5, end);
			snapshot_time = tmp.trim();
			logger.warn("Snapshot delay " + tmp + "min");
		}
	}
	
	private void setStreamNumber(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("stream");
		int end  = strBuff.indexOf(";", start + 6);
		if(start > -1){
			String tmp = strBuff.substring(start + 6, end);
			streamNumber = tmp.trim();
			logger.warn("Stream number " + tmp);
		}
	}

	public String getCamList() {
		return cam_list;
	}

	public String getSnapshot_time() {
		return snapshot_time;
	}

	public String getStreamNumber() {
		return streamNumber;
	}
	
	private void closeStream(){
		try {
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}

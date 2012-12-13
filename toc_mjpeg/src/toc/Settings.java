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
	private String fileFormat;
	private String ftpServer;
	private String username;
	private String password;
	private String dir;
	
	protected Settings(){}
	
	public static Settings getInstance(){
		if(instance == null){
			
			instance = new Settings();
			instance.loadSettings();
			instance.closeStream();
		}
		return instance;	
	}
	
	
	/**
	 * 
	 */
	public void loadSettings(){
		loadFile();
		loadCamFile();
		loadTime();
		loadStreamNumber();
		loadFormat();
		loadFtpServer();
		loadUsername();
		loadPassword();
		loadDir();
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
	

	
	private void loadCamFile(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("camera_list");
		int end  = strBuff.indexOf(";", start + 11);
		if(start > -1){
			String tmp = strBuff.substring(start + 11, end);
			cam_list = tmp.trim();
			logger.warn("Camera list loaded from \"" + tmp + "\"");
		}
		
		if(cam_list == null)
				logger.warn("Wrong settings file!!!");

	}
	
	private void loadTime(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("ttime");
		int end  = strBuff.indexOf(";", start + 5);
		if(start > -1){
			String tmp = strBuff.substring(start + 5, end);
			snapshot_time = tmp.trim();
			logger.warn("Snapshot delay: " + tmp + "min");
		}
	}
	
	private void loadStreamNumber(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("stream");
		int end  = strBuff.indexOf(";", start + 6);
		if(start > -1){
			String tmp = strBuff.substring(start + 6, end);
			streamNumber = tmp.trim();
			logger.warn("Stream number: " + tmp);
		}
	}
	
	private void loadFormat(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("format");
		int end  = strBuff.indexOf(";", start + 6);
		if(start > -1){
			String tmp = strBuff.substring(start + 6, end);
			fileFormat = tmp.trim();
			logger.warn("File format: " + tmp);
		}
	}
	
	private void loadFtpServer(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("ftp_server");
		int end  = strBuff.indexOf(";", start + 10);
		if(start > -1){
			String tmp = strBuff.substring(start + 10, end);
			ftpServer = tmp.trim();
			logger.warn("Server: " + tmp);
		}
	}
	
	private void loadUsername(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("username");
		int end  = strBuff.indexOf(";", start + 8);
		if(start > -1){
			String tmp = strBuff.substring(start + 8, end);
			username = tmp.trim();
			logger.warn("Username: " + tmp);
		}
	}
	
	private void loadPassword(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("password");
		int end  = strBuff.indexOf(";", start + 8);
		if(start > -1){
			String tmp = strBuff.substring(start + 8, end);
			password = tmp.trim();
			logger.warn("Password: xxxxxxxx");
		}
	}
	
	private void loadDir(){
		StringBuffer strBuff = sb;
		int start = strBuff.lastIndexOf("dir");
		int end  = strBuff.indexOf(";", start + 3);
		if(start > -1){
			String tmp = strBuff.substring(start + 3, end);
			dir = tmp.trim();
			logger.warn("Upload images from: " + tmp);
		}
	}
	
	private void closeStream(){
		try {
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFileFormat() {
		return fileFormat;
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
	
	public String getFtpServer() {
		return ftpServer;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDir() {
		return dir;
	}
	
	
	
	
	
	
}

package toc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;


import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;



public class SendToFTP {
	
	static final Logger logger = Logger.getLogger(SendToFTP.class);
	
	private Settings settings;
	private String[] fileList;
	private FTPClient ftp;
	private File dir;
	private int reply;
	private FileInputStream in;
	
	
	public SendToFTP(){
		settings = Settings.getInstance();		
		in = null;
		getFileList();	
		initFtpClient();
	}
	
	private void initFtpClient(){
		ftp = new FTPClient();
		try {
			ftp.connect(settings.getFtpServer());
			logger.warn(ftp.getReplyString());
			ftp.login(settings.getUsername(), settings.getPassword());
			logger.warn(ftp.getReplyString());
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.setBufferSize(8000);
			//ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); 
			logger.warn(ftp.getReplyString());
			
			reply = ftp.getReplyCode();
			
			if(FTPReply.isPositiveCompletion(reply)){
				logger.warn("Connectd");
				for(String s : fileList){
					upload(s);
				}
				
			}
			else{
				logger.warn("Connection Faild");
				ftp.disconnect();
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void getFileList(){
		dir = new File("images");
		fileList = dir.list();
	}
	
	private void upload(String file){
		try {
			long start;
			//in = new FileInputStream("images/" + file);
			File f = new File("images/" + file);
			in = new FileInputStream(f);
			float kbytes = f.length()/1024;
			start = System.currentTimeMillis();
			if(ftp.storeFile(settings.getDir() + "/" + file,in)){
				float time = (System.currentTimeMillis() - start) / 1000F;
				logger.warn("File: " + file + " Uploaded || size: "+ kbytes + "kB, time: " + time + "s, speed: " + kbytes/time + "kB/s");
			}else{
				logger.warn("Save file: " + file + " Faild");
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

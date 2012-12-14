package toc;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;


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
	private float totalTime;
	private DecimalFormat df;
	
	public SendToFTP(){
		settings = Settings.getInstance();
		df = new DecimalFormat("0.##");
		in = null;
		totalTime = 0;
		getFileList();	
		initFtpClient();
	}
	
	private void initFtpClient(){
		ftp = new FTPClient();
		try {
			logger.info("Connecting to FTP Server");
			ftp.connect(settings.getFtpServer());
			logger.info(ftp.getReplyString());
			logger.info("Loging in to FTP Server as a: " + settings.getUsername());
			ftp.login(settings.getUsername(), settings.getPassword());
			logger.info(ftp.getReplyString());
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			ftp.setBufferSize(8191);
			//ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); 
			logger.info(ftp.getReplyString());
			
			reply = ftp.getReplyCode();
			
			if(FTPReply.isPositiveCompletion(reply)){
				logger.info("Connectd");
				for(String s : fileList){
					upload(s);
					
				}
				logger.info("Totale upload time: " + df.format(totalTime) + "s");
				 
			}
			else{
				logger.warn("Connection Faild.");
				logger.info("Disconnecting from FTP Server");
				ftp.disconnect();
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		}catch(UnknownHostException e){
			logger.error("Cannot fint host: " + settings.getFtpServer() + "\n" + e.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				logger.info("Loging out");
				ftp.logout();
				logger.info("Totale upload time: " + df.format(totalTime) + "s");
				logger.info("Disconnecting from FTP Server");
				ftp.disconnect();
			} catch (IOException e) {
				logger.error(e.toString());
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
				
				logger.info("File: " + file + " Uploaded || size: "+ kbytes + "kB, time: " + df.format(time) + "s, speed: " + df.format(kbytes/time) + "kB/s");
				totalTime = totalTime + time;
			}else{
				logger.error("Save file: " + file + " Faild");
			}
			in.close();
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		
		
	}
}

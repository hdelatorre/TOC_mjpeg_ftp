package toc;


import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;


public class Main {
	
	static final Logger logger = Logger.getLogger(Main.class);
	public static void main(String[] args) {
		Settings settings = Settings.getInstance();
		CamSettings cmf = new CamSettings();
		
		while(true){
			GetJpeg gj = new GetJpeg(cmf.getCamList());
			gj.connectToCameras();
			SaveToFile stf = new SaveToFile();
			stf.save(gj.getCameras());
			
			new SendToFTP();
			try {
				int time = settings.getSnapshot_time();
				logger.info("Wating " + time/60000 + "min...");
				Thread.sleep(time);
				logger.info("Start");
				
			} catch (InterruptedException e) {
				logger.error(e.toString());
			}
		}
	}

}

package toc;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Settings {
	private static Settings instance = null;
	static final Logger logger = Logger.getLogger(Settings.class);
	
	protected Settings(){}
	
	public static Settings getInstance(){
		if(instance == null){
			BasicConfigurator.configure();
			
			instance = new Settings();
			logger.warn("Loading Settings");
		}
		return instance;
	}
}

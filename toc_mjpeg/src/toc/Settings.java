package toc;

public class Settings {
	private static Settings instance = null;
	
	protected Settings(){}
	
	public static Settings getInstance(){
		if(instance == null){
			instance = new Settings();
		}
		return instance;
	}
}

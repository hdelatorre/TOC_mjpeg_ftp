package toc;


import org.apache.log4j.BasicConfigurator;


public class Main {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		CamSettings cmf = new CamSettings();
		
		while(true){
			GetJpeg gj = new GetJpeg(cmf.getCamList());
			gj.connectToCameras();
			SaveToFile stf = new SaveToFile();
			stf.save(gj.getCameras());
			
			SendToFTP ftp = new SendToFTP();
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

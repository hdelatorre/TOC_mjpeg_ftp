package toc;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import toc.mjpeg.MjpegFrame;
import toc.mjpeg.MjpegInputStream;

public class GetJpeg {
	
	static final Logger logger = Logger.getLogger(GetJpeg.class);

	private URL url;
	private BufferedImage bf;
	private MjpegFrame frame;
	private ArrayList<String> urlList;
	
	

	public GetJpeg(List<String> list){
		urlList = (ArrayList<String>)list;
		url = null;
		bf = null;
		frame = null;
		
	}
	
	public void connectToCameras(){
		
		for(String cameraUrl : urlList){
			try {
				URL url = new URL(cameraUrl);
				MjpegInputStream in = new MjpegInputStream(new BufferedInputStream(url.openStream()));
				
				/*while((frame = in.readMjpegFrame()) !=null){
					logger.warn("Reading fream from a streem " + cameraUrl);
				}*/
				
				if((frame = in.readMjpegFrame()) != null){
					logger.warn("Reading fream from a streem " + cameraUrl);
				}
				
			} catch (MalformedURLException e) {
				logger.warn("Problem connecting to the camera " + cameraUrl+ "\n" + e.toString());
			}catch (IOException e) {
				logger.warn("Problem connecting to the camera " + cameraUrl+ "\n" + e.toString());
			}
		}
		
	}
}

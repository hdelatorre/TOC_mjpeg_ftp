package toc;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import toc.mjpeg.MjpegFrame;
import toc.mjpeg.MjpegInputStream;

public class GetJpeg {
	
	static final Logger logger = Logger.getLogger(GetJpeg.class);

	private ArrayList<Camera> cameraList;
	private MjpegFrame frame;
	private ArrayList<String> urlList;
	
	

	public GetJpeg(List<String> list){
		urlList = (ArrayList<String>)list;
		frame = null;
		cameraList = new ArrayList<Camera>();
		
	}
	
	public void connectToCameras(){
		cameraList.clear();
		for(String cameraUrl : urlList){
			try {
				URL url = new URL(cameraUrl);
				MjpegInputStream in = new MjpegInputStream(new BufferedInputStream(url.openStream()));
				
				if((frame = in.readMjpegFrame()) != null){
					logger.warn("Reading frame from a camera: " + cameraUrl);
					BufferedImage image = ImageIO.read(new ByteArrayInputStream(frame.getJpegBytes()));
					String name = cameraUrl.substring(17, 20);
					cameraList.add(new Camera(name,image));
					logger.warn("Addeding camera: " + name + " to a list");
				}
				
			} catch (MalformedURLException e) {
				logger.warn("Problem connecting to the camera: " + cameraUrl+ "\n\n\t\t" + e.toString() + "\n");
			}catch (IOException e) {
				logger.warn("Problem connecting to the camera: " + cameraUrl+ "\n\n\t\t" + e.toString() + "\n");
			}
		}
		
	}
	
	public List<Camera> getCameras(){
		return cameraList;
	}
}

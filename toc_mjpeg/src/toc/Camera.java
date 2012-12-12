package toc;

import java.awt.image.BufferedImage;

public class Camera {
	private String name;
	private BufferedImage image;
	
	public Camera(String name, BufferedImage image){
		this.name = name;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}

package toc;

import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;


public class Main {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		CamFromFile cmf = new CamFromFile("camera_addr.txt");
		ArrayList<String> list = (ArrayList) cmf.getCamList();
		
		Settings.getInstance();
		for(String s : list){
			System.out.println(s);
		}
	}

}

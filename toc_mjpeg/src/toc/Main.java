package toc;

import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {
		CamFromFile cmf = new CamFromFile("camera_addr.txt");
		ArrayList<String> list = (ArrayList) cmf.getCamList();
		
		for(String s : list){
			System.out.println(s);
		}
	}

}

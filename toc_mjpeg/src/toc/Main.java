package toc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;


public class Main {
	public static void main(String[] args) {
		BasicConfigurator.configure();
		CamSettings cmf = new CamSettings();

		ArrayList<String> list = (ArrayList)cmf.getCamList();
		
		for(String s : list){
			System.out.println(s);
		}
	}

}

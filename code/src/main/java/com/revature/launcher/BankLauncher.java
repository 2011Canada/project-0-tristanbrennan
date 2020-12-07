package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.ShapeMenu;
import com.revature.repositories.ShapeDAO;
import com.revature.repositories.ShapeMemoryDAO;
import com.revature.services.ShapeService;
import com.revature.services.ShapeServiceImplementation;

public class BankLauncher {
	
	static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	public static void main(String[] args) {
		
		ShapeDAO sdao = new ShapeMemoryDAO();
		ShapeService sser = new ShapeServiceImplementation(sdao);
		ShapeMenu smen = new ShapeMenu(sser);
		
		e720Logger.info("The server has started.");
		
		while(true) { //the server is running
			
			System.out.println(smen.display());
			smen.manageUserInput();
		}
		
	}

}

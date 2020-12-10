package com.revature.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.BankMenu;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountMemoryDAO;
import com.revature.services.BankAccountService;
import com.revature.services.BankAccountServiceImplementation;

public class BankLauncher {
	
	static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
//	public static void main(String[] args) {
//		
//		ShapeDAO sdao = new ShapeMemoryDAO();
//		ShapeService sser = new ShapeServiceImplementation(sdao);
//		ShapeMenu smen = new ShapeMenu(sser);
//		
//		e720Logger.info("The server has started.");
//		
//		while(true) { //the server is running
//			
//			System.out.println(smen.display());
//			smen.manageUserInput();
//		}
//		
//	}
	
	public static void main(String[] args) {
		e720Logger.info("The server has started.");
		
		BankAccountDAO bdao = new BankAccountMemoryDAO();
		BankAccountService bad = new BankAccountServiceImplementation(bdao);
		BankMenu bmen = new BankMenu(bad);
		
		bmen.loginPrompt();
	}
}

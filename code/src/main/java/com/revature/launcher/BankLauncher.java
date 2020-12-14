package com.revature.launcher;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.menu.BankMenu;
import com.revature.models.BankAccount;
import com.revature.repositories.BankAccountDAO;
import com.revature.repositories.BankAccountPostgresDAO;
import com.revature.repositories.UserAccountDAO;
import com.revature.repositories.UserAccountPostgresDAO;
import com.revature.services.BankAccountService;
import com.revature.services.BankAccountServiceImplementation;
import com.revature.services.UserAccountService;
import com.revature.services.UserAccountServiceImplementation;

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
		
		UserAccountDAO uad = new UserAccountPostgresDAO();
		UserAccountService uas = new UserAccountServiceImplementation(uad);
		
		BankAccountDAO bad = new BankAccountPostgresDAO();
		BankAccountService bas = new BankAccountServiceImplementation(bad);
		
		
		BankMenu bmen = new BankMenu(bas,uas);
		
		//adao.saveNewLoginInfo("xgardener", "fairy");
		//System.out.println(login_info.get("tbrennan"));
		
		bmen.beginMenuLoop();
		
		
		
	}
}

package com.revature.menu;

import com.revature.services.ShapeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.models.Displayable;

public class ShapeMenu implements Displayable {
	
	ShapeService ss;
	List<Displayable> lines;
	
	Scanner userIn;
	
	public ShapeMenu(ShapeService ss) {
		this.ss = ss;
		lines = new ArrayList<Displayable>(this.ss.seeAllShapes());
		
		userIn = new Scanner(System.in);
	}
	
	public String display() {
		String result = "";
		
		for(Displayable d : lines) {
			result = result + d.display() + "\n";
		}
		
		return result;
	}
	
	public void manageUserInput() {
		String input = this.userIn.nextLine();
		//read in the entire line of text
		//this should be a number, but not necessarily
		//for now assume it's a number
		
		//int choice = Integer.parseInt(input);
		
		System.out.println(input);
	}

}

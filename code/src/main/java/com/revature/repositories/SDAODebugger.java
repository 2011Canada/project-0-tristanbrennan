package com.revature.repositories;

import java.util.List;

import com.revature.models.AbstractShape;

public class SDAODebugger {
	
	public static void main(String[] args) {
		ShapeDAO sd = new ShapeMemoryDAO();
		
		List<AbstractShape> catalogue = sd.findAll();
		
		for(AbstractShape as : catalogue) {
			System.out.println(as);
		}
	}

}

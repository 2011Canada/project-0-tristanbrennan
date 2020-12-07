package com.revature.services;

import java.util.List;

import com.revature.models.AbstractShape;
import com.revature.repositories.ShapeDAO;

public class ShapeServiceImplementation implements ShapeService {
	
	ShapeDAO sd;
	
	public ShapeServiceImplementation(ShapeDAO sd) {
		this.sd = sd;
	}

	public List<AbstractShape> seeAllShapes() {
		// TODO Auto-generated method stub
		return sd.findAll();
	}

	public void rateShape(String name, int rating) {
		// TODO Auto-generated method stub
		
	}

	public AbstractShape buyShape(AbstractShape ab) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

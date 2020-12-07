package com.revature.services;

import java.util.List;

import com.revature.models.AbstractShape;

public interface ShapeService {
	
	public List<AbstractShape> seeAllShapes();
	
	public void rateShape(String name, int rating);
	
	public AbstractShape buyShape(AbstractShape ab);

}

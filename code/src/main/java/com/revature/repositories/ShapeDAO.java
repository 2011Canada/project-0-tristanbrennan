package com.revature.repositories;

import java.util.List;

import com.revature.models.AbstractShape;

public interface ShapeDAO {
	
	public AbstractShape saveOne(AbstractShape as);
	
	public AbstractShape updateShape(AbstractShape as);
	
	public AbstractShape findByName(String name);
	
	public List<AbstractShape> findAll();

}

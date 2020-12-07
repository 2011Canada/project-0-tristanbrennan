package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;
import com.revature.models.*;

public class ShapeMemoryDAO implements ShapeDAO {
	
	static List<AbstractShape> catalogue = new ArrayList<AbstractShape>();
	
	static {
		AbstractShape[] all_bleggs = {new ShapeBlegg(1),new ShapeBlegg(2),new ShapeBlegg(1)};
		AbstractShape[] all_rubes = {new ShapeRube(1), new ShapeRube(2), new ShapeRube(2)};
		
		for(int i=0;i<3;i++) {
			catalogue.add(all_bleggs[i]);
			catalogue.add(all_rubes[i]);
		}
	}

	public AbstractShape saveOne(AbstractShape as) {
		// TODO Auto-generated method stub
		return null;
	}

	public AbstractShape updateShape(AbstractShape as) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AbstractShape> findAll() {
		return catalogue;
	}

	public AbstractShape findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

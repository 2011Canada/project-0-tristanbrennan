package com.revature.models;

public class ShapeBlegg extends AbstractShape {
	
	public ShapeBlegg(int weight) {
		super("Blue","Egg",weight);
	}

	public String display() {
		return this.toString();
	}

}

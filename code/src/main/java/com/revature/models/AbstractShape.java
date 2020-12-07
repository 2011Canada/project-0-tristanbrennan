package com.revature.models;

public abstract class AbstractShape implements Displayable {
	
	private String colour;
	private String form;
	private int weight;
	
	public AbstractShape(String colour,String form,int weight) {
		super();
		this.colour = colour;
		this.form = form;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Colour: " + getColour() + " Form: " + getForm() + " Weight: " + getWeight();
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractShape other = (AbstractShape) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	
	

}

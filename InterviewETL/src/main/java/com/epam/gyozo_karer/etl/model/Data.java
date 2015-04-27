package com.epam.gyozo_karer.etl.model;

import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;

public class Data implements Record {
	
	private List<Element> elements;
	
	public void addElement(String name, String value) {
		Element element = new Element();
		element.setName(name);
		element.setValue(value);
		
		if (this.elements == null) {
			this.elements = new LinkedList<Element>();
		}
		
		elements.add(element);
	}
	
	public Element getElement(String name) {
		Preconditions.checkNotNull(elements, "elements argument cannot be null");
		boolean haventFindElement = true;
		int counter = 0;
		Element returnValue = null;
		
		while (haventFindElement 
				&& counter < this.elements.size()) {
			if (this.elements.get(counter).getName().equals(name)) {
				returnValue = this.elements.get(counter);
				haventFindElement = false;
			}
			counter++;
		}
		
		return returnValue;
	}
	
	public Element getElement(Integer id) {
		Preconditions.checkNotNull(id, "id argument cannot be null");
		Preconditions.checkArgument(id>=0 && id<elements.size(), "id is out of range");
		return	this.elements.get(id);
	}

	
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	
	public List<Element> getElements() {
		Preconditions.checkNotNull(elements, "elements argument cannot be null");
		return this.elements;
	}

}

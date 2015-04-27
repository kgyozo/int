package com.epam.gyozo_karer.etl.transform;

import com.epam.gyozo_karer.etl.model.Element;

public class SimpleSequentialIdleProcess extends SimpleSequentialProcess {
	@Override
	public void run() {
		System.out.println("simple process");
		Element elem1 = this.sourceModel.getElement("NAME1");
		String value = elem1.getValue() + "/a";
		elem1.setValue(value);
		System.out.println(elem1.getValue());
	}

}

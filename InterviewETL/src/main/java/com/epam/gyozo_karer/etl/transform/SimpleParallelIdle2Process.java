package com.epam.gyozo_karer.etl.transform;

import com.epam.gyozo_karer.etl.model.Element;

public class SimpleParallelIdle2Process extends SimpleParallelProcess {

	@Override
	public void run() {
		System.out.println("parallel process");
		for (int i = 0; i < this.sourceModel.getElements().size(); i++) {
			Element elem1 = this.sourceModel.getElement(i);
			String value = elem1.getValue() + "/a";
			elem1.setValue(value);
			System.out.println(elem1.getValue());
		}

		this.latch.countDown();
	}

}

package com.epam.gyozo_karer.etl.transform;

import com.epam.gyozo_karer.etl.model.Data;

public abstract class SimpleSequentialProcess extends SimpleProcess {
	
	public Data processing(Data sourceModel) throws IllegalArgumentException {
		
		if (sourceModel == null) {
			throw new IllegalArgumentException();
		}
		
		this.sourceModel = sourceModel;
		
		run();
		
		return this.sourceModel;
	}



}

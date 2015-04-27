package com.epam.gyozo_karer.etl.transform;

import java.util.concurrent.CountDownLatch;

import com.epam.gyozo_karer.etl.model.Data;

public abstract class SimpleParallelProcess extends SimpleProcess {
	
	protected CountDownLatch latch;

	@Override
	public Data processing(Data sourceModel) {
		if (sourceModel == null) {
			throw new IllegalArgumentException();
		}
		
		this.sourceModel = sourceModel;
		
		new Thread(this).start();
		
		return this.sourceModel;
	}
	
	public void setCountDownLatch(CountDownLatch latch) {
		this.latch = latch;
	}

}

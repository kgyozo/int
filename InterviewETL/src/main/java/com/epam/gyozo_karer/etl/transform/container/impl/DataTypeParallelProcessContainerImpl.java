package com.epam.gyozo_karer.etl.transform.container.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.epam.gyozo_karer.etl.model.Data;
import com.epam.gyozo_karer.etl.model.Element;
import com.epam.gyozo_karer.etl.transform.Process;
import com.epam.gyozo_karer.etl.transform.SimpleParallelProcess;

/**
 * Data type: By splitting a single sequential file into smaller data files to provide parallel access.
 */

public class DataTypeParallelProcessContainerImpl extends
		ParallelProcessContainerImpl {
	
	@Override
	public Data processing(Data sourceModel) {
		int splitSize = Math.min(this.processes.size(), sourceModel.getElements().size());
		CountDownLatch latch = new CountDownLatch(splitSize);
		List<Data> splitData = new LinkedList<>();
		List<Data> resultData = new LinkedList<>();
		int splitPart = sourceModel.getElements().size() / splitSize;
		for (int i=0; i<splitSize; i++) {
			Data data = new Data();
			List<Element> subElements = 
					sourceModel.getElements().subList(i*splitPart, 
							Math.min( (i+1)*splitPart,sourceModel.getElements().size()));
			data.setElements(subElements);
			splitData.add(data);
		}
		
		for (int i=0; i<splitSize; i++) {
			Process process = this.processes.get(i);
			if (process instanceof SimpleParallelProcess) {
				SimpleParallelProcess proc = (SimpleParallelProcess) process;
				proc.setCountDownLatch(latch);
				resultData.add(process.processing(splitData.get(i)));
				
			} 
		}
		
		try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		
		return sourceModel;
	}

}

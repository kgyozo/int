package com.epam.gyozo_karer.etl.transform.container.impl;

import com.epam.gyozo_karer.etl.model.Data;

/**
 * Pipeline type: Allowing the simultaneous running of several components on the same data stream. 
 * For example: looking up a value on record 1 at the same time as adding two fields on record 2.
 */

public class PipelineTypeParallelProcessContainerImpl extends
		ParallelProcessContainerImpl {

	@Override
	public Data processing(Data sourceModel) {
		// TODO Auto-generated method stub
		return null;
	}

}

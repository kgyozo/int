package com.epam.gyozo_karer.etl.transform;

import com.epam.gyozo_karer.etl.model.Data;

public abstract class SimpleProcess implements Process, Runnable {
	
	protected Data sourceModel;
}

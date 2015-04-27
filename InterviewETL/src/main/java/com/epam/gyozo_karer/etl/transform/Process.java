package com.epam.gyozo_karer.etl.transform;

import com.epam.gyozo_karer.etl.model.Data;

public interface Process {
	public Data processing(Data sourceModel);
}

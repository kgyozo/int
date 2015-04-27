package com.epam.gyozo_karer.etl.transform.container;

import com.epam.gyozo_karer.etl.transform.Process;

public interface SequentialProcessContainer extends Process {
	public void addProcess(Process process);
}

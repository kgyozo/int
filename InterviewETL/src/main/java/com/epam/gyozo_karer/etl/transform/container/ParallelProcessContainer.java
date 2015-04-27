package com.epam.gyozo_karer.etl.transform.container;

import com.epam.gyozo_karer.etl.transform.Process;

public interface ParallelProcessContainer extends Process {
	public void addProcess(Process process);
}

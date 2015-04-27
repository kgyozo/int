package com.epam.gyozo_karer.etl.transform.container.impl;

import java.util.LinkedList;
import java.util.List;

import com.epam.gyozo_karer.etl.transform.Process;
import com.epam.gyozo_karer.etl.transform.container.ParallelProcessContainer;
import com.google.common.base.Preconditions;

public abstract class ParallelProcessContainerImpl implements
		ParallelProcessContainer {
	
	protected List<Process> processes = new LinkedList<>();
	
	public List<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Process> processes) {
		Preconditions.checkNotNull(processes);
		this.processes = processes;
	}

	public void addProcess(Process process) {
		Preconditions.checkNotNull(processes);
		processes.add(process);
	}
}

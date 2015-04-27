package com.epam.gyozo_karer.etl.transform.container.impl;

import java.util.LinkedList;
import java.util.List;

import com.epam.gyozo_karer.etl.model.Data;
import com.epam.gyozo_karer.etl.model.Element;
import com.epam.gyozo_karer.etl.transform.Process;
import com.epam.gyozo_karer.etl.transform.container.SequentialProcessContainer;

public class SequentialProcessContainerImpl implements
		SequentialProcessContainer {

	private List<Process> processes = new LinkedList<>();
	
	public Data processing(Data sourceModel) {
		Data usedModel = sourceModel;
		
		for (Process process : processes) {
			usedModel = process.processing(usedModel);
		}
		return usedModel;
	}

	public List<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}

	@Override
	public void addProcess(Process process) {
		processes.add(process);
	}
	
 


}

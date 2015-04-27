package com.epam.gyozo_karer.etl.extract;

public interface Extract {
	public void read();
	public void setSource(String source);
}

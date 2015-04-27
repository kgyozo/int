package com.epam.gyozo_karer.etl.transform;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.epam.gyozo_karer.etl.model.Data;
import com.epam.gyozo_karer.etl.model.Element;

public class SimpleProcessTest {
	
	private Process process;

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		process = new SimpleSequentialIdleProcess();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialValueIsNull() {
		Data basicValue = null;
		boolean wasExceptionThrown = false;
		
		try{
			Data returnValue = process.processing(basicValue);	
		} catch (IllegalArgumentException iae) {
			wasExceptionThrown = true;
		}
		
		assertTrue(wasExceptionThrown);
		
	}

}

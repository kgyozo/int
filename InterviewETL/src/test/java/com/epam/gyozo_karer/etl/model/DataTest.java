package com.epam.gyozo_karer.etl.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DataTest {

	private Data records;
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		records = new Data();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getElementFromUninitializedData() {
		thrown.expect(NullPointerException.class);
		Element element = records.getElement("NAME");
	}

	@Test
	public void getAllElementsFromUninitializedData() {
		boolean wasExceptionThrown = false;

		try {
			records.getElements();
		} catch (NullPointerException npe) {
			wasExceptionThrown = true;
		}

		assertTrue(wasExceptionThrown);
	}

	@Test
	public void addElementToUninitializedData() {
		records.addElement("NAME", "value name");
		int size =	records.getElements().size();
		assertEquals(1, size);
	}

	@Test
	public void addElementToData() {
		records.addElement("NAME", "value name 1");
		int initialSize = records.getElements().size();
		int finalSize;

		records.addElement("NAME", "value name 2");
		records.getElements();

		finalSize = this.records.getElements().size();

		assertEquals(1, initialSize);
		assertEquals(2, finalSize);
	}

	@Test
	public void getTheAddedElement() {
		records.addElement("NAME", "value name");
		Element element = records.getElement("NAME");

		assertEquals("NAME", element.getName());
		assertEquals("value name", element.getValue());
	}

	@Test
	public void getAnUnaddedElement() {
		records.addElement("NAME", "value name");
		Element element = records.getElement("NAME1");

		assertNull(element);
	}

	@Test
	public void getSecondElement() {
		records.addElement("NAME1", "value name 1");
		records.addElement("NAME2", "value name 2");
		Element element = records.getElement(1);

		assertEquals("NAME2", element.getName());
		assertEquals("value name 2", element.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getOutOfRangeDataAbove() {
		records.addElement("NAME1", "value name 1");
		records.addElement("NAME2", "value name 2");
		records.getElement(3);
	}

	@Test
	public void getOutOfRangeDataBelow() {
		records.addElement("NAME1", "value name 1");
		records.addElement("NAME2", "value name 2");
		boolean wasExceptionThrown = false;

		try {
			records.getElement(-2);
		} catch (IllegalArgumentException iae) {
			wasExceptionThrown = true;
		}

		assertTrue(wasExceptionThrown);
	}

}

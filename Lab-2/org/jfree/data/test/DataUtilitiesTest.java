package org.jfree.data.test;

import junit.framework.TestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.*;

import java.lang.Number;

public class DataUtilitiesTest {
	
	private Values2D values2D;
	private double array[] = new double[1];
	private double array2D[][] = new double[1][1];
	private KeyedValues keyedValues;
	private Number number[] = new Number[1];
	private Number number2D[][] = new Number[1][1];
	
	@Before
	public void setUp() {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);
		testValues.addValue(8, 2, 0);
		testValues.addValue(10, 3, 0);
		testValues.addValue(2, 0, 1);
		testValues.addValue(5, 1, 1);
		testValues.addValue(9, 2, 1);
		testValues.addValue(11, 3, 1);
		testValues.addValue(3, 0, 2);
		testValues.addValue(6, 1, 2);
		testValues.addValue(10, 2, 2);
		testValues.addValue(12, 3, 2);
		testValues.addValue(4, 0, 3);
		testValues.addValue(7, 1, 3);
		testValues.addValue(11, 2, 3);
		testValues.addValue(13, 3, 3);
		testValues.addValue(5, 0, 4);
		testValues.addValue(8, 1, 4);
		testValues.addValue(12, 2, 4);
		testValues.addValue(14, 3, 4);
		array[0] = 1;
		number[0] = 1;
		array2D[0][0] = 1;
		number2D[0][0] = 1;
		DefaultKeyedValues testVals = new DefaultKeyedValues();
		keyedValues = testVals;
		testVals.addValue("0", 5/16);
		testVals.addValue("1", (5+9)/16);
		testVals.addValue("2", (2+5+9)/16);
	}
	
	@After
	public void tearDown() {
		values2D = null;
		array = null;
		number = null;
		array2D = null;
		number2D = null;
		keyedValues = null;
	}
	
	@Test
	public void testColumnLessThanZero() {
		try {
			assertEquals("Wrong output returned, should be 0", 0, DataUtilities.calculateColumnTotal(values2D, -1), 0.0000001d);
			
		} catch (Exception ex) {
			fail("Method supposed to return zero");
		}
	}
	
	@Test
	public void testDataIsNullColumn() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception ex) {
			assertTrue("Incorrect exception type thrown", ex.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testValidDataColumnZero() {
		assertEquals("Wrong sum returned, should be 23", 23, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testValidDataColumnNormalValue() {
		assertEquals("Wrong sum returned, should be 31", 31, DataUtilities.calculateColumnTotal(values2D, 2), 0.0000001d);
	}
	
	@Test
	public void testValidDataColumnIsTotalColumnsMinusOne() {
		assertEquals("Wrong sum returned, should be 39", 39, DataUtilities.calculateColumnTotal(values2D, 4), 0.0000001d);
	}
	
	@Test
	public void testValidDataColumnIsTotalColumns() {
		try {
			assertEquals("Wrong output returned, should be 0", 0, DataUtilities.calculateColumnTotal(values2D, 5), 0.0000001d);
			
		} catch (Exception ex) {
			fail("Method supposed to return zero");
		}
	}
	@Test
	public void testRowLessThanZero() {
		try {
			assertEquals("Wrong output returned, should be 0", 0, DataUtilities.calculateRowTotal(values2D, -1), 0.0000001d);
			
		} catch (Exception ex) {
			fail("Method supposed to return zero");
		}
	}
	
	@Test
	public void testDataIsNullRow() {
		try {
			DataUtilities.calculateRowTotal(null, 0);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception ex) {
			assertTrue("Incorrect exception type thrown", ex.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testValidDataRowZero() {
		assertEquals("Wrong sum returned, should be 15", 15, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testValidDataRowNormalValue() {
		assertEquals("Wrong sum returned, should be 50", 50, DataUtilities.calculateRowTotal(values2D, 2), 0.0000001d);
	}
	
	@Test
	public void testValidDataRowIsTotalRowsMinusOne() {
		assertEquals("Wrong sum returned, should be 60", 60, DataUtilities.calculateRowTotal(values2D, 3), 0.0000001d);
	}
	
	@Test
	public void testValidDataRowIsTotalRow() {
		try {
			assertEquals("Wrong output returned, should be 0", 0, DataUtilities.calculateRowTotal(values2D, 4), 0.0000001d);
			
		} catch (Exception ex) {
			fail("Method supposed to return zero");
		}
	}

	@Test
	public void testDataIsNullNumberArray() {
		try {
			DataUtilities.createNumberArray(null);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception ex) {
			assertTrue("Incorrect exception type thrown", ex.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testDataIsNotNullNumberArray() {
		Number[] testNum = DataUtilities.createNumberArray(array);
		assertEquals("Failed to create correct Number array", number[0], testNum[0]);
	}
	
	@Test
	public void testDataIsNullNumberArray2D() {
		try {
			DataUtilities.createNumberArray2D(null);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception ex) {
			assertTrue("Incorrect exception type thrown", ex.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testDataIsNotNullNumberArray2D() {
		Number[][] testNum2D = DataUtilities.createNumberArray2D(array2D);
		assertEquals("Failed to create correct Number array", number2D[0][0], testNum2D[0][0]);
	}
	
	@Test
	public void testDataIsNullCumulativePercentages() {
		try {
			DataUtilities.getCumulativePercentages(null);
			
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception ex) {
			assertTrue("Incorrect exception type thrown", ex.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testDataIsNotNullCumulativePercentages() {
		KeyedValues keyVals = DataUtilities.getCumulativePercentages(keyedValues);
		assertEquals("Failed to create correct KeyedValues object", keyedValues.getValue("0"), keyVals.getValue("0"));
	}
}

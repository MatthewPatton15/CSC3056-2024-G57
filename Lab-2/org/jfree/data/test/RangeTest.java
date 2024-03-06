package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(0, 10);
    }

    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }

    // Tests for constrain method
    @Test
    public void testConstrain_ValueAtLowerBoundary() {
        double result = rangeObjectUnderTest.constrain(0);
        assertEquals("Value at lower boundary should be equal to lower boundary", 0, result, 0.00001d);
    }

    @Test
    public void testConstrain_ValueJustAboveLowerBoundary() {
        double result = rangeObjectUnderTest.constrain(0.001);
        assertEquals("Value just above the lower boundary should return the actual value", 0.001, result, 0.00001d);
    }

    @Test
    public void testConstrain_ValueInTheMiddle() {
        double result = rangeObjectUnderTest.constrain(5);
        assertEquals("Value in the middle of the range should be equal to itself", 5, result, 0.00001d);
    }

    @Test
    public void testConstrain_ValueAtUpperBoundary() {
        double result = rangeObjectUnderTest.constrain(10);
        assertEquals("Value at upper boundary should be equal to upper boundary", 10, result, 0.00001d);
    }

    @Test
    public void testConstrain_ValueJustBelowUpperBoundary() {
        double result = rangeObjectUnderTest.constrain(9.999);
        assertEquals("Value just below the upper boundary should return the actual value", 9.999, result, 0.00001d);
    }

    @Test
    public void testConstrain_ExtremeValueAboveRange() {
        double result = rangeObjectUnderTest.constrain(Double.MAX_VALUE);
        assertEquals("Extreme value above range should return upper boundary", 10, result, 0.00001d);
    }

    @Test
    public void testConstrain_ExtremeValueBelowRange() {
        double result = rangeObjectUnderTest.constrain(-Double.MAX_VALUE);
        assertEquals("Extreme value below range should return lower boundary", 0, result, 0.00001d);
    }

    // Tests for expandToInclude method
    @Test
    public void testExpandToInclude_NullRange_Creation() {
        Range result = Range.expandToInclude(null, 5);
        assertNotNull("Expanding to include a value in a null range should create a new Range object", result);
    }

    @Test
    public void testExpandToInclude_NullRange_LowerBound() {
        Range result = Range.expandToInclude(null, 5);
        assertEquals("Expanding a null range to include a new value should set the lower bound to the new value", 5, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_NullRange_UpperBound() {
        Range result = Range.expandToInclude(null, 5);
        assertEquals("Expanding a null range to include a new value should set the upper bound to the new value", 5, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueBelowLowerBoundary() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, -1);
        assertEquals("Lower boundary should be updated to include new value", -1, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueAboveUpperBoundary() {
        Range result = Range.expandToInclude(rangeObjectUnderTest, 11);
        assertEquals("Upper boundary should be updated to include new value", 11, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueWithinRange_LowerBound() {
        Range existingRange = new Range(0, 10);
        Range result = Range.expandToInclude(existingRange, 5);
        assertEquals("Expanding to include a value within the range should not change the lower bound", 0, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueWithinRange_UpperBound() {
        Range existingRange = new Range(0, 10);
        Range result = Range.expandToInclude(existingRange, 5);
        assertEquals("Expanding to include a value within the range should not change the upper bound", 10, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueOutsideRange_LowerBound() {
        Range existingRange = new Range(0, 10);
        Range result = Range.expandToInclude(existingRange, 15);
        assertEquals("Expanding to include a value outside the range should not change the lower bound", 0, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testExpandToInclude_ValueOutsideRange_UpperBound() {
        Range existingRange = new Range(0, 10);
        Range result = Range.expandToInclude(existingRange, 15);
        assertEquals("Expanding to include a value outside the range should update the upper bound", 15, result.getUpperBound(), 0.00001d);
    }

    // Test for getLength method
    @Test
    public void testGetLength() {
        double length = rangeObjectUnderTest.getLength();
        assertEquals("Length of the range should be 10", 10, length, 0.00001d);
    }

    // Tests for the shift method
    @Test
    public void testShiftWithPositiveDelta_LowerBound() {
        Range result = Range.shift(rangeObjectUnderTest, 1, false);
        assertEquals("Range should be shifted to the right by 1, lower bound check", 1, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testShiftWithPositiveDelta_UpperBound() {
        Range result = Range.shift(rangeObjectUnderTest, 1, false);
        assertEquals("Range should be shifted to the right by 1, upper bound check", 11, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testShiftWithNegativeDelta_LowerBound() {
        Range result = Range.shift(rangeObjectUnderTest, -1, false);
        assertEquals("Range should be shifted to the left by 1, lower bound check", -1, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testShiftWithNegativeDelta_UpperBound() {
        Range result = Range.shift(rangeObjectUnderTest, -1, false);
        assertEquals("Range should be shifted to the left by 1, upper bound check", 9, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testShiftWithZeroDelta_LowerBound() {
        Range result = Range.shift(rangeObjectUnderTest, 0, false);
        assertEquals("Shifting range by zero delta should not change the lower bound", 0, result.getLowerBound(), 0.00001d);
    }

    @Test
    public void testShiftWithZeroDelta_UpperBound() {
        Range result = Range.shift(rangeObjectUnderTest, 0, false);
        assertEquals("Shifting range by zero delta should not change the upper bound", 10, result.getUpperBound(), 0.00001d);
    }

    @Test
    public void testShift_NullBaseRange() {
        try {
            Range.shift(null, 1.0);
            fail("Expected an exception to be thrown when shifting a null base range");
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            // Check for the specific exception type if needed
            assertTrue("Expected IllegalArgumentException to be thrown", e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testShift_LargeNegativeDelta() {
        Range result = Range.shift(rangeObjectUnderTest, -20, true);
        assertTrue("Shifting with large negative delta should move range into negative territory", result.getLowerBound() < 0);
    }

    // Tests for the toString method
    @Test
    public void testToString() {
        String toStringResult = rangeObjectUnderTest.toString();
        assertEquals("String representation of the range should match", "Range[0.0,10.0]", toStringResult);
    }

    @Test
    public void testToString_AfterShifting() {
        Range shiftedRange = Range.shift(rangeObjectUnderTest, 2, false);
        String toStringResult = shiftedRange.toString();
        assertEquals("String representation of the range after shifting should match", "Range[2.0,12.0]", toStringResult);
    }

    @Test
    public void testToString_AfterExpanding() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 11);
        String toStringResult = expandedRange.toString();
        assertEquals("String representation of the range after expanding should match", "Range[0.0,11.0]", toStringResult);
    }

    @Test
    public void testPrecision_AfterOperations() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 10.0000001);
        Range shiftedRange = Range.shift(expandedRange, 0.0000001, false);
        assertEquals("After expanding and shifting, the upper bound should maintain precision", 10.0000002, shiftedRange.getUpperBound(), 0.0000001d);
    }

    @Test
    public void testToString_AfterPrecisionOperations() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 10.0000001);
        Range shiftedRange = Range.shift(expandedRange, 0.0000001, false);
        String toStringResult = shiftedRange.toString();
        assertTrue("String representation should reflect precision operations", toStringResult.contains("10.0000002"));
    }

    @Test
    public void testConstructor_InvalidInputs() {
        try {
            new Range(5, 1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Exception message should indicate the problem", "Lower bound must be less than or equal to upper bound", e.getMessage());
        }
    }

    // Test for serialisation and deserialisation of lower bound
    @Test
    public void testSerialisation_LowerBound() throws IOException, ClassNotFoundException {
        Range originalRange = new Range(0, 5);

        // Serialise the original Range object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(originalRange);
        out.flush();
        byte[] serialisedData = bos.toByteArray();

        // Deserialise the byte array back into a Range object
        ByteArrayInputStream bis = new ByteArrayInputStream(serialisedData);
        ObjectInputStream in = new ObjectInputStream(bis);
        Range deserialisedRange = (Range) in.readObject();

        // Verify the lower bound is the same after serialisation and deserialisation
        assertEquals("The lower bound should be the same after serialisation and deserialisation",
                originalRange.getLowerBound(), deserialisedRange.getLowerBound(), 0.00001d);
    }

    // Test for serialisation and deserialisation of upper bound
    @Test
    public void testSerialisation_UpperBound() throws IOException, ClassNotFoundException {
        Range originalRange = new Range(5, 10);

        // Serialise the original Range object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(originalRange);
        out.flush();
        byte[] serialisedData = bos.toByteArray();

        // Deserialise the byte array back into a Range object
        ByteArrayInputStream bis = new ByteArrayInputStream(serialisedData);
        ObjectInputStream in = new ObjectInputStream(bis);
        Range deserialisedRange = (Range) in.readObject();

        // Verify the upper bound is the same after serialisation and deserialisation
        assertEquals("The upper bound should be the same after serialisation and deserialisation",
                originalRange.getUpperBound(), deserialisedRange.getUpperBound(), 0.00001d);
    }
}

package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
    
    // NEW
    @Test
    public void testConstrain_NaNValue() {
        double result = rangeObjectUnderTest.constrain(Double.NaN);
        assertTrue("Constraining NaN should return NaN", Double.isNaN(result));
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
    
    // NEW
    @Test
    public void testShift_RangeCrossingZero() {
        Range base = new Range(-5, 5);
        double delta = 10;
        Range expected = new Range(0, 15); // Lower bound does not cross zero
        Range result = Range.shift(base, delta);
        assertEquals("Shifting a range crossing zero should set the lower bound to zero", expected, result);
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
    
    // Tests for the combine method
    @Test
    public void testCombine_WithNonNullRanges() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(6, 10);
        Range expected = new Range(1, 10);
        Range result = Range.combine(range1, range2);
        assertEquals("Combining two non-null ranges should result in a range spanning both", expected, result);
    }
    
    @Test
    public void testCombine_WithNonNullRangesReverseOrder() {
        Range range1 = new Range(6, 10);
        Range range2 = new Range(1, 5);
        Range expected = new Range(1, 10);
        Range result = Range.combine(range1, range2);
        assertEquals("Combining two non-null ranges should result in a range spanning both", expected, result);
    }

    @Test
    public void testCombine_WithFirstRangeNullAndSecondNonNull() {
        Range range1 = null;
        Range range2 = new Range(6, 10);
        Range result = Range.combine(range1, range2);
        assertEquals("Combining a null first range with a non-null second range should return the non-null second range", range2, result);
    }

    @Test
    public void testCombine_WithFirstRangeNonNullAndSecondNull() {
        Range range1 = new Range(5, 9);
        Range range2 = null;
        Range result = Range.combine(range1, range2);
        assertEquals("Combining a non-null first range with a null second range should return the non-null first range", range1, result);
    }

    // Tests for the intersects method
    @Test
    public void testIntersects_WithNonOverlappingRange() {
    	assertFalse("Should return false when ranges do not overlap", rangeObjectUnderTest.intersects(11, 15));
    }
    
    @Test
    public void testIntersects_LowerAndUpperBothLessThanRangeLower() {
        assertFalse("Should return false when both lower and upper are less than range's lower", rangeObjectUnderTest.intersects(-2, -1));
    }

    @Test
    public void testIntersects_LowerLessThanRangeLowerAndUpperInsideRange() {
        assertTrue("Should return true when lower is less than range's lower and upper is inside the range", rangeObjectUnderTest.intersects(-1, 1));
    }

    @Test
    public void testIntersects_LowerAndUpperBothInsideRange() {
        assertTrue("Should return true when both lower and upper are inside the range", rangeObjectUnderTest.intersects(1, 9));
    }

    @Test
    public void testIntersects_LowerEqualToRangeUpperAndUpperBeyondRangeUpper() {
        assertFalse("Should return false when lower is equal to range's upper and upper is beyond range's upper", rangeObjectUnderTest.intersects(10, 11));
    }

    @Test
    public void testIntersects_LowerGreaterThanRangeUpper() {
        assertFalse("Should return false when lower is greater than range's upper", rangeObjectUnderTest.intersects(11, 12));
    }
    
    @Test
    public void whenUpperIsWithinRange_shouldIntersect() {
        // `upper` is 9, which is within the range of (0,10) and also >= `lower` which is 5.
        assertTrue("Should return true when upper is within the range and greater than lower",
                   rangeObjectUnderTest.intersects(5, 9));
    }

    @Test
    public void whenUpperIsEqualToThisUpper_shouldNotIntersect() {
        // `upper` is 10, which is equal to `this.upper` and should return false for the given condition.
        assertFalse("Should return false when upper is equal to this.upper",
                    rangeObjectUnderTest.intersects(5, 10));
    }

    @Test
    public void whenUpperIsBeyondThisUpper_shouldNotIntersect() {
        // `upper` is 11, which is beyond the range of (0,10) and should return false.
        assertFalse("Should return false when upper is beyond this.upper",
                    rangeObjectUnderTest.intersects(5, 11));
    }

    @Test
    public void whenUpperIsLessThanLower_shouldNotIntersect() {
        // `upper` is 4, which is less than `lower` which is 5, this is logically incorrect and should return false.
        assertFalse("Should return false when upper is less than lower",
                    rangeObjectUnderTest.intersects(5, 4));
    }
    
    // Tests for getCentralValue method
    @Test
    public void testGetCentralValue_PositiveRange() {
        Range range = new Range(10, 20);
        assertEquals("The central value of a positive range should be correct", 15.0, range.getCentralValue(), 0.00001d);
    }

    @Test
    public void testGetCentralValue_NegativeRange() {
        Range range = new Range(-20, -10);
        assertEquals("The central value of a negative range should be correct", -15.0, range.getCentralValue(), 0.00001d);
    }

    @Test
    public void testGetCentralValue_SpanningZero() {
        Range range = new Range(-5, 5);
        assertEquals("The central value of a range spanning zero should be zero", 0.0, range.getCentralValue(), 0.00001d);
    }

    @Test
    public void testGetCentralValue_LargeNumbers() {
        Range range = new Range(10000000, 10000020);
        assertEquals("The central value with large numbers should be correct", 10000010.0, range.getCentralValue(), 0.00001d);
    }

    @Test
    public void testGetCentralValue_SameBounds() {
        Range range = new Range(5, 5);
        assertEquals("The central value when both bounds are the same should be equal to the bound", 5.0, range.getCentralValue(), 0.00001d);
    }
    
    // Tests for expand method
    @Test
    public void testExpand_ZeroMargins() {
        Range range = new Range(2, 6);
        Range result = Range.expand(range, 0.0, 0.0);
        assertEquals("Expansion with zero margins should return the same range", range, result);
    }

    @Test
    public void testExpand_PositiveMargins() {
        Range range = new Range(2, 6);
        Range result = Range.expand(range, 0.25, 0.5);
        Range expected = new Range(1, 8);
        assertEquals("Expansion with positive margins should return the correct range", expected, result);
    }

    @Test
    public void testExpand_UnequalMargins() {
        Range range = new Range(10, 30);
        Range result = Range.expand(range, 0.2, 0.4);
        Range expected = new Range(6, 38);
        assertEquals("Expansion with unequal margins should return the correct range", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NegativeMargins() {
        Range range = new Range(5, 15);
        Range.expand(range, -0.1, -0.1);
    }

    @Test
    public void testExpand_MarginsExceeding100Percent() {
        Range range = new Range(0, 50);
        Range result = Range.expand(range, 2, 2);
        Range expected = new Range(-100, 150);
        assertEquals("Expansion with margins exceeding 100% should return the correct range", expected, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testExpand_NullRange() {
        Range.expand(null, 0.1, 0.1);
    }
    
    // Tests for hashCode method
    @Test
    public void testHashCode_Consistency() {
        Range range = new Range(1, 5);
        int expectedHash = range.hashCode();
        assertEquals("Hash code should be consistent", expectedHash, range.hashCode());
        assertEquals("Hash code should be consistent on subsequent calls", expectedHash, range.hashCode());
    }

    @Test
    public void testHashCode_Equality() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 5);
        assertEquals("Equal objects must have equal hash codes", range1.hashCode(), range2.hashCode());
    }

    @Test
    public void testHashCode_Inequality() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(2, 6);
        assertNotEquals("It's desirable for unequal objects to have different hash codes", range1.hashCode(), range2.hashCode());
    }
    
    // Tests for equals method
    @Test
    public void testEquals_NotInstanceOfRange() {
        Range range = new Range(1, 5);
        String notARange = "Not a Range";
        assertFalse("Should return false because the object is not a Range", range.equals(notARange));
    }

    @Test
    public void testEquals_DifferentLowerBound() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(2, 5);
        assertFalse("Should return false because the lower bounds are different", range1.equals(range2));
    }

    @Test
    public void testEquals_DifferentUpperBound() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 6);
        assertFalse("Should return false because the upper bounds are different", range1.equals(range2));
    }

    @Test
    public void testEquals_SameBounds() {
        Range range1 = new Range(1, 5);
        Range range2 = new Range(1, 5);
        assertTrue("Should return true because both bounds are the same", range1.equals(range2));
    }

}

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ArrOperationTest {

    //=========== Test Cases for Max Mirror function ============
    @Test
    public void maxMirrorPositiveTestCases() {
        ArrOperation obj = new ArrOperation();
        assertEquals(3, obj.maxMirror(new int[]{1, 2, 3, 8, 9, 3, 2, 1}));
        assertEquals(2, obj.maxMirror(new int[]{7, 1, 4, 9, 7, 4, 1}));
        assertEquals(3, obj.maxMirror(new int[]{1, 2, 1, 4}));
        assertEquals(7, obj.maxMirror(new int[]{1, 4, 5, 3, 5, 4, 1}));
    }
    
    @Test
    public void maxMirrorNegativeTestCases(){
        ArrOperation obj = new ArrOperation();
        AssertionError error = assertThrows(AssertionError.class, () -> obj.maxMirror(new int[]{}));
        assertEquals("Array is empty.", error.getMessage());
    }

    //=========== Test Cases for Count Clumps function ============
    @Test
    public void countClumpsPositiveTestCases(){
        ArrOperation obj = new ArrOperation();
        assertEquals(2, obj.countClumps(new int[]{1, 2, 2, 3, 4, 4}));
        assertEquals(2, obj.countClumps(new int[]{1, 1, 2, 1, 1}));
        assertEquals(1, obj.countClumps(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void countClumpsNegativeTestCases(){
        ArrOperation obj = new ArrOperation();
        AssertionError error = assertThrows(AssertionError.class, () -> obj.countClumps(new int[]{}));
        assertEquals("Array is empty.", error.getMessage());
    }

    //=========== Test Cases for Split Array function ============
    @Test
    public void splitArrayPositiveTestCases(){
        ArrOperation obj = new ArrOperation();
        assertEquals(3, obj.splitArray(new int[]{1, 1, 1, 2, 1}));
        assertEquals(-1, obj.splitArray(new int[]{2, 1, 1, 2, 1}));
        assertEquals(1, obj.splitArray(new int[]{10, 10}));
    }

    @Test
    public void splitArrayNegativeTestCases(){
        ArrOperation obj = new ArrOperation();
        AssertionError error = assertThrows(AssertionError.class, () -> obj.splitArray(new int[]{}));
        assertEquals("Array is empty.", error.getMessage());
    }
    
    //=========== Test Cases for Fit XY function ============
    @Test
    void fitXYPositiveTestCases(){
        ArrOperation obj = new ArrOperation();
        int X = 4, Y = 5;
        assertArrayEquals(new int[]{9, 4, 5, 4, 5, 9}, obj.fixXY(new int[]{5, 4, 9, 4, 9, 5}, X, Y));
        assertArrayEquals(new int[]{1, 4, 5, 1}, obj.fixXY(new int[]{1, 4, 1, 5}, X, Y));
        assertArrayEquals(new int[]{1, 4, 5, 1, 1, 4, 5}, obj.fixXY(new int[]{1, 4, 1, 5, 5, 4, 1}, X, Y));
    }

    @Test
    public void fitXYNegativeTestCases(){
        ArrOperation obj = new ArrOperation();
        int X = 4, Y = 5;
        assertEquals("Array is empty.", assertThrows(AssertionError.class, () -> obj.fixXY(new int[]{}, X, Y)).getMessage());
        assertEquals("Count of both X and Y is unequal.", 
                    assertThrows(AssertionError.class, () -> obj.fixXY(new int[]{1, 4, 9, 5, 5}, X, Y)).getMessage());
        assertEquals("Multiple values of X occured adjacently.",
                    assertThrows(AssertionError.class, () -> obj.fixXY(new int[]{1, 4, 4, 5, 5}, X, Y)).getMessage());
        assertEquals("Found value of X at last index.",
                    assertThrows(AssertionError.class, () -> obj.fixXY(new int[]{1, 5, 9, 4}, X, Y)).getMessage());
    }
}

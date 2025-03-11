
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ArrOperationTest {

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
        AssertionError error = assertThrows(java.lang.Error, obj.maxMirror(new int[]{}));
        assertEquals("Array is empty.", error.getMessage());
    }

    @Test
    public void countClumpsPositiveTestCases(){
        ArrOperation obj = new ArrOperation();
        assertEquals(2, obj.countClumps(new int[]{1, 2, 2, 3, 4, 4}));
        assertEquals(2, obj.countClumps(new int[]{1, 1, 2, 1, 1}));
        assertEquals(1, obj.countClumps(new int[]{1, 1, 1, 1, 1}));
    }

    // @Test
    // public void countClumpsNegativeTestCases(){
    //     ArrOperation obj = new ArrOperation();
    //     assertEquals(2, obj.countClumps(new int[]{1, 2, 2, 3, 4, 4}));
    //     assertEquals(2, obj.countClumps(new int[]{1, 1, 2, 1, 1}));
    //     assertEquals(2, obj.countClumps(new int[]{1, 1, 1, 1, 1}));
    // }
}

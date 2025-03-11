import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SearchTest {

    @Test
    public void linearSearchTest(){
        Search obj = new Search();
        assertEquals(1, obj.linearSearch(new double[]{54, 24, 18}, 24, 0));
        assertEquals(-1, obj.linearSearch(new double[]{17, 13, 19}, 24, 0));
        assertEquals(0, obj.linearSearch(new double[]{8, 12, 10}, 8, 0));
        assertEquals(2, obj.linearSearch(new double[]{10, 15, 25}, 25, 0));
    }

    @Test
    public void binarySearchTest() {
        Search obj = new Search();
        int size = 2;
        assertEquals(1, obj.binarySearch(new double[]{54, 24, 18}, 24, 0,size));
        assertEquals(-1, obj.binarySearch(new double[]{17, 13, 19}, 24, 0, size));
        assertEquals(0, obj.binarySearch(new double[]{8, 12, 10}, 8, 0, size));
        assertEquals(2, obj.binarySearch(new double[]{10, 15, 25}, 25, 0, size));
    }
}

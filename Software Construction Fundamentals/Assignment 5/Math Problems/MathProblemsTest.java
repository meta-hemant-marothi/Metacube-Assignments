import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MathProblemsTest {

    @Test
    public void testHcfOfMultipleNumbers() {
        assertEquals(6, MathProblems.hcfOfMultipleNumbers(new int[]{54, 24, 18}, 0));
        assertEquals(1, MathProblems.hcfOfMultipleNumbers(new int[]{17, 13, 19}, 0));
        assertEquals(2, MathProblems.hcfOfMultipleNumbers(new int[]{8, 12, 10}, 0));
        assertEquals(5, MathProblems.hcfOfMultipleNumbers(new int[]{10, 15, 25}, 0));
    }

    @Test
    public void testLcmMultiple() {
        assertEquals(216, MathProblems.lcmOfMultipleNumbers(new int[]{54, 24, 18}, 0));
        assertEquals(4199, MathProblems.lcmOfMultipleNumbers(new int[]{17, 13, 19}, 0));
        assertEquals(120, MathProblems.lcmOfMultipleNumbers(new int[]{8, 12, 10}, 0));
        assertEquals(150, MathProblems.lcmOfMultipleNumbers(new int[]{10, 15, 25}, 0));
    }
}

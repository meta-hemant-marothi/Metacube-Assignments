import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SparseMatrixTest {

    @Test
    void testTranspose() {
        Map<SparseMatrix.Pair<Integer, Integer>, Double> values = new HashMap<>();
        values.put(new SparseMatrix.Pair<>(0, 1), 5.0);
        values.put(new SparseMatrix.Pair<>(1, 0), 3.0);
        SparseMatrix matrix = new SparseMatrix(2, 2, values);

        SparseMatrix transposed = matrix.transpose();

        assertEquals(5.0, transposed.getValues().get(new SparseMatrix.Pair<>(1, 0)));
        assertEquals(3.0, transposed.getValues().get(new SparseMatrix.Pair<>(0, 1)));
    }

    @Test
    void testSymmetric() {
        Map<SparseMatrix.Pair<Integer, Integer>, Double> values = new HashMap<>();
        values.put(new SparseMatrix.Pair<>(0, 1), 2.0);
        values.put(new SparseMatrix.Pair<>(1, 0), 2.0);
        SparseMatrix matrix = new SparseMatrix(2, 2, values);

        assertTrue(matrix.isSymmetric());
    }

    @Test
    void testAddition() {
        Map<SparseMatrix.Pair<Integer, Integer>, Double> valuesA = new HashMap<>();
        valuesA.put(new SparseMatrix.Pair<>(0, 1), 2.0);

        Map<SparseMatrix.Pair<Integer, Integer>, Double> valuesB = new HashMap<>();
        valuesB.put(new SparseMatrix.Pair<>(0, 1), 3.0);

        SparseMatrix matrixA = new SparseMatrix(2, 2, valuesA);
        SparseMatrix matrixB = new SparseMatrix(2, 2, valuesB);

        SparseMatrix result = matrixA.add(matrixB);

        assertEquals(5.0, result.getValues().get(new SparseMatrix.Pair<>(0, 1)));
    }
}

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public final class SparseMatrix {
    private final int rows;
    private final int cols;
    private final Map<Pair<Integer, Integer>, Double> values;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Constructs an immutable sparse matrix.
     * @param rows   The number of rows in the matrix.
     * @param cols   The number of columns in the matrix.
     * @param values A map storing nonzero elements, where keys are (row, column) pairs.
     */
    public SparseMatrix(int rows, int cols, Map<Pair<Integer, Integer>, Double> values) {
        this.rows = rows;
        this.cols = cols;
        this.values = Collections.unmodifiableMap(new HashMap<>(values));
    }

    /**
     * Returns the transpose of this matrix.
     * @return A new SparseMatrix that is the transpose of this matrix.
     */
    public SparseMatrix transpose() {
        Map<Pair<Integer, Integer>, Double> transposedValues = new HashMap<>();
        for (Map.Entry<Pair<Integer, Integer>, Double> entry : values.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            transposedValues.put(new Pair<>(key.getSecond(), key.getFirst()), entry.getValue());
        }
        return new SparseMatrix(cols, rows, transposedValues);
    }

    /**
     * Checks if the matrix is symmetric.
     * A matrix is symmetric if it is square (rows == cols) and M[i][j] == M[j][i] for all elements.
     * @return true if the matrix is symmetric, false otherwise.
     */
    public boolean isSymmetric() {
        if (rows != cols) return false;
        for (Map.Entry<Pair<Integer, Integer>, Double> entry : values.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            Double value = entry.getValue();
            if (!Objects.equals(values.get(new Pair<>(key.getSecond(), key.getFirst())), value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds this matrix with another sparse matrix.
     * @param other The matrix to add.
     * @return A new SparseMatrix representing the sum of both matrices.
     * @throws IllegalArgumentException If the dimensions do not match.
     */
    public SparseMatrix add(SparseMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }

        Map<Pair<Integer, Integer>, Double> resultValues = new HashMap<>(this.values);

        for (Map.Entry<Pair<Integer, Integer>, Double> entry : other.values.entrySet()) {
            resultValues.merge(entry.getKey(), entry.getValue(), Double::sum);
        }

        return new SparseMatrix(rows, cols, resultValues);
    }

    /**
     * Multiplies this matrix with another sparse matrix.
     * @param other The matrix to multiply with.
     * @return A new SparseMatrix representing the product.
     * @throws IllegalArgumentException If the number of columns in this matrix does not match the number of rows in the other matrix.
     */
    public SparseMatrix multiply(SparseMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Invalid matrix dimensions for multiplication.");
        }

        Map<Pair<Integer, Integer>, Double> resultValues = new HashMap<>();
        
        for (Map.Entry<Pair<Integer, Integer>, Double> entryA : this.values.entrySet()) {
            int rowA = entryA.getKey().getFirst();
            int colA = entryA.getKey().getSecond();
            double valueA = entryA.getValue();

            for (Map.Entry<Pair<Integer, Integer>, Double> entryB : other.values.entrySet()) {
                int rowB = entryB.getKey().getFirst();
                int colB = entryB.getKey().getSecond();
                double valueB = entryB.getValue();

                if (colA == rowB) {
                    Pair<Integer, Integer> resultKey = new Pair<>(rowA, colB);
                    resultValues.merge(resultKey, valueA * valueB, Double::sum);
                }
            }
        }

        return new SparseMatrix(this.rows, other.cols, resultValues);
    }

    /**
     * Gets the number of rows in the matrix.
     * @return The row count.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns in the matrix.
     * @return The column count.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the nonzero values in the matrix.
     * @return An unmodifiable map of nonzero values.
     */
    public Map<Pair<Integer, Integer>, Double> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SparseMatrix that = (SparseMatrix) o;
        return rows == that.rows && cols == that.cols && values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows, cols, values);
    }

    @Override
    public String toString() {
        return "SparseMatrix{" + "rows=" + rows + ", cols=" + cols + ", values=" + values + '}';
    }

    /**
     * A simple immutable pair class representing a row and column index.
     * @param <T> The type of the first element.
     * @param <U> The type of the second element.
     */
    public static class Pair<T, U> {
        private final T first;
        private final U second;

        /**
         * Constructs a pair.
         * @param first  The first value.
         * @param second The second value.
         */
        public Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        /**
         * Gets the first value.
         * @return The first value.
         */
        public T getFirst() {
            return first;
        }

        /**
         * Gets the second value.
         * @return The second value.
         */
        public U getSecond() {
            return second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    /**
     * This Function is to get a valid integer input in the given range.
     * @param sc
     * @param min
     * @param max
     * @return valid integer.
     */
    public static int getIntInput(Scanner sc, int min, int max){
        int num;
        while(true){
            try{
                num = sc.nextInt();
                sc.nextLine();
                if(num >= min && num <= max)return num;
                else System.out.println("Enter a valid number between " + min + " & " + max);
            }catch(Exception e){
                System.out.println("Enter a valid number between " + min + " & " + max);
                sc.nextLine();
            }
        }
    }

    /**
     * This Function is to get a valid double input.
     * @param sc
     * @return valid double value.
     */
    public static double getDoubleInput(Scanner sc){
        while(true){
            if(sc.hasNextDouble()){
                return sc.nextDouble();
            }else{
                System.out.println("Enter a valid decimal number");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Sparse Matrix Operations!");
        SparseMatrix matrix1 = createMatrix();
        
        while (true) {
            try{
                System.out.println("\n======= Choose an operation: =======");
                System.out.println("1. Print Matrix");
                System.out.println("2. Transpose Matrix");
                System.out.println("3. Check if Symmetric");
                System.out.println("4. Add Another Matrix");
                System.out.println("5. Multiply Another Matrix");
                System.out.println("6. Exit");
                System.out.println("====================================");

                int choice = getIntInput(sc, 1, 6);
                switch (choice) {
                    case 1:
                        printMatrix(matrix1);
                        break;
                    case 2:
                        matrix1 = matrix1.transpose();
                        System.out.println("Matrix Transposed!");
                        break;
                    case 3:
                        System.out.println(matrix1.isSymmetric() ? "The matrix is symmetric." : "The matrix is not symmetric.");
                        break;
                    case 4:
                        SparseMatrix matrix2 = createMatrix();
                        matrix1 = matrix1.add(matrix2);
                        System.out.println("Matrices Added!");
                        break;
                    case 5:
                        SparseMatrix matrix3 = createMatrix();
                        matrix1 = matrix1.multiply(matrix3);
                        System.out.println("Matrices Multiplied!");
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice, try again.");
                }
            }catch(Exception e){
                System.out.println("Error Occured: " + e + "\n!! Try again !!");
            }
        }
    }

    private static SparseMatrix createMatrix() {
        System.out.println("Enter number of rows:");
        int rows = getIntInput(sc, 1, Integer.MAX_VALUE);
        System.out.println("Enter number of columns:");
        int cols = getIntInput(sc, 1, Integer.MAX_VALUE);

        Map<Pair<Integer, Integer>, Double> values = new HashMap<>();
        System.out.println("Enter number of nonzero elements:");
        int nonZeroCount = getIntInput(sc, 0, Integer.MAX_VALUE);
        
        for (int i = 0; i < nonZeroCount; i++) {
            System.out.println("Enter row, column, and value:");
            int row = getIntInput(sc, 0, rows - 1);
            int col = getIntInput(sc, 0, cols - 1);
            double value = getDoubleInput(sc);
            values.put(new Pair<>(row, col), value);
        }

        return new SparseMatrix(rows, cols, values);
    }

    private static void printMatrix(SparseMatrix matrix) {
        System.out.println("Matrix Representation:");
        System.out.println(matrix.getValues());
    }
}

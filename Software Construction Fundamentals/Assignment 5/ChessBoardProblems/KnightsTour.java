import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class KnightsTour {
    private static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter board size (N x N): ");
        int n = scanner.nextInt();

        System.out.print("Enter starting X position: ");
        int startX = scanner.nextInt();

        System.out.print("Enter starting Y position: ");
        int startY = scanner.nextInt();

        solveKnightsTour(n, startX, startY);
    }

    /**
     * Solves the Knight's Tour problem and prints the result.
     */
    public static void solveKnightsTour(int n, int startX, int startY) {
        int[][] board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, -1);

        board[startX][startY] = 0;
        if (knightsTourUtil(board, n, startX, startY, 1)) {
            printBoard(board, n);
        } else {
            System.out.println("No solution found.");
        }
    }

    /**
     * Recursively attempts to complete the Knight's Tour.
     */
    private static boolean knightsTourUtil(int[][] board, int n, int x, int y, int moveCount) {
        if (moveCount == n * n) return true;

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i], nextY = y + dy[i];
            if (isValidMove(board, n, nextX, nextY)) {
                board[nextX][nextY] = moveCount;
                if (knightsTourUtil(board, n, nextX, nextY, moveCount + 1)) return true;
                board[nextX][nextY] = -1; // Backtrack
            }
        }
        return false;
    }

    /**
     * Checks if a move is valid.
     */
    private static boolean isValidMove(int[][] board, int n, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n && board[x][y] == -1;
    }

    /**
     * Prints the chessboard solution.
     */
    private static void printBoard(int[][] board, int n) {
        IntStream.range(0, n).forEach(i -> {
            Arrays.stream(board[i]).forEach(cell -> System.out.printf("%2d ", cell));
            System.out.println();
        });
    }
}

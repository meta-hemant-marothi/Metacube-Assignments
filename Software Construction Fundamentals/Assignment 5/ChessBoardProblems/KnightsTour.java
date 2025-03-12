import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class KnightsTour {
    private static final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    /**
     * Solves the Knight's Tour problem and prints the result.
     */
    public void solveKnightsTour(int n, int startX, int startY) {
        if(n <= 1){
            System.out.println("0");
            return;
        }
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
    public boolean knightsTourUtil(int[][] board, int n, int x, int y, int moveCount) {
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        KnightsTour kt = new KnightsTour();
        while (true) {
            System.out.println("===== Welcome to Knights Tour Analyzer =====");
            System.out.println("Choose what to do:- ");
            System.out.println("1. Solve Knights Tour.");
            System.out.println("2. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            int choice = getIntInput(sc, 1, 2);

            if (choice == 2) {
                System.out.println("Exiting...");
                break;
            }

            System.out.print("Enter the size of the board: ");
            int sizeOfBoard = getIntInput(sc, 1, Integer.MAX_VALUE);
            System.out.println("Enter the starting position:- ");
            System.out.print("X coordinates: ");
            int x = getIntInput(sc, 0, sizeOfBoard - 1);
            System.out.print("Y coordinates: ");
            int y = getIntInput(sc, 0, sizeOfBoard - 1);
            kt.solveKnightsTour(sizeOfBoard, x, y);
            System.out.println("\n");
        }

    }
}
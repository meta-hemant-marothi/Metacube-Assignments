import java.util.Scanner;

class NQueens {
    /**
     * Solves the N-Queens problem using recursion.
     * @param board The array representing the board, where board[i] is the column position of the queen in row i.
     * @param startRow The current row where a queen needs to be placed.
     * @param dimensionOfMatrix The size of the chessboard (N x N).
     * @return True if a solution is found, False otherwise.
     */
    static boolean nQueen(int[] board, int startRow, int dimensionOfMatrix) {
        if (startRow == dimensionOfMatrix) {
            printBoard(board, dimensionOfMatrix);
            return true;
        }

        boolean foundSolution = false;
        for (int col = 0; col < dimensionOfMatrix; col++) {
            if (isSafe(board, startRow, col)) {
                board[startRow] = col; // Place the queen
                foundSolution = nQueen(board, startRow + 1, dimensionOfMatrix) || foundSolution;
                board[startRow] = -1; // Backtrack
            }
        }

        return foundSolution;
    }

    /**
     * Checks if it's safe to place a queen at board[row] = col.
     * @param board The current state of the board.
     * @param row The row where the queen is to be placed.
     * @param col The column where the queen is to be placed.
     * @return True if it's safe, False otherwise.
     */
    static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false; // Same column or diagonal
            }
        }
        return true;
    }

    /**
     * Prints the board configuration when a valid solution is found.
     * @param board The array representing queen positions.
     * @param n The size of the board.
     */
    static void printBoard(int[] board, int n) {
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
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
        
        while (true) {
            System.out.println("===== Welcome to NQueens Problem Analyzer =====");
            System.out.println("Choose what to do:- ");
            System.out.println("1. Solve Nqueen.");
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
            int[] board = new int[sizeOfBoard];
            for (int i = 0; i < sizeOfBoard; i++) board[i] = -1;
            if (!nQueen(board, 0, sizeOfBoard)) {
                System.out.println("No solution exists.");
            }
            System.out.println("\n");

        }

    }
}
public class Main {

    private final int N;
    private int steps;

    public Main(int N) {
        this.N = N;
        this.steps = 0;
    }

    /* A utility function to print solution */
    private void printSolution(int[][] board) {
        for (int[] row : board) {
            for (int c : row) {
                System.out.print(" " + c + " ");
            }
            System.out.println();
        }
    }

    /* A utility function to check if a queen can be placed on board[row][col] */
    private boolean isSafe(int[][] board, int row, int col) {
        int i, j;

        /* Check this row on left side */
        for (i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        /* Check upper diagonal on left side */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        /* Check lower diagonal on left side */
        for (i = row, j = col; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    /* A recursive utility function to solve N Queen problem */
    private boolean solveNQUtil(int[][] board, int col) {
        /* base case: If all queens are placed then return true */
        if (col >= N) {
            return true;
        }

        /* Consider this column and try placing this queen in all rows one by one */
        for (int i = 0; i < N; i++) {
            /* Check if the queen can be placed on board[i][col] */
            if (isSafe(board, i, col)) {
                /* Place this queen in board[i][col] */
                board[i][col] = 1;

                /* Increment steps and print */
                steps++;
                System.out.println("Step " + steps + ": Queen placed at row " + (i+1) + ", column " + (col+1));

                /* recur to place rest of the queens */
                if (solveNQUtil(board, col + 1)) {
                    return true;
                }

                /* If placing queen in board[i][col] doesn't lead to a solution then remove queen from board[i][col] */
                board[i][col] = 0;
            }
        }

        /* If the queen can not be placed in any row in this column col, then return false */
        return false;
    }

    /* This function solves the N Queen problem using Backtracking */
    public boolean solveNQ() {
        int[][] board = new int[N][N];

        if (!solveNQUtil(board, 0)) {
            System.out.print("Solution does not exist");
            return false;
        }

        printSolution(board);
        return true;
    }

    public static void main(String[] args) {
        int N = 8; // Change this value to N you want to solve for
        Main Queen = new Main(N);
        Queen.solveNQ();
    }
}

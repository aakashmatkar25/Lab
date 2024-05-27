public class Main {

    private final int N;
    private int[][] board;
    private int moveCount; // To track the number of moves

    public Main(int N) {
        this.N = N;
        this.board = new int[N][N];
        this.moveCount = 0; // Initialize move count
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
        // ... same as before ...
    }

    private boolean solveNQDFS(int col) {
        if (col >= N) {
            printSolution(); // Call the printSolution method when all queens are placed
            return true;
        }
        for (int i = 0; i < N; i++) {
            if (isSafe(i, col)) {
                board[i][col] = 1;
                moveCount++; // Increment move count when a queen is placed
                if (solveNQDFS(col + 1)) {
                    return true;
                }
                board[i][col] = 0; // Backtrack
                moveCount++; // Increment move count when backtracking
            }
        }
        return false;
    }

    public void solveNQ() {
        if (!solveNQDFS(0)) {
            System.out.println("No solution exists");
        }
    }

    private void printSolution() {
        System.out.println("Solution found in " + moveCount + " moves:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1) {
                    System.out.println("Queen placed at (" + i + ", " + j + ")");
                }
            }
        }
        printBoard(); // Print the final board configuration
    }

    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 8; // Change this value to the N you want to solve for
        Main nQueensDFS = new Main(N);
        nQueensDFS.solveNQ();
    }
}
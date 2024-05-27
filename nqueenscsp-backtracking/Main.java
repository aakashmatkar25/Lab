import java.util.Arrays;

public class Main {
    private static final int N = 8;

    public static void main(String[] args) {
        int[] board = new int[N];
        Arrays.fill(board, -1);
        boolean foundSolution = solve(board, 0);

        if (foundSolution) {
            System.out.println("Solution found:");
            printBoard(board);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solve(int[] board, int col) {
        if (col >= N) return true;

        for (int rowToTry = 0; rowToTry < N; rowToTry++) {
            if (placeQueen(board, rowToTry, col)) {
                // Print the step of placing the queen at the specific position
                System.out.println("Placing queen at (" + rowToTry + "," + col + ")");
                if (solve(board, col + 1)) return true;
                // Print the step of backtracking from the position
                System.out.println("Backtracking from (" + rowToTry + "," + col + ")");
                board[col] = -1;
            }
        }
        return false;
    }

    private static boolean placeQueen(int[] board, int row, int col) {
        if (isSafe(board, row, col)) {
            board[col] = row;
            return true;
        }
        return false;
    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int prevCol = 0; prevCol < col; prevCol++) {
            int prevRow = board[prevCol];
            if (prevRow == row || Math.abs(prevRow - row) == Math.abs(prevCol - col)) {
                return false;
            }
        }
        return true;
    }

    private static void printBoard(int[] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[col] == row) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int N = 8; // Change this value to N you want to solve for

    // Helper class to hold the state of the board, the column number, and the path taken
    private static class BoardState {
        int[] queens; // queens[i] = row number where a queen is placed in the i-th column
        int col; // next column to place a queen
        LinkedList<String> path; // path taken to reach this state

        public BoardState(int[] queens, int col, LinkedList<String> path) {
            this.queens = queens.clone();
            this.col = col;
            this.path = new LinkedList<>(path);
        }
    }

    // Check if the queen can be placed at the given row and column
    private static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (queens[i] == row || Math.abs(queens[i] - row) == Math.abs(i - col)) {
                return false;
            }
        }
        return true;
    }

    // Print the board configuration
    private static void printBoard(int[] queens) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(queens[i] == j ? "Q " : "_ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Solve the N-Queens problem using BFS
    public static void solveNQueens() {
        Queue<BoardState> queue = new LinkedList<>();
        queue.add(new BoardState(new int[N], 0, new LinkedList<>()));

        while (!queue.isEmpty()) {
            BoardState currentState = queue.poll();

            // If all queens are placed, print the solution and the steps
            if (currentState.col == N) {
                printBoard(currentState.queens);
                System.out.println("Steps to reach the goal state:");
                for (String step : currentState.path) {
                    System.out.println(step);
                }
                return; // Remove this line if you want to find all solutions
            }

            // Try to place a queen in all rows of the current column
            for (int row = 0; row < N; row++) {
                if (isSafe(currentState.queens, row, currentState.col)) {
                    currentState.queens[currentState.col] = row;
                    LinkedList<String> newPath = new LinkedList<>(currentState.path);
                    newPath.add("Place queen in column " + currentState.col + " at row " + row);
                    queue.add(new BoardState(currentState.queens, currentState.col + 1, newPath));
                }
            }
        }
    }

    public static void main(String[] args) {
        solveNQueens();
    }
}

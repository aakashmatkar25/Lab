public class Main {

    private static final int N = 8; // Change this value to N you want to solve for

    // Helper method to print the board
    private static void printBoard(int[] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i] == j ? "Q " : "_ ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Check if the current board is a valid solution
    private static boolean isSolutionValid(int[] board) {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Generate all possible configurations of the board
    private static void generateAndTest(int[] board, int position) {
        if (position == N) {
            if (isSolutionValid(board)) {
                printBoard(board);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            board[position] = i;
            generateAndTest(board, position + 1);
        }
    }

    public static void main(String[] args) {
        int[] board = new int[N];
        generateAndTest(board, 0);
    }
}

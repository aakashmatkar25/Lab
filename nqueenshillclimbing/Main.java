import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int N = 8; // You can change N to be any size of the board
        int[] initialState = generateRandomState(N);
        int[] finalState = hillClimbing(initialState);
        printBoard(finalState);
    }

    private static int[] hillClimbing(int[] initialState) {
        int[] currentState = initialState;
        int currentCost = getObjectiveCost(currentState);

        while (true) {
            int[] next = getNextState(currentState);
            int nextCost = getObjectiveCost(next);

            if (nextCost < currentCost) {
                currentState = next;
                currentCost = nextCost;
            } else {
                // No better neighbors, return current state
                return currentState;
            }
        }
    }

    private static int[] getNextState(int[] currentState) {
        int[] nextState = Arrays.copyOf(currentState, currentState.length);
        int[] bestState = Arrays.copyOf(currentState, currentState.length);
        int bestCost = getObjectiveCost(currentState);

        for (int col = 0; col < nextState.length; col++) {
            for (int row = 0; row < nextState.length; row++) {
                if (row != nextState[col]) {
                    nextState[col] = row;
                    int cost = getObjectiveCost(nextState);
                    if (cost < bestCost) {
                        bestCost = cost;
                        bestState = Arrays.copyOf(nextState, nextState.length);
                    }
                    nextState[col] = currentState[col]; // revert change
                }
            }
        }
        return bestState;
    }

    private static int getObjectiveCost(int[] state) {
        int attackingPairs = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = i + 1; j < state.length; j++) {
                if (state[i] == state[j] || Math.abs(state[i] - state[j]) == Math.abs(i - j)) {
                    attackingPairs++;
                }
            }
        }
        return attackingPairs;
    }

    private static int[] generateRandomState(int N) {
        int[] state = new int[N];
        for (int i = 0; i < N; i++) {
            state[i] = random.nextInt(N);
        }
        return state;
    }

    private static void printBoard(int[] state) {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state.length; j++) {
                System.out.print(state[i] == j ? "Q " : "_ ");
            }
            System.out.println();
        }
    }
}

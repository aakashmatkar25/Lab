import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int N = 8; // You can change N to be any size of the board
        int[] initialState = generateRandomState(N);
        int[] finalState = steepestAscentHillClimbing(initialState);
        printBoard(finalState);
    }

    private static int[] steepestAscentHillClimbing(int[] initialState) {
        int[] currentState = initialState;
        int currentCost = heuristicCost(currentState);

        while (true) {
            Pair<int[], Integer> next = getBestNeighbor(currentState);
            int[] nextState = next.first;
            int nextCost = next.second;

            if (nextCost < currentCost) {
                currentState = nextState;
                currentCost = nextCost;
            } else {
                // No better neighbors, return current state
                return currentState;
            }
        }
    }

    private static Pair<int[], Integer> getBestNeighbor(int[] currentState) {
        int[] bestState = Arrays.copyOf(currentState, currentState.length);
        int bestCost = heuristicCost(currentState);

        for (int col = 0; col < currentState.length; col++) {
            int[] tempState = Arrays.copyOf(currentState, currentState.length);
            for (int row = 0; row < currentState.length; row++) {
                tempState[col] = row;
                int cost = heuristicCost(tempState);
                if (cost < bestCost) {
                    bestCost = cost;
                    bestState = Arrays.copyOf(tempState, tempState.length);
                }
            }
        }
        return new Pair<>(bestState, bestCost);
    }

    private static int heuristicCost(int[] state) {
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

    // Helper class to hold a pair of values
    private static class Pair<T1, T2> {
        public final T1 first;
        public final T2 second;

        public Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }
    }
}

import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] visited = new boolean[5][5];
    static Stack<Node> result = new Stack<Node>();
    static int totalNodesVisited = 0;

    // Depth-limited DFS
    static boolean solveDFS(int curj1, int curj2, int jug1, int jug2, int tx, int ty, int depth) {
        if (curj1 == tx && curj2 == ty) {
            result.push(new Node(curj1, curj2));
            return true;
        }

        if (depth == 0)
            return false;

        visited[curj1][curj2] = true;
        totalNodesVisited++;

        if (curj1 < jug1 && solveDFS(jug1, curj2, jug1, jug2, tx, ty, depth - 1)) {
            result.push(new Node(curj1, curj2));
            return true;
        }

        if (curj2 < jug2 && solveDFS(curj1, jug2, jug1, jug2, tx, ty, depth - 1)) {
            result.push(new Node(curj1, curj2));
            return true;
        }

        if (curj1 > 0 && solveDFS(0, curj2, jug1, jug2, tx, ty, depth - 1)) {
            result.push(new Node(curj1, curj2));
            return true;
        }

        if (curj2 > 0 && solveDFS(curj1, 0, jug1, jug2, tx, ty, depth - 1)) {
            result.push(new Node(curj1, curj2));
            return true;
        }

        if (curj1 > 0 && curj2 < jug2) {
            boolean temp;
            if (curj2 + curj1 <= jug2) {
                temp = solveDFS(0, curj1 + curj2, jug1, jug2, tx, ty, depth - 1);
            } else {
                temp = solveDFS(curj1 - (jug2 - curj2), jug2, jug1, jug2, tx, ty, depth - 1);
            }
            if (temp) {
                result.push(new Node(curj1, curj2));
                return true;
            }
        }

        if (curj2 > 0 && curj1 < jug1
                && solveDFS(Math.min(curj1 + curj2, jug1), curj2 - Math.min(jug1, curj2), jug1, jug2, tx, ty, depth - 1)) {
            boolean temp;
            if (curj2 + curj1 <= jug1) {
                temp = solveDFS(curj1 + curj2, 0, jug1, jug2, tx, ty, depth - 1);
            } else {
                temp = solveDFS(jug1, curj2 - (jug1 - curj2), jug1, jug2, tx, ty, depth - 1);
            }
            if (temp) {
                result.push(new Node(curj1, curj2));
                return true;
            }
        }

        return false;
    }

    // Iterative Deepening DFS
    static boolean iterativeDeepeningDFS(int jug1, int jug2, int tx, int ty, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            if (solveDFS(0, 0, jug1, jug2, tx, ty, depth)) {
                return true;
            }
            clearVisited();
        }
        return false;
    }

    static void clearVisited() {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    // function to print the stack in reverse order
    public static void printReverseStack(Stack<Node> stack) {
        // base case
        if (stack.isEmpty())
            return;

        // create temporary stack
        Stack<Node> temporaryStack = new Stack<>();

        // copy all the elements from given stack to temporary stack
        while (stack.size() > 0) {
            temporaryStack.push(stack.pop());
        }

        for (Node n : temporaryStack) {
            System.out.println("(" + n.x + "," + n.y + ")");
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        boolean res = iterativeDeepeningDFS(4, 3, 2, 0, 10); // Adjust maxDepth as needed
        if (res) {
            printReverseStack(result);
        } else {
            System.out.println("No solution found within depth limit.");
        }
        System.out.println("Total Nodes Visited: " + totalNodesVisited);
    }
}
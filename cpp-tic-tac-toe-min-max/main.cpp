#include <iostream>
#include <vector>
#include <algorithm> // Include algorithm for std::find

#define SIZE 3

// Structure representing a block on the Tic Tac Toe board
typedef struct {
    int value;
} Block;

// Structure representing the Tic Tac Toe game
typedef struct {
    Block board[SIZE][SIZE];
    int turn;
} TicTacToe;

// Function prototypes
void initializeGame(TicTacToe *game);
void printBoard(TicTacToe *game);
int isGameOver(TicTacToe *game);
int checkWin(TicTacToe *game, int player);
int checkDraw(TicTacToe *game);
int minMax(TicTacToe *game, int depth, int isMaximizing);
int getBestMove(TicTacToe *game);
double heuristicEvaluation(TicTacToe *game);

int main() {
    TicTacToe game;
    initializeGame(&game);

    while (!isGameOver(&game)) {
        printBoard(&game);

        if (game.turn == 1) {
            // Player's turn
            int row, col;
            printf("Enter your move (row and column, separated by space): ");
            scanf("%d %d", &row, &col);

            if (game.board[row][col].value == 0) {
                game.board[row][col].value = 1;
                game.turn = -1;
            } else {
                printf("Invalid move. Try again.\n");
            }
        } else {
            // Computer's turn
            int bestMove = getBestMove(&game);
            int row = bestMove / SIZE;
            int col = bestMove % SIZE;

            if (game.board[row][col].value == 0) {
                game.board[row][col].value = -1;
                game.turn = 1;
            }

            printBoard(&game); // Display the board after the computer's move
            std::cout << "Heuristic value for the selected move: " << heuristicEvaluation(&game) << std::endl;
        }
    }

    printBoard(&game);

    if (checkWin(&game, 1)) {
        std::cout << "You win!" << std::endl;
    } else if (checkWin(&game, -1)) {
        std::cout << "Computer wins!" << std::endl;
    } else {
        std::cout << "It's a draw!" << std::endl;
    }

    return 0;
}

void initializeGame(TicTacToe *game) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            game->board[i][j].value = 0;
        }
    }
    game->turn = 1;
}

void printBoard(TicTacToe *game) {
    std::cout << std::endl;
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (game->board[i][j].value == 1) {
                std::cout << "X ";
            } else if (game->board[i][j].value == -1) {
                std::cout << "O ";
            } else {
                std::cout << "_ ";
            }
        }
        std::cout << std::endl;
    }
    std::cout << std::endl;
}

int isGameOver(TicTacToe *game) {
    return checkWin(game, 1) || checkWin(game, -1) || checkDraw(game);
}

int checkWin(TicTacToe *game, int player) {
    for (int i = 0; i < SIZE; i++) {
        if ((game->board[i][0].value == player && game->board[i][1].value == player && game->board[i][2].value == player) ||
            (game->board[0][i].value == player && game->board[1][i].value == player && game->board[2][i].value == player)) {
            return 1;
        }
    }
    if ((game->board[0][0].value == player && game->board[1][1].value == player && game->board[2][2].value == player) ||
        (game->board[0][2].value == player && game->board[1][1].value == player && game->board[2][0].value == player)) {
        return 1;
    }

    return 0;
}

int checkDraw(TicTacToe *game) {
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (game->board[i][j].value == 0) {
                return 0;
            }
        }
    }
    return 1;
}

double heuristicEvaluation(TicTacToe *game) {
    // A simple heuristic: the probability of winning is higher if more moves have been made
    int movesMade = 0;
    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (game->board[i][j].value != 0) {
                movesMade++;
            }
        }
    }

    return static_cast<double>(movesMade) / (SIZE * SIZE);
}

int minMax(TicTacToe *game, int depth, int isMaximizing) {
    if (checkWin(game, 1)) {
        return -1;
    } else if (checkWin(game, -1)) {
        return 1;
    } else if (checkDraw(game)) {
        return 0;
    }

    if (isMaximizing) {
        int bestScore = -1000;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (game->board[i][j].value == 0) {
                    game->board[i][j].value = -1;
                    int score = minMax(game, depth + 1, 0);
                    game->board[i][j].value = 0;
                    bestScore = std::max(score, bestScore);
                }
            }
        }
        return bestScore;
    } else {
        int bestScore = 1000;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (game->board[i][j].value == 0) {
                    game->board[i][j].value = 1;
                    int score = minMax(game, depth + 1, 1);
                    game->board[i][j].value = 0;
                    bestScore = std::min(score, bestScore);
                }
            }
        }
        return bestScore;
    }
}

int getBestMove(TicTacToe *game) {
    int bestMove = -1;
    double bestScore = -1000;

    for (int i = 0; i < SIZE; i++) {
        for (int j = 0; j < SIZE; j++) {
            if (game->board[i][j].value == 0) {
                game->board[i][j].value = -1;
                double score = minMax(game, 0, 0) + heuristicEvaluation(game);
                game->board[i][j].value = 0;

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i * SIZE + j;
                }
            }
        }
    }

    return bestMove;
}


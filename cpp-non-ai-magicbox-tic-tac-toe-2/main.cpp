#include <iostream>
#include <vector>

using namespace std;

// Global variables
vector<int> board(10, 0); // Index 0 is unused, to make the board indices match the numbering in the algorithm
int turn = 1; // Indicates which move of the game is about to be played

// Function to check if a player can win on the next move
int posswin(int p) {
    vector<int> playerSquares;
    for (int i = 1; i <= 9; ++i) {
        if (board[i] == p) {
            playerSquares.push_back(i);
        }
    }
    for (int i = 0; i < playerSquares.size(); ++i) {
        for (int j = i + 1; j < playerSquares.size(); ++j) {
            int diff = 15 - (playerSquares[i] + playerSquares[j]);
            if (diff > 0 && diff <= 9 && board[diff] == 0) {
                return diff;
            }
        }
    }
    return 0;
}

// Function to make a move in square n
void go(int n, int player) {
    board[n] = player;
    cout << "Player " << ((player == 1) ? 'X' : 'O') << " plays in square " << n << endl;
    turn++;
}

// Function to display the board
void displayBoard() {
    cout << " " << board[8] << " | " << board[3] << " | " << board[4] << endl;
    cout << "---|---|---" << endl;
    cout << " " << board[1] << " | " << board[5] << " | " << board[9] << endl;
    cout << "---|---|---" << endl;
    cout << " " << board[6] << " | " << board[7] << " | " << board[2] << endl;
}

// Main function to play the game
void playGame() {
    while (turn <= 9) {
        if (turn % 2 == 1) {
            // Player X's turn
            int move;
            cout << "Enter the position (1-9) to place your mark (X): ";
            cin >> move;
            if (move < 1 || move > 9 || board[move] != 0) {
                cout << "Invalid move. Please try again." << endl;
                continue;
            }
            go(move, 1);
        } else {
            // CPU's turn
            int winMove = posswin(2); // Check if CPU can win
            if (winMove != 0) {
                go(winMove, 2);
                cout << "CPU (O) wins!" << endl;
                break;
            }
            int blockMove = posswin(1); // Check if human can win and block
            if (blockMove != 0) {
                go(blockMove, 2);
                continue;
            }
            int move = posswin(1); // Check if human can win
            if (move != 0) {
                go(move, 2);
                continue;
            }
            move = posswin(2); // Check if CPU can win
            if (move != 0) {
                go(move, 2);
                continue;
            }
            for (int i = 1; i <= 9; ++i) {
                if (board[i] == 0) {
                    go(i, 2);
                    break;
                }
            }
        }
        displayBoard(); // Display the board after each move
        cout << endl;
    }
}

int main() {
    cout << "Welcome to Tic-Tac-Toe!" << endl;
    cout << "You are playing as X against the CPU (O)" << endl;
    cout << "The board positions are numbered as follows:" << endl;
    cout << " 8 | 3 | 4 " << endl;
    cout << "---|---|---" << endl;
    cout << " 1 | 5 | 9 " << endl;
    cout << "---|---|---" << endl;
    cout << " 6 | 7 | 2 " << endl;
    cout << "Let's begin!" << endl;
    displayBoard(); // Display the initial board
    cout << endl;
    playGame();
    return 0;
}

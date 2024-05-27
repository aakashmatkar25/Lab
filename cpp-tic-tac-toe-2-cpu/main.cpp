#include <iostream>
#include <vector>

using namespace std;

// Global variables
vector<int> board(10, 2); // Index 0 is unused, to make the board indices match the numbering in the algorithm
int turn = 1; // Indicates which move of the game is about to be played

// Function to check if a player can win on the next move
int posswin(int p) {
    // Checking rows
    for (int i = 1; i <= 7; i += 3) {
        if (board[i] * board[i + 1] * board[i + 2] == p * p * 2) {
            if (board[i] == 2) return i;
            if (board[i + 1] == 2) return i + 1;
            if (board[i + 2] == 2) return i + 2;
        }
    }
    // Checking columns
    for (int i = 1; i <= 3; ++i) {
        if (board[i] * board[i + 3] * board[i + 6] == p * p * 2) {
            if (board[i] == 2) return i;
            if (board[i + 3] == 2) return i + 3;
            if (board[i + 6] == 2) return i + 6;
        }
    }
    // Checking diagonals
    if (board[1] * board[5] * board[9] == p * p * 2) {
        if (board[1] == 2) return 1;
        if (board[5] == 2) return 5;
        if (board[9] == 2) return 9;
    }
    if (board[3] * board[5] * board[7] == p * p * 2) {
        if (board[3] == 2) return 3;
        if (board[5] == 2) return 5;
        if (board[7] == 2) return 7;
    }
    return 0;
}

// Function to make a move in square n
void go(int n) {
    board[n] = (turn % 2 == 1) ? 3 : 5;
    cout << "CPU (" << ((turn % 2 == 1) ? 'X' : 'O') << ") plays in square " << n << " using go("<< n <<")"<< endl;
    turn++;
}

// Function to make 2
int make2() {
    if (board[5] == 2)
        return 5;
    else {
        for (int i = 2; i <= 8; i += 2) {
            if (board[i] == 2)
                return i;
        }
    }
    return 0;
}

// Function to display the board
void displayBoard() {
    cout << " " << board[1] << " | " << board[2] << " | " << board[3] << endl;
    cout << "---|---|---" << endl;
    cout << " " << board[4] << " | " << board[5] << " | " << board[6] << endl;
    cout << "---|---|---" << endl;
    cout << " " << board[7] << " | " << board[8] << " | " << board[9] << endl;
}

// Main function to play the game
void playGame() {
    while (turn <= 9) {
        if (turn == 1)
            go(1); // First move
        else if (turn == 2) {
            int move = make2();
            if (move != 0)
                go(move);
            else
                go(1);
        } else if (turn == 3) {
            if (board[9] == 2)
                go(9);
            else
                go(3);
        } else if (turn == 4) {
            int winMove = posswin(3); // Check if X can win
            if (winMove != 0) {
                go(winMove);
                cout << "Posswin(X) is used to block the opponent's win" << endl;
            } else {
                go(make2());
                cout << "Make2() is used to select a move" << endl;
            }
        } else if (turn == 5) {
            int winMoveX = posswin(3); // Check if X can win
            if (winMoveX != 0) {
                go(winMoveX);
                cout << "Posswin(X) is used to win" << endl;
            } else {
                int winMoveO = posswin(5); // Check if O can win
                if (winMoveO != 0) {
                    go(winMoveO);
                    cout << "Posswin(O) is used to block the opponent's win" << endl;
                } else if (board[7] == 2) {
                    go(7);
                    cout << "CPU (X) makes a fork by playing in square 7" << endl;
                } else {
                    go(3);
                    cout << "Default move" << endl;
                }
            }
        } else if (turn == 6) {
            int winMoveO = posswin(5); // Check if O can win
            if (winMoveO != 0) {
                go(winMoveO);
                cout << "Posswin(O) is used to win" << endl;
            } else {
                int winMoveX = posswin(3); // Check if X can win
                if (winMoveX != 0) {
                    go(winMoveX);
                    cout << "Posswin(X) is used to block the opponent's win" << endl;
                } else {
                    go(make2());
                    cout << "Make2() is used to select a move" << endl;
                }
            }
        } else if (turn == 7 || turn == 9) {
            int winMoveX = posswin(3); // Check if X can win
            if (winMoveX != 0) {
                go(winMoveX);
                cout << "Posswin(X) is used to win" << endl;
            } else {
                int winMoveO = posswin(5); // Check if O can win
                if (winMoveO != 0) {
                    go(winMoveO);
                    cout << "Posswin(O) is used to block the opponent's win" << endl;
                } else {
                    for (int i = 1; i <= 9; ++i) {
                        if (board[i] == 2) {
                            go(i);
                            cout << "CPU (" << ((turn % 2 == 1) ? 'X' : 'O') << ") makes a move" << endl;
                            break;
                        }
                    }
                }
            }
        } else if (turn == 8) {
            int winMoveO = posswin(5); // Check if O can win
            if (winMoveO != 0) {
                go(winMoveO);
                cout << "Posswin(O) is used to win" << endl;
            } else {
                int winMoveX = posswin(3); // Check if X can win
                if (winMoveX != 0) {
                    go(winMoveX);
                    cout << "Posswin(X) is used to block the opponent's win" << endl;
                } else {
                    for (int i = 1; i <= 9; ++i) {
                        if (board[i] == 2) {
                            go(i);
                            cout << "CPU (" << ((turn % 2 == 1) ? 'X' : 'O') << ") makes a move" << endl;
                            break;
                        }
                    }
                }
            }
        }
        displayBoard(); // Display the board after each move
        cout << endl;
    }
}

int main() {
    cout << "Welcome to Tic-Tac-Toe!" << endl;
    cout << "CPU (X) is playing against CPU (O)" << endl;
    cout << "The board positions are numbered as follows:" << endl;
    cout << " 1 | 2 | 3 " << endl;
    cout << "---|---|---" << endl;
    cout << " 4 | 5 | 6 " << endl;
    cout << "---|---|---" << endl;
    cout << " 7 | 8 | 9 " << endl;
    cout << "Let's begin!" << endl;
    displayBoard(); // Display the initial board
    cout << endl;
    playGame();
    return 0;
}

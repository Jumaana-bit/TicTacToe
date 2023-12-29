# Java Tic-Tac-Toe Game

## Overview
This Java program implements a simple command-line Tic-Tac-Toe game where a user can play against a computer opponent. The game uses the minimax algorithm for the computer's move decision.

## Files
- `Main.java`: Contains the main class with the game loop and user interface.
- `TicTacToe.java`: Defines the TicTacToe class, which manages the game board and logic.
- `gameMoves.java`: Defines the gameMoves class, representing a move on the game board.

## How to Play
1. Run the `Main.java` file to start the game.
2. The user is prompted to choose who will play first: Computer (C) or Human (H).
3. If the computer starts, it makes the first move randomly.
4. The user enters their moves by specifying the row and column numbers (0-2).
5. The game continues until there is a winner or a draw.

## Game Logic
- The game checks for a winner or draw after each move.
- The computer uses the minimax algorithm to make intelligent moves.
- The program keeps track of the user's win, draw, and loss streaks.

## Sample Run
1. Choose who plays first (C or H).
2. Enter the human player's moves.
3. The computer responds intelligently.
4. The game outcome is displayed, along with streak statistics.
5. Choose to play again (enter 0) or exit (enter any other number).

## Dependencies
- Java Development Kit (JDK)

## Notes
- This program was developed and tested with Java.
- Feel free to explore and modify the code to add features or improve functionality.


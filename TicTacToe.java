import java.util.ArrayList;
import java.util.List;

public class TicTacToe {

    private static char [][] gameBoard = new char[3][3];
    public char human, computer;
    public gameMoves computersGameMove;

    public TicTacToe() {
        human = 'X';
        computer = 'O';
    }

    public void resetGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    public List<gameMoves> getAvailableMoves() {
        List<gameMoves> availableMoves = new ArrayList<gameMoves>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == 0) {
                    availableMoves.add(new gameMoves(i,j));
                }
            }
        }
        return availableMoves;
    }

    /* Display the board with coordinates to make it easier*/
    public void displayGameBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.print(" |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + i + " " + j + ",");
            }
            System.out.println();
        }
    }

    public boolean isGameOver() {
        return AIWin() || humanWin() || getAvailableMoves().isEmpty();
    }
    public boolean AIWin() {
        /*check if AI won by making a row or column */
        if ((gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] == 'O')
                || (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] == 'O')) {
            return true;
        }
        /*check if AI won by making a diagonal */
        for (int i = 0; i < 3; ++i) {
            if (((gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] == 'O')
                    || (gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i]
                    && gameBoard[0][i] == 'O'))) {
                return true;
            }
        }
        return false;
    }

    public boolean humanWin() {
        /*check if human won by making a row or column */
        if ((gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && gameBoard[0][0] == 'X')
                || (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && gameBoard[0][2] == 'X')) {
            return true;
        }
        /*check if human won by making a row or column */
        for (int i = 0; i < 3; ++i) {
            if ((gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][0] == gameBoard[i][2] && gameBoard[i][0] == 'X')
                    || (gameBoard[0][i] == gameBoard[1][i] && gameBoard[0][i] == gameBoard[2][i]
                    && gameBoard[0][i] == 'X')) {
                return true;
            }
        }

        return false;
    }

    /*Ensure position was not already taken in a previous move*/
    public boolean moveNotTaken(int x, int y) {
        if (gameBoard[x][y] != 0) {
            System.out.println("Invalid move");
            return false;
        }
        return true;
    }

    //this function compares all possible moves and returns the one with max score
    public int minimax(int depth, char player) {

        if (AIWin())
            return +1;
        if (humanWin())
            return -1;

        List<gameMoves> movesAvailable = getAvailableMoves();
        /*If no moves are available, return 0 to indicate a draw*/
        if (movesAvailable.isEmpty())
            return 0;

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < movesAvailable.size(); ++i) {

            /*gets available move at the index*/
            gameMoves gameMove = movesAvailable.get(i);

            if (player == computer) {
                /*computer makes a move */
                makeMove(gameMove, 'O');
                int currentScore = minimax(depth + 1, human);
                /*gives the maximum value between currentScore and MIN_VALUE (+1 or 0)*/
                max = Math.max(currentScore, max);


                if (currentScore >= 0) {
                    if (depth == 0)
                        computersGameMove = gameMove;
                } else if (currentScore == 1) { /*If computer wins, reset win */
                    resetMove(gameMove);
                    break;
                }

                if (i == getAvailableMoves().size() - 1 && max > 0) {
                    if (depth == 0)
                        computersGameMove = gameMove;
                }
            } else if (player == human) {
                makeMove(gameMove, 'X');
                int currentScore = minimax(depth + 1, computer);
                /* gives the minimum value between currentScore and MAX_VALUE (-1 or 0)*/
                min = Math.min(currentScore, min);
                /*If computer loses, reset move*/
                if (min == -1) {
                    resetMove(gameMove);
                    break;
                }
            }
            resetMove(gameMove);
        }
        /*If player is computer, return max. Else, return min */
        return (player == computer) ? max : min;
    }

    public void resetMove(gameMoves move) {
        gameBoard[move.getX()][move.getY()] = 0;
    }

    public void makeMove(gameMoves move, char player) {
        gameBoard[move.getX()][move.getY()] = player;
    }
}
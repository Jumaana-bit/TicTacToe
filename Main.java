import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int t = 0, w = 0, l = 0, d = 0, match = 0, wm = 0, dm = 0, lm = 0;
        do {
            Scanner scan = new Scanner(System.in);
            System.out.println("Tic-Tac-Toe");
            System.out.println("-----------------------------------------");

            TicTacToe game = new TicTacToe();
            Random random = new Random();
            game.resetGame();
            game.displayGameBoard();
            System.out.println("");
            System.out.print("Who will play first? (C)omputer (H)uman, Enter C or H ");

            char firstMove = scan.next().charAt(0);

            if (firstMove == 'C') {
                //computer will start with a move in a random position
                gameMoves gameMove = new gameMoves(random.nextInt(3), random.nextInt(3));
                System.out.println("Computer will use O, Human will use X\n");
                game.makeMove(gameMove, 'O');
                game.displayGameBoard();
            }

            //take row and column number only if it's not already taken
            while (!game.isGameOver()) {
                System.out.println("Human Move: ");
                System.out.println("Enter value (first enter row number(0-2) then column number(0-2)):");

                //exception handling for numbers out of range (0-2)
                int x, y;
                try {
                    x = scan.nextInt();
                    y = scan.nextInt();
                    if (!(x >= 0 && x <= 2) && !(y >= 0 && y <= 2)) {
                        System.out.println("Invalid input; re-enter slot number:");
                        continue;
                    }
                }
                catch (InputMismatchException e) {
                        System.out.println("Invalid input; re-enter slot number:");
                        continue;
                    }

                while (!game.moveNotTaken(x, y)) {
                    System.out.println("Renter position");
                    x = scan.nextInt();
                    y = scan.nextInt();
                }

                gameMoves gameMove = new gameMoves(x, y);
                game.makeMove(gameMove, 'X');

                //display game board after making move
                game.displayGameBoard();

                if (game.isGameOver())
                    break;

                game.minimax(0, 'O');

                game.makeMove(game.computersGameMove, 'O');
                System.out.println("Computer move:");
                game.displayGameBoard();
            }

            if (game.AIWin()) {
                System.out.println("You Lost!");
                l++;
                w=0;
                d=0;
                lm++;
                System.out.println("Your Streak " + l + " loss(es)");
            } else if (game.humanWin()) {
                System.out.println("Congratulations. You win!");
                w++;
                l=0;
                d=0;
                wm++;
                System.out.println("Your Streak " + w + " Win(s)");
            } else {
                System.out.println("Draw Match!");
                d++;
                w=0;
                l=0;
                dm++;
                System.out.println("Your Streak " + d + " Draw");
            }
            match++;
            System.out.println("You have " +  wm + " win(s), " + dm + " draw(s), and "+ lm + " loss(es) in a total of "+ match + " game(s)." );

            System.out.println("Hit 0 to do it again, or any number to exit ");
            t = scan.nextInt();
        } while(t==0);
    }
}
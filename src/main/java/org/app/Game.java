package org.app;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final int BOARD_SIZE = 9;
    private static final char EMPTY_CELL = ' ';
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';

    private final char[] board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final Scanner scanner;
    private final Random random = new Random();

    public Game(Scanner scanner) {
        this.scanner = scanner;
    }


    public void play() {
        boolean isGameOver = false;
        boolean isBoxEmpty = false;

        while (!isGameOver) {
            displayBoard();

            if (!isBoxEmpty) {
                resetBoard();
                isBoxEmpty = true;
            }

            if (checkWinner(PLAYER_X)) {
                isGameOver = true;
                displayMessage("You won the game!", isGameOver);
            } else if (checkWinner(PLAYER_O)) {
                isGameOver = true;
                displayMessage("You lost the game!", isGameOver);
            } else if (isBoardFull()) {
                isGameOver = true;
                displayMessage("It's a draw!", isGameOver);
            }


            if (!isGameOver) {
                playerMove(PLAYER_X);
                computerMove(PLAYER_O);
            }
        }
    }

    private void displayBoard() {
        System.out.println("\n\n " + board[0] + " | " + board[1] + " | " + board[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8] + " \n");
    }

    private void resetBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY_CELL;
        }
    }

    private void displayMessage(String message, boolean finish) {
        if (finish) {
            System.out.println(message + "\nCreated by Shreyas Saha. Thanks for playing!");
        } else {
            System.out.println(message);
        }
    }

    private boolean checkWinner(char player) {
        for (int i = 0; i < BOARD_SIZE; i += 3) {
            if (board[i] == player && board[i + 1] == player && board[i + 2] == player) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] == player && board[i + 3] == player && board[i + 6] == player) {
                return true;
            }
        }

        if (board[0] == player && board[4] == player && board[8] == player) {
            return true;
        }

        return board[2] == player && board[4] == player && board[6] == player;
    }


    private boolean isBoardFull() {
        for (char cell : board) {
            if (cell != PLAYER_X && cell != PLAYER_O) {
                return false;
            }
        }
        return true;
    }


    private void playerMove(char player) {

        while (true) {
            try {
                displayMessage("Enter box number to select.", false);
                int input = scanner.nextInt();
                if (isValidMove(input)) {
                    board[input - 1] = player;
                    break;
                } else {
                    displayMessage("Invalid input. Enter again.", false);
                }
            } catch (NumberFormatException e) {
                displayMessage("Invalid input. Enter again.", false);
            }
        }
    }


    private boolean isValidMove(int move) {
        return move > 0 && move <= BOARD_SIZE && board[move - 1] == EMPTY_CELL;
    }

    private void computerMove(char player) {

        while (true) {
            int randomMove = random.nextInt(BOARD_SIZE);
            if (isValidMove(randomMove)) {
                board[randomMove - 1] = player;
                break;
            }
        }
    }
}

package org.app;

import java.util.Scanner;

public class AppLauncher {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            var game = new Game(scanner);
            game.play();
        }
    }
}
package com.neptune;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gomoku gomoku = new Gomoku();
        while (true) {
            if (gomoku.state.getCurrentPlayer() == Mark.O) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                gomoku.performMove(x, y);
            } else {
                scanner.nextInt();
            }
            System.out.println(gomoku.state.toString());
        }
    }
}

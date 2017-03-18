package com.neptune;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        Gomoku gomoku = new Gomoku();
//        while (true) {
//            if (gomoku.state.getCurrentPlayer() == Mark.O) {
//                int x = scanner.nextInt();
//                int y = scanner.nextInt();
//                gomoku.performMove(x, y);
//            } else {
//                Move move = gomoku.getBestMove();
//                gomoku.performMove(move.row, move.col);
//            }
//            System.out.println(gomoku.state.toString());
//        }
        GomokuFrame gomokuFrame = new GomokuFrame();
        gomokuFrame.setResizable(false);
        gomokuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}

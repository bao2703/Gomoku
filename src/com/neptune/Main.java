package com.neptune;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int in = scanner.nextInt();
//        Mark firstPlayer = Mark.O;
//        if (in == 1) {
//            firstPlayer = Mark.X;
//        }
        GomokuFrame gomokuFrame = new GomokuFrame();
        gomokuFrame.setResizable(false);
        gomokuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}

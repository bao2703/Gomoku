package com.neptune;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GomokuFrame gomokuFrame = new GomokuFrame();
        gomokuFrame.setResizable(false);
        gomokuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}

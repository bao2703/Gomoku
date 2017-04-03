package com.neptune;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SetupFrame setupFrame = new SetupFrame();
        setupFrame.setResizable(false);
        setupFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setupFrame.setSize(300, 300);
        setupFrame.setVisible(true);
    }
}

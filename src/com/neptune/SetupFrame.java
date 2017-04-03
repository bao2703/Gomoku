package com.neptune;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Neptune on 4/3/2017.
 */
public class SetupFrame extends JFrame {
    private GomokuFrame gomokuFrame;

    public SetupFrame() {
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(2, 2));
        JButton first = new JButton("First");
        JButton second = new JButton("Second");
        panel.add(first);
        panel.add(second);
        first.addActionListener(e -> {
            gomokuFrame = new GomokuFrame(true);
            showGomokuFrame();
        });

        second.addActionListener(e -> {
            gomokuFrame = new GomokuFrame(false);
            showGomokuFrame();
        });
    }

    private void showGomokuFrame() {
        gomokuFrame.setResizable(false);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}

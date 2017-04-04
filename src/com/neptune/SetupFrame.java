package com.neptune;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Neptune on 4/3/2017.
 */
public class SetupFrame extends JFrame {
    public SetupFrame() {
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(2, 2));
        JButton first = new JButton("First");
        first.setFont(new Font("Arial", Font.BOLD, 45));
        JButton second = new JButton("Second");
        second.setFont(new Font("Arial", Font.BOLD, 45));
        panel.add(first);
        panel.add(second);
        first.addActionListener(e -> {
            Rule.PLAYER_FIRST = true;
            initGomokuFrame();
        });

        second.addActionListener(e -> {
            Rule.PLAYER_FIRST = false;
            initGomokuFrame();
        });
    }

    private void initGomokuFrame() {
        GomokuFrame gomokuFrame = new GomokuFrame();
        gomokuFrame.setResizable(false);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}

package com.neptune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Neptune on 3/18/2017.
 */
public class GomokuFrame extends JFrame {
    private Gomoku gomoku;
    private MarkButton[][] markButton;
    GameState gameState = GameState.ON_GOING;

    public GomokuFrame() {
        gomoku = new Gomoku();
        markButton = new MarkButton[Rule.SIZE][Rule.SIZE];
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(Rule.SIZE, Rule.SIZE));
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                markButton[i][j] = new MarkButton(i, j);
                panel.add(markButton[i][j]);
            }
        }
    }

    private class MarkButton extends JButton implements ActionListener {
        private boolean active;
        public int row;
        public int col;

        public MarkButton(int row, int col) {
            active = true;
            this.row = row;
            this.col = col;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (active) {
                active = false;
                gomoku.performMove(row, col);
                this.setText("X");
                this.setFont(new Font("Arial", Font.BOLD, 40));
                this.setForeground(Color.red);

                if (gameState == GameState.ON_GOING) {
                    Move move = gomoku.getBestMove();
                    gomoku.performMove(move.row, move.col);
                    markButton[move.row][move.col].setText("O");
                    markButton[move.row][move.col].setFont(new Font("Arial", Font.BOLD, 40));
                    markButton[move.row][move.col].setForeground(Color.blue);
                }
            }
        }
    }
}


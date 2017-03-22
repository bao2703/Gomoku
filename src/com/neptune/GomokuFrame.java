package com.neptune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Neptune on 3/18/2017.
 */
public class GomokuFrame extends JFrame {
    public Gomoku gomoku;
    private GameState gameState = GameState.ON_GOING;
    private MarkButton[][] markButton;
    private boolean playerTurn = true;
    //private File xFile = new File("X.txt");
    //private File oFile = new File("O.txt");

    public GomokuFrame() {
        gomoku = new Gomoku();
        markButton = new MarkButton[Rule.SIZE][Rule.SIZE];
        initComponents();

//        if (xFile.exists()) {
//            xFile.delete();
//        }
//        if (oFile.exists()) {
//            oFile.delete();
//        }

//        Move move = gomoku.getBestMove();
//        markButton[move.row][move.col].makeMove();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(Rule.SIZE, Rule.SIZE));
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                markButton[i][j] = new MarkButton(i, j);
                markButton[i][j].setFont(new Font("Arial", Font.BOLD, 45));
                panel.add(markButton[i][j]);
            }
        }
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void aiTurn() {
        if (gameState == GameState.ON_GOING) {
            Move move = gomoku.getBestMove();
            markButton[move.row][move.col].makeMove();
            playerTurn = true;
        }
    }

    private class MarkButton extends JButton implements ActionListener {
        private boolean active;
        private int row;
        private int col;

        public MarkButton(int row, int col) {
            active = true;
            this.row = row;
            this.col = col;
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (active && playerTurn) {
                Move aiMove = gomoku.state.getLastMove();
                if (aiMove != null) {
                    markButton[aiMove.row][aiMove.col].setForeground(Color.blue);
                }
                this.makeMove();
                IterativeDeepeningThread iterativeDeepeningThread = new IterativeDeepeningThread(GomokuFrame.this, 0);
                iterativeDeepeningThread.start();
            }
        }

        public void makeMove() {
            changeIcon();
            gomoku.performMove(row, col);
        }

        private void changeIcon() {
            active = false;
            if (gomoku.state.getCurrentPlayer() == Mark.MIN) {
                this.setText("O");
                this.setForeground(Color.red);
            } else {
                this.setText("X");
                this.setForeground(Color.magenta);
            }
        }
    }
}


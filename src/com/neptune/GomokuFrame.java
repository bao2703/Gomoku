package com.neptune;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Neptune on 3/18/2017.
 */
public class GomokuFrame extends JFrame {
    public State state;
    private GameState gameState = GameState.ON_GOING;
    private MarkButton[][] markButton;
    private boolean playerTurn = true;
    private AlphaBeta alphaBeta = new AlphaBeta();

    public GomokuFrame() {
        state = new State();
        markButton = new MarkButton[Rule.SIZE][Rule.SIZE];
        initComponents();
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
            Move move = alphaBeta.exec(state, Rule.MAX_DEPTH);
            markButton[move.row][move.col].makeMove();
            playerTurn = true;
        }
    }

    public void writeState(File file) throws IOException {
        BufferedWriter bufferedWriter;
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.write(state.getLastMove().toString());
        bufferedWriter.newLine();
        bufferedWriter.close();
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
                Move aiMove = state.getLastMove();
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
            state.performMove(new Move(row, col));
        }

        private void changeIcon() {
            active = false;
            if (state.getCurrentPlayer() == Mark.MIN) {
                this.setText("O");
                this.setForeground(Color.red);
            } else {
                this.setText("X");
                this.setForeground(Color.magenta);
            }
        }
    }
}
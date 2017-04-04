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
    private MarkButton[][] markButton;
    private boolean playerTurn = true;

    public GomokuFrame() {
        initComponents();

        if (!Rule.PLAYER_FIRST) {
            aiTurn();
        }
    }

    private void initComponents() {
        state = new State();
        markButton = new MarkButton[Rule.SIZE][Rule.SIZE];
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(Rule.SIZE, Rule.SIZE));
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                markButton[i][j] = new MarkButton(i, j);
                markButton[i][j].setFont(new Font("Arial", Font.BOLD, 45));
                markButton[i][j].setOpaque(true);
                panel.add(markButton[i][j]);
            }
        }
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void aiTurn() {
        IterativeDeepeningThread iterativeDeepeningThread = new IterativeDeepeningThread(GomokuFrame.this);
        iterativeDeepeningThread.start();
    }

    public void performMove(Move move) {
        markButton[move.row][move.col].setMove();
        checkState();
    }

    public void checkState() {
        GameState gameState = state.checkState();
        if (gameState == GameState.MAX_WIN) JOptionPane.showMessageDialog(null, "O Win");
        else if (gameState == GameState.MIN_WIN) JOptionPane.showMessageDialog(null, "X Win");
        else if (gameState == GameState.DRAW) JOptionPane.showMessageDialog(null, "Draw");
        else return;
        this.dispose();
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
                    if (Rule.PLAYER_FIRST) markButton[aiMove.row][aiMove.col].setForeground(Color.BLUE);
                    else markButton[aiMove.row][aiMove.col].setForeground(Color.RED);
                }
                performMove(new Move(row, col));

                aiTurn();
            }
        }

        public void setMove() {
            changeIcon();
            state.performMove(new Move(row, col));
        }

        private void changeIcon() {
            active = false;
            if (state.getCurrentPlayer() == Mark.MIN) {
                this.setText("X");
                this.setForeground(Color.RED);
            } else {
                this.setText("O");
                this.setForeground(Color.BLUE);
            }
            if (!playerTurn) this.setForeground(Color.MAGENTA);
        }
    }
}
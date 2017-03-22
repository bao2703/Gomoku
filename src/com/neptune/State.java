package com.neptune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Neptune on 3/18/2017.
 */
public class State {
    public Mark[][] board;
    private Stack<Move> moveHistory;

    public State() {
        board = new Mark[Rule.SIZE][Rule.SIZE];
        for (Mark[] marks : board) {
            Arrays.fill(marks, Mark.BLANK);
        }
        moveHistory = new Stack<>();
    }

    public void performMove(int row, int col) {
        if (board[row][col] != Mark.BLANK) return;
        board[row][col] = getCurrentPlayer();
        moveHistory.push(new Move(row, col));
    }

    public Mark getCurrentPlayer() {
        return moveHistory.size() % 2 == 0 ? Mark.MIN : Mark.MAX;
    }

    public Mark getPlayer(int row, int col) {
        Move move = new Move(row, col);
        for (int i = 0; i < moveHistory.size(); i++) {
            if (moveHistory.get(i).equals(move)) {
                return i % 2 == 0 ? Mark.MIN : Mark.MAX;
            }
        }
        return null;
    }

    public Move getLastMove() {
        if (moveHistory.isEmpty())
            return null;
        return moveHistory.peek();
    }

    public void undoLastMove() {
        Move move = moveHistory.pop();
        board[move.row][move.col] = Mark.BLANK;
    }

    public boolean isValidMove(Move move) {
        return board[move.row][move.col] == Mark.BLANK;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                if (board[i][j] == Mark.BLANK) {
                    output.append("[ ]");
                } else if (board[i][j] == Mark.MAX) {
                    output.append("[X]");
                } else {
                    output.append("[O]");
                }

                output.append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }

    public HashMap<Move, Integer> getMoveSuccessors() {
        HashMap<Move, Integer> moveMap = new HashMap<>();
        if (moveHistory.isEmpty()) {
            moveMap.put(new Move(5, 5), 0);
            return moveMap;
        }
        for (Move move : moveHistory) {
            ArrayList<Move> generatedMoves = move.generateMove(Rule.RADIUS);
            for (Move generatedMove : generatedMoves) {
                if (isValidMove(generatedMove)) {
                    moveMap.put(generatedMove, 0);
                }
            }
        }
        return moveMap;
    }

    public boolean canFiveInARow(int index) {
        return index + Rule.WIN_REQUIRED <= Rule.SIZE;
    }

    public GameState checkState() {
        if (moveHistory.size() == Math.pow(Rule.SIZE, 2)) return GameState.DRAW;
        Move lastMove = getLastMove();
        Mark currentPlayer = getCurrentPlayer();
        if (hasHorizontalWin(lastMove, currentPlayer) || hasVerticalWin(lastMove, currentPlayer) || hasDiagonalPrimaryWin(lastMove, currentPlayer) || hasDiagonalSubWin(lastMove, currentPlayer)) {
            return currentPlayer == Mark.MAX ? GameState.MAX_WIN : GameState.MIN_WIN;
        }
        return GameState.ON_GOING;
    }

    public boolean hasHorizontalWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.col + i)) break;
            if (board[move.row][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.col - i)) break;
            if (board[move.row][move.col - i] == player)
                count = count + 1;
            else break;
        }
        return count == Rule.WIN_REQUIRED;
    }

    public boolean hasVerticalWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i)) break;
            if (board[move.row + i][move.col] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i)) break;
            if (board[move.row - i][move.col] == player)
                count = count + 1;
            else break;
        }
        return count == Rule.WIN_REQUIRED;
    }

    public boolean hasDiagonalPrimaryWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i) || isOutOfRange(move.col + i)) break;
            if (board[move.row + i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i) || isOutOfRange(move.col - i)) break;
            if (board[move.row - i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        return count == Rule.WIN_REQUIRED;
    }

    public boolean hasDiagonalSubWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i) || isOutOfRange(move.col - i)) break;
            if (board[move.row + i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i) || isOutOfRange(move.col + i)) break;
            if (board[move.row - i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        return count == Rule.WIN_REQUIRED;
    }

    public boolean isOutOfRange(int i) {
        return i < 0 || i >= Rule.SIZE;
    }
}

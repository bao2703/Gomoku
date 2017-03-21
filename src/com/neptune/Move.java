package com.neptune;

import java.util.ArrayList;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Move {
    public int row;
    public int col;

    public Move() {
        this(0, 0);
    }

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Move(Move move) {
        this(move.row, move.col);
    }

    @Override
    public String toString() {
        return row + " " + col;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;
        return col == other.col && row == other.row;
    }

    public boolean isValid() {
        return !(row < 0 || row >= Rule.SIZE || col < 0 || col >= Rule.SIZE);
    }

    public ArrayList<Move> generateMove(int radius) {
        if (!this.isValid()) {
            return null;
        }
        ArrayList<Move> result = new ArrayList<>();
        for (int i = -radius; i <= radius; i++) {
            for (int j = -radius; j <= radius; j++) {
                Move move = new Move(row + i, col + j);
                if (move.isValid())
                    result.add(move);
            }
        }
        return result;
    }
}

package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Move {
    public int row;
    public int col;

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
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Move other = (Move) obj;
        if (this.row != other.row) return false;
        if (this.col != other.col) return false;
        return true;
    }
}

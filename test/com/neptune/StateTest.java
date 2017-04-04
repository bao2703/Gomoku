package com.neptune;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Neptune on 3/18/2017.
 */
class StateTest {
    State state;

    @BeforeEach
    void setUp() {
        state = new State();
    }

    @Test
    void performMove() {
        state.performMove(new Move(5, 5));
        Assertions.assertEquals(state.getPlayer(5, 5), state.board[5][5]);
    }

    @Test
    void getCurrentPlayer_MIN_first() {
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.MIN, actual);
    }

    @Test
    void getCurrentPlayer_MIN_first_MAX_second() {
        state.performMove(new Move(0, 0));
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.MAX, actual);
    }

    @Test
    public void canFiveInARow() {
        int index = 7;
        for (int i = 0; i < 7; i++) {
            assertEquals(true, state.canFiveInARow(i));
        }
        for (int i = index; i < Rule.SIZE; i++) {
            assertEquals(false, state.canFiveInARow(i));
        }
    }

    @Test
    void getMoveSuccessors() {
        Rule.RADIUS = 2;
        state.performMove(new Move(5, 5));
        assertEquals(24, state.getMoveSuccessors().size());

        state.performMove(new Move(5, 4));
        assertEquals(28, state.getMoveSuccessors().size());

        state.performMove(new Move(5, 3));
        assertEquals(32, state.getMoveSuccessors().size());

        state.performMove(new Move(4, 5));
        assertEquals(36, state.getMoveSuccessors().size());
    }

    @Test
    void hasWinnerHorizontal() {
        state.board[0][1] = Mark.MAX;
        state.board[0][2] = Mark.MAX;
        state.board[0][3] = Mark.MAX;
        state.board[0][4] = Mark.MAX;
        state.board[0][5] = Mark.MAX;

        assertEquals(true, state.hasHorizontalWin(new Move(0, 1), Mark.MAX));
        assertEquals(true, state.hasHorizontalWin(new Move(0, 2), Mark.MAX));
        assertEquals(true, state.hasHorizontalWin(new Move(0, 3), Mark.MAX));
        assertEquals(true, state.hasHorizontalWin(new Move(0, 4), Mark.MAX));
        assertEquals(true, state.hasHorizontalWin(new Move(0, 5), Mark.MAX));
    }

    @Test
    void hasVerticalWin() {
        state.board[1][0] = Mark.MAX;
        state.board[2][0] = Mark.MAX;
        state.board[3][0] = Mark.MAX;
        state.board[4][0] = Mark.MAX;
        state.board[5][0] = Mark.MAX;

        assertEquals(true, state.hasVerticalWin(new Move(1, 0), Mark.MAX));
        assertEquals(true, state.hasVerticalWin(new Move(2, 0), Mark.MAX));
        assertEquals(true, state.hasVerticalWin(new Move(3, 0), Mark.MAX));
        assertEquals(true, state.hasVerticalWin(new Move(4, 0), Mark.MAX));
        assertEquals(true, state.hasVerticalWin(new Move(5, 0), Mark.MAX));
    }

    @Test
    void hasDiagonalPrimaryWin() {
        state.board[1][1] = Mark.MAX;
        state.board[2][2] = Mark.MAX;
        state.board[3][3] = Mark.MAX;
        state.board[4][4] = Mark.MAX;
        state.board[5][5] = Mark.MAX;

        assertEquals(true, state.hasDiagonalPrimaryWin(new Move(1, 1), Mark.MAX));
        assertEquals(true, state.hasDiagonalPrimaryWin(new Move(2, 2), Mark.MAX));
        assertEquals(true, state.hasDiagonalPrimaryWin(new Move(3, 3), Mark.MAX));
        assertEquals(true, state.hasDiagonalPrimaryWin(new Move(4, 4), Mark.MAX));
        assertEquals(true, state.hasDiagonalPrimaryWin(new Move(5, 5), Mark.MAX));
    }

    @Test
    void hasDiagonalSubWin() {
        state.board[4][6] = Mark.MAX;
        state.board[5][5] = Mark.MAX;
        state.board[6][4] = Mark.MAX;
        state.board[7][3] = Mark.MAX;
        state.board[8][2] = Mark.MAX;

        assertEquals(true, state.hasDiagonalSubWin(new Move(4, 6), Mark.MAX));
        assertEquals(true, state.hasDiagonalSubWin(new Move(5, 5), Mark.MAX));
        assertEquals(true, state.hasDiagonalSubWin(new Move(6, 4), Mark.MAX));
        assertEquals(true, state.hasDiagonalSubWin(new Move(7, 3), Mark.MAX));
        assertEquals(true, state.hasDiagonalSubWin(new Move(8, 2), Mark.MAX));
    }
}
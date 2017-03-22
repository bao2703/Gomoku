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
        state.performMove(5, 5);
        Assertions.assertEquals(state.getPlayer(5, 5), state.board[5][5]);
    }

    @Test
    void getCurrentPlayer_O_first() {
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.MIN, actual);
    }

    @Test
    void getCurrentPlayer_O_first_X_seccond() {
        state.performMove(0, 0);
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
        state.performMove(5, 5);
        assertEquals(24, state.getMoveSuccessors().size());

        state.performMove(5, 4);
        assertEquals(28, state.getMoveSuccessors().size());

        state.performMove(5, 3);
        assertEquals(32, state.getMoveSuccessors().size());

        state.performMove(4, 5);
        assertEquals(36, state.getMoveSuccessors().size());
    }

    @Test
    void a() {
        state.board[0][1] = Mark.MAX;
        state.board[0][2] = Mark.MAX;
        state.board[0][3] = Mark.MAX;
        state.board[0][4] = Mark.MAX;
        state.board[0][5] = Mark.MAX;

        assertEquals(true, state.a(new Move(0, 1), Mark.MAX));
    }
}
package com.neptune;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void getSuccessors_1_in_first_move() {
        ArrayList<State> successors = state.getSuccessors();
        assertEquals(1, successors.size());
    }

    @Test
    void getSuccessors_24_in_second_move() {
        ArrayList<State> successors = state.getSuccessors();
        successors = successors.get(0).getSuccessors();
        assertEquals(24, successors.size());
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
        state.performMove(Rule.SIZE / 2, Rule.SIZE / 2);
        assertEquals(24, state.getMoveSuccessors().size());

        state.performMove(5, 4);
        assertEquals(28, state.getMoveSuccessors().size());

        state.performMove(5, 3);
        assertEquals(32, state.getMoveSuccessors().size());

        state.performMove(4, 5);
        assertEquals(36, state.getMoveSuccessors().size());
    }
}
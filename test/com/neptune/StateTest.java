package com.neptune;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertEquals(state.getPlayer(5,5), state.board[5][5]);
    }

    @Test
    void getCurrentPlayer_O_first() {
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.O, actual);
    }

    @Test
    void getCurrentPlayer_O_first_X_seccond() {
        state.performMove(0, 0);
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.X, actual);
    }
}
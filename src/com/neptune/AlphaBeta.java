package com.neptune;

import java.util.ArrayList;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {

    public static int alphaBeta(State currentState, int alpha, int beta, int depth) {
        ArrayList<State> successors = currentState.getSuccessors();
        if (successors.isEmpty() || depth == 0) {
            return currentState.getHeuristic();
        }
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.X) {
            bestValue = alpha;
            for (State successor : successors) {
                bestValue = Math.max(bestValue, alphaBeta(successor, bestValue, beta, depth - 1));
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (State successor : successors) {
                bestValue = Math.min(bestValue, alphaBeta(successor, alpha, bestValue, depth - 1));
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}

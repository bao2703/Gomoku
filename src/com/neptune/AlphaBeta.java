package com.neptune;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {
    private Evaluation evaluation;
    private ArrayList<HashMap<Integer, Move>> trackingMove;

    public AlphaBeta() {
        evaluation = new Evaluation();
        trackingMove = new ArrayList<>();
    }

    public Move exec(State currentState, int depth) {
        int value = exec(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        return trackingMove.get(0).get(value);
    }

    private int exec(State currentState, int alpha, int beta, int depth) {
        if (depth == 0) {
            return evaluation.computeHeuristic(currentState);
        }
        ArrayList<State> successors = currentState.getSuccessors();
        trackingMove.add(new HashMap<>());
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.MAX) {
            bestValue = alpha;
            for (State successor : successors) {
                int childValue = exec(successor, bestValue, beta, depth - 1);
                if (childValue > bestValue) {
                    bestValue = childValue;
                    trackingMove.get(depth - 1).put(bestValue, successor.getLastMove());
                }
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (State successor : successors) {
                int childValue = exec(successor, alpha, bestValue, depth - 1);
                if (childValue < bestValue) {
                    bestValue = childValue;
                    trackingMove.get(depth - 1).put(bestValue, successor.getLastMove());
                }
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}

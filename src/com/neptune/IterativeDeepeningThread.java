package com.neptune;

/**
 * Created by Neptune on 3/22/2017.
 */
public class IterativeDeepeningThread extends Thread {
    private GomokuFrame gomokuFrame;
    private AlphaBeta alphaBeta = new AlphaBeta();

    public IterativeDeepeningThread(GomokuFrame gomokuFrame) {
        this.gomokuFrame = gomokuFrame;
    }

    @Override
    public void run() {
        gomokuFrame.setPlayerTurn(false);
        Move aiMove = alphaBeta.exec(gomokuFrame.state, Rule.MAX_DEPTH);
        gomokuFrame.performMove(aiMove);
        gomokuFrame.setPlayerTurn(true);
    }
}

package com.neptune;

/**
 * Created by Neptune on 3/22/2017.
 */
public class IterativeDeepeningThread extends Thread {
    private GomokuFrame gomokuFrame;
    private int timeLimit;

    public IterativeDeepeningThread(GomokuFrame gomokuFrame, int timeLimit) {
        this.gomokuFrame = gomokuFrame;
        this.timeLimit = timeLimit;
    }

    @Override
    public void run() {
        gomokuFrame.setPlayerTurn(false);
        gomokuFrame.aiTurn();
    }
}

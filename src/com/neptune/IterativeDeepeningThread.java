package com.neptune;

/**
 * Created by Neptune on 3/22/2017.
 */
public class IterativeDeepeningThread extends Thread {
    private GomokuFrame gomokuFrame;

    public IterativeDeepeningThread(GomokuFrame gomokuFrame) {
        this.gomokuFrame = gomokuFrame;
    }

    @Override
    public void run() {
        gomokuFrame.setPlayerTurn(false);
        gomokuFrame.aiTurn();
    }
}

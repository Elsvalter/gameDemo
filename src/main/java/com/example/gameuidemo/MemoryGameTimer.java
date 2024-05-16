package com.example.gameuidemo;

import java.util.TimerTask;

public class MemoryGameTimer extends TimerTask {
    private int timePeriod;
    private MemoryGameScene obj;

    public MemoryGameTimer(int timePeriod, MemoryGameScene obj) {
        this.timePeriod = timePeriod;
        this.obj = obj;
    }

    @Override
    public void run() {
        if (timePeriod == 0) {
            synchronized (obj) {
                obj.notify();
            }
        }
        timePeriod--;
    }
}

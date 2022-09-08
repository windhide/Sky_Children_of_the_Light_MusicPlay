package com.windhide;


import com.windhide.runnable.FrameRunnable;

public class Start {
    public static void main(String[] args) {
        FrameRunnable frameRunnable = new FrameRunnable();
        Thread mainThread = new Thread(frameRunnable);
        mainThread.start();
    }
}

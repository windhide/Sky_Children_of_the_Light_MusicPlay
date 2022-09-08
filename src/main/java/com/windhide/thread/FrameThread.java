package com.windhide.thread;

import com.windhide.frame.MainFrame;

public class FrameThread extends Thread {
    @Override
    public void start() {
        new MainFrame();
    }
}

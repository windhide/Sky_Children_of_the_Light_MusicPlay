package com.windhide.runnable;

import com.windhide.frame.MainFrame;

public class FrameRunnable implements Runnable {
    @Override
    public void run() {
        new MainFrame();
    }
}

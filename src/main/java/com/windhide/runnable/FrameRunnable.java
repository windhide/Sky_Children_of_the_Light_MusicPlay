package com.windhide.runnable;

import com.windhide.frame.MainFrame;
import com.windhide.util.StaticUtil;

public class FrameRunnable implements Runnable {
    @Override
    public void run() {
        StaticUtil.mainFrame = new MainFrame();
    }
}

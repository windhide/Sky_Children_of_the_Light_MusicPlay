package com.windhide;


import com.windhide.thread.FrameThread;
import com.windhide.thread.PlayThread;
import com.windhide.util.StaticUtil;

public class Start {
    public static void main(String[] args) {
        Thread frame = new FrameThread();
        StaticUtil.playThread = new PlayThread();
        frame.start();
        StaticUtil.playThread.start();
    }
}

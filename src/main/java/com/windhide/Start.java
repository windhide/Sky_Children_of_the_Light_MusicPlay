package com.windhide;


import com.windhide.runnable.FrameRunnable;
import com.windhide.util.JarLoaderUtil;

public class Start {
    public static void main(String[] args) {
        new JarLoaderUtil().setIconImage(); // 设置一下图标

        FrameRunnable frameRunnable = new FrameRunnable();
        Thread mainThread = new Thread(frameRunnable);
        mainThread.start();
    }
}

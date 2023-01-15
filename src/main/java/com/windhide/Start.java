package com.windhide;


import com.windhide.runnable.FrameRunnable;
import com.windhide.util.JarLoaderUtil;
import com.windhide.util.PlayUtil;
import com.windhide.util.StaticUtil;
import com.windhide.util.TextMusicScoreUtil;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {
        JarLoaderUtil jarLoaderUtil = new JarLoaderUtil();
        jarLoaderUtil.setIconImage();
        jarLoaderUtil.setFileNameList();
        StaticUtil.isSystemPlay = true; // 初始化
        // 初始化文件列表
        FrameRunnable frameRunnable = new FrameRunnable();
        Thread mainThread = new Thread(frameRunnable);
        mainThread.start();
        TextMusicScoreUtil.getKeyTapInCache();
    }
}

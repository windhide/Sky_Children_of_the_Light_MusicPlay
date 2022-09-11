package com.windhide.runnable;


import com.windhide.util.PlayUtil;
import com.windhide.util.StaticUtil;

import javax.swing.*;

public class PlayRunnable implements Runnable {

    private String musicName = "";


    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @Override
    public void run() {
        try {
            PlayUtil.textMusicPlay(musicName, StaticUtil.keyTap);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "请先设置按键！！", "错误", JOptionPane.WARNING_MESSAGE);
            StaticUtil.playThread.stop();
            // 如果出错直接终止线程
        }
    }
}

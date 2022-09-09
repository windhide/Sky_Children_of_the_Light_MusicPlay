package com.windhide.runnable;

import com.windhide.entity.Music;
import com.windhide.util.StaticUtil;

import java.util.List;

public class PlayBarRunnable implements Runnable {

    @Override
    public void run() {
        List<Music> nowPlayMusic = StaticUtil.nowPlayMusic;
        Long endTime = nowPlayMusic.get(nowPlayMusic.size()).getDelay();
        if (nowPlayMusic != null) {
            for (int i = 0; i < nowPlayMusic.size(); i++) {
                setPlayBar(nowPlayMusic.get(i).getDelay(), endTime);
            }
        }
    }

    public void setPlayBar(Long startTime, Long endTime) {
        System.out.println(Math.round(startTime / endTime));
        StaticUtil.mainFrame.playBar.setValue(Math.round(startTime / endTime));
    }
}

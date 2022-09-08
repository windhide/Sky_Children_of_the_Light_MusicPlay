package com.windhide.runnable;


import com.windhide.util.PlayUtil;
import com.windhide.util.StaticUtil;

public class PlayRunnable implements Runnable {

    private String musicName = "";


    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    @Override
    public void run() {
        PlayUtil.textMusicPlay(musicName, StaticUtil.keyTap);
    }
}

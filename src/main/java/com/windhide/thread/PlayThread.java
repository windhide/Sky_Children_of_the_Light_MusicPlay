package com.windhide.thread;


import com.windhide.util.PlayUtil;
import com.windhide.util.StaticUtil;

import java.util.Objects;

public class PlayThread extends Thread {

    private String musicName = "";
    private boolean lock = true;

    @Override
    public void start() {
        while (lock) {
            System.out.println();
            if (!Objects.equals(musicName, "") && musicName != null) {
                try {
                    lock = false;
                    PlayUtil.textMusicPlay(musicName, StaticUtil.keyTap);
                    musicName = "";
                    lock = true;
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

}

package com.windhide.runnable;

import com.windhide.util.StaticUtil;

public class PlayBarRunnable implements Runnable {

    @Override
    public void run() {
        Long endTime = StaticUtil.nowPlayMusic.get(StaticUtil.nowPlayMusic.size() - 1).getDelay();
        while (StaticUtil.nowPlayMusic != null) {
            System.out.println(StaticUtil.nowPlayTime + "/" + endTime);
            setPlayBar(StaticUtil.nowPlayTime, endTime);
        }
    }

    public void setPlayBar(Long startTime, Long endTime) {
        Double start = Double.valueOf(startTime);
        Double end = Double.valueOf(endTime);
        StaticUtil.mainFrame.playBar.setValue((int) Math.round((start / end) * 100));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

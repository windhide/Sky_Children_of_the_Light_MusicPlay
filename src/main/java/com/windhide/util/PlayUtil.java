package com.windhide.util;

import com.windhide.entity.Music;
import com.windhide.entity.MusicType.TextMusicNotes;
import com.windhide.entity.Tap.KeyTap;
import com.windhide.runnable.PlayBarRunnable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 播放形式工具类
 */
public class PlayUtil {

    /**
     * jar包模式使用的方法
     *
     * @param musicName
     * @param keyTap
     * @throws AWTException
     */
    public static void textMusicPlay(String musicName, KeyTap keyTap) throws AWTException, InterruptedException {
        Robot robot = null;
        robot = new Robot();
        TapTransforUtil tapTransforUtil = new TapTransforUtil(keyTap);
        HashMap<String, String> hashMap = tapTransforUtil.tapMap;
        TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
        List<TextMusicNotes> songNotes = textMusicScoreUtil.getFileNameList(musicName).getSongNotes();
        List<Music> music = new ArrayList<>();
        for (int i = 0; i < songNotes.size(); i++) {
            Music tempMusic = new Music();
            int lamdaI = i;
            if (music.stream().anyMatch(temp -> Objects.equals(temp.getDelay(), songNotes.get(lamdaI).getTime()))) {
                String tempKey = music.get(i - 1).getTap();
                music.get(i - 1).setTap(tempKey + hashMap.get(songNotes.get(i).getKey()));
                music.get(i - 1).setDelay(music.get(i - 1).getDelay());
                songNotes.remove(i);
                --i;
            } else {
                tempMusic.setTap(hashMap.get(songNotes.get(i).getKey()));
                tempMusic.setDelay(songNotes.get(i).getTime());
                music.add(tempMusic);
            }
        }

        PlayBarRunnable playBarRunnable = null;
        if (StaticUtil.playBarRunnable == null) {
            playBarRunnable = new PlayBarRunnable();
            StaticUtil.playBarRunnable = playBarRunnable;
        }
        StaticUtil.nowPlayMusic = music;

        // 这里设置窗口的歌名显示
        StaticUtil.mainFrame.playBarMusicName.setText(musicName.replaceAll(".txt", ""));

        StaticUtil.playBarThread = new Thread(StaticUtil.playBarRunnable);
        StaticUtil.nowPlayTime = music.get(0).getDelay(); // 提前放置，避免null

        // 设置成3秒，给用户操作机会
        Thread.sleep(3000);

        StaticUtil.playBarThread.start();

        for (int i = 0; i < music.size(); i++) {
            String s = music.get(i).getTap().toUpperCase();
            if (s.length() > 1) {
                for (int inString = 0; inString < s.length(); inString++) {
                    robot.keyPress(s.charAt(inString));
                    robot.keyRelease(s.charAt(inString));
                }
            } else {
                robot.keyPress(s.charAt(0));
                robot.keyRelease(s.charAt(0));
            }
            StaticUtil.nowPlayTime = music.get(i).getDelay();
//            System.out.println("Press \t ->" + s);
            try {
                long delay = music.get(i + 1).getDelay() - music.get(i).getDelay();
                Thread.sleep(delay);
            } catch (Exception e) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        StaticUtil.playBarRunnable = null; // 线程归零;
        StaticUtil.nowPlayMusic = null; // 停止进度条
        StaticUtil.playThread = null; // 清除歌曲播放
        StaticUtil.playRunnable = null; // 清除歌曲Runnable
        StaticUtil.mainFrame.playBar.setValue(100);
        StaticUtil.mainFrame.playBarMusicName.setText("歌曲播放完毕");
        StaticUtil.playBarThread.stop(); // 终止线程
    }

    /**
     * 调试模式使用的方法
     * @param musicName
     * @param keyTap
     * @throws AWTException
     */
//    public static void textMusicPlay(String musicName, KeyTap keyTap) throws AWTException {
//        Robot robot = null;
//        robot = new Robot();
//        TapTransforUtil tapTransforUtil = new TapTransforUtil(keyTap);
//        HashMap<String, String> hashMap = tapTransforUtil.tapMap;
//        TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
//        List<TextMusicNotes> songNotes = textMusicScoreUtil.getFileNameList(musicName).getSongNotes();
//        List<Music> music = new ArrayList<>();
//        for (int i = 0; i < songNotes.size(); i++) {
//            Music tempMusic = new Music();
//            int lamdaI = i;
//            if (music.stream().anyMatch(temp -> Objects.equals(temp.getDelay(), songNotes.get(lamdaI).getTime()))) {
//                String tempKey = music.get(i - 1).getTap();
//                music.get(i - 1).setTap(tempKey + hashMap.get(songNotes.get(i).getKey()));
//                music.get(i - 1).setDelay(music.get(i - 1).getDelay());
//                songNotes.remove(i);
//                --i;
//            } else {
//                tempMusic.setTap(hashMap.get(songNotes.get(i).getKey()));
//                tempMusic.setDelay(songNotes.get(i).getTime());
//                music.add(tempMusic);
//            }
//        }
//
//        PlayBarRunnable playBarRunnable = null;
//        if (StaticUtil.playBarRunnable == null) {
//            playBarRunnable = new PlayBarRunnable();
//            StaticUtil.playBarRunnable = playBarRunnable;
//        }
//        StaticUtil.nowPlayMusic = music;
//
//        // 这里设置窗口的歌名显示
//        StaticUtil.mainFrame.playBarMusicName.setText(musicName.replaceAll(".txt", ""));
//
//        StaticUtil.playBarThread = new Thread(StaticUtil.playBarRunnable);
//        StaticUtil.nowPlayTime = music.get(0).getDelay(); // 提前放置，避免null
//        StaticUtil.playBarThread.start();
//
//        for (int i = 0; i < music.size(); i++) {
//            String s = music.get(i).getTap().toUpperCase();
//            if (s.length() > 1) {
//                for (int inString = 0; inString < s.length(); inString++) {
//                    robot.keyPress(s.charAt(inString));
//                    robot.keyRelease(s.charAt(inString));
//                }
//            } else {
//                robot.keyPress(s.charAt(0));
//                robot.keyRelease(s.charAt(0));
//            }
//            StaticUtil.nowPlayTime = music.get(i).getDelay();
////            System.out.println("Press \t ->" + s);
//            try {
//                long delay = music.get(i + 1).getDelay() - music.get(i).getDelay();
//                Thread.sleep(delay);
//            } catch (Exception e) {
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }
//        StaticUtil.playBarThread.stop(); // 终止线程
//        StaticUtil.playBarRunnable = null; // 线程归零;
//        StaticUtil.nowPlayMusic = null; // 停止进度条
//    }
}
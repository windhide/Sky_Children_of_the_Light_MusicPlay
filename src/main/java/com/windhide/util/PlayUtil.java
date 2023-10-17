package com.windhide.util;

import com.windhide.entity.Music;
import com.windhide.entity.MusicType.TextMusicNotes;
import com.windhide.entity.Tap.KeyTap;
import com.windhide.runnable.PlayBarRunnable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

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
        StaticUtil.musicPlayMaxIndex = music.size();
        for (StaticUtil.musicPlayIndex = 0; StaticUtil.musicPlayIndex < music.size(); StaticUtil.musicPlayIndex++) {
            String s = music.get(StaticUtil.musicPlayIndex).getTap().toUpperCase();
            playCore(robot, music, s);
            try {
                long delay = music.get(StaticUtil.musicPlayIndex + 1).getDelay() - music.get(StaticUtil.musicPlayIndex).getDelay();
                robot.delay((int) delay);
            } catch (Exception e) {
                    Thread.sleep(100);
                    robot.delay(100);

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
    public static void textMusicPlay(String musicName, KeyTap keyTap, boolean isOut) throws AWTException {
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
        StaticUtil.playBarThread.start();
        StaticUtil.musicPlayMaxIndex = music.size();
        for (StaticUtil.musicPlayIndex = 0; StaticUtil.musicPlayIndex < music.size(); StaticUtil.musicPlayIndex++) {
            String s = music.get(StaticUtil.musicPlayIndex).getTap().toLowerCase(Locale.ROOT);
            playCore(robot, music, s);
            try {
                long delay = music.get(StaticUtil.musicPlayIndex + 1).getDelay() - music.get(StaticUtil.musicPlayIndex).getDelay();
                robot.delay((int) delay);
            } catch (Exception e) {
                    robot.delay(100);
            }
        }
        StaticUtil.playBarThread.stop(); // 终止线程
        StaticUtil.playBarRunnable = null; // 线程归零;
        StaticUtil.nowPlayMusic = null; // 停止进度条
    }

    public static void playCore(Robot robot, List<Music> music, String s) {
        s = s.toLowerCase();
        if (s.length() > 1) {
            for (int inString = 0; inString < s.length(); inString++) {
                robot.keyPress(EventKeyUtil.EventKeyTransfor(s.charAt(inString)+""));
                robot.delay(StaticUtil.isRandom?randomDelay():StaticUtil.delay);
                robot.keyRelease(EventKeyUtil.EventKeyTransfor(s.charAt(inString)+""));
            }
        } else {
            robot.keyPress(EventKeyUtil.EventKeyTransfor(s.charAt(0)+""));
            robot.delay(StaticUtil.isRandom?randomDelay():StaticUtil.delay);
            robot.keyRelease(EventKeyUtil.EventKeyTransfor(s.charAt(0)+""));
        }
        StaticUtil.nowPlayTime = music.get(StaticUtil.musicPlayIndex).getDelay();
        System.out.println("Press \t ->" + s);
    }

    private static Integer randomDelay(){
        Random random = new Random();
        return random.nextInt(11)+10;
    }
}
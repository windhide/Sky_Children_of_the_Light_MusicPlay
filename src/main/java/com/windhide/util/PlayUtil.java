package com.windhide.util;

import com.windhide.entity.Music;
import com.windhide.entity.MusicType.TextMusicNotes;
import com.windhide.entity.Tap.KeyTap;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 播放形式工具类
 */
public class PlayUtil {

    public static void textMusicPlay(String musicName, KeyTap keyTap) throws AWTException {
        Robot robot = null;
        robot = new Robot();
        TapTransforUtil tapTransforUtil = new TapTransforUtil(keyTap);
        HashMap<String, String> hashMap = tapTransforUtil.tapMap;
        List<String> list = MusicScoreUtil.Music1.musicMapOrder(keyTap);

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
            System.out.println("Press \t ->" + s);
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
    }
}

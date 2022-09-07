package com.windhide.util;

import com.windhide.entity.Music;
import com.windhide.entity.MusicType.TextMusic;
import com.windhide.entity.MusicType.TextMusicNotes;
import com.windhide.entity.Tap.KeyTap;
import com.windhide.test.TestMain;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 播放形式工具类
 */
public class PlayUtil {

    public void textMusicPlay(TextMusic textMusic) {


    }


    public static void main(String[] args) throws InterruptedException, AWTException {
        Robot robot = new Robot();
//        Thread.sleep(3000);
        KeyTap keyTapUtil = new KeyTap();
        keyTapUtil.Do = "y";
        keyTapUtil.Re = "u";
        keyTapUtil.Mi = "i";
        keyTapUtil.Fa = "o";
        keyTapUtil.So = "p";
        keyTapUtil.La = "h";
        keyTapUtil.Xi = "j";
        keyTapUtil.H_Do = "k";
        keyTapUtil.H_Re = "l";
        keyTapUtil.H_Mi = ";";
        keyTapUtil.H_Fa = "n";
        keyTapUtil.H_So = "m";
        keyTapUtil.H_La = ",";
        keyTapUtil.H_Xi = ".";
        keyTapUtil.HH_Do = "/";

        TapTransforUtil tapTransforUtil = new TapTransforUtil(keyTapUtil);
        HashMap<String, String> hashMap = tapTransforUtil.tapMap;

        List<String> list = MusicScoreUtil.Music1.musicMapOrder(keyTapUtil);

        TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
        List<TextMusicNotes> songNotes = textMusicScoreUtil.getFileNameList("所念皆星河.txt").getSongNotes();
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
                Thread.sleep(100);
            }
        }
        System.out.println("done!");
    }
}

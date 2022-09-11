package com.windhide.util;


import com.alibaba.fastjson.JSON;
import com.windhide.entity.MusicType.TextMusic;
import com.windhide.runnable.PlayRunnable;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class TextMusicScoreUtil {

    String[] EncodingList = new String[]{"UTF-8", "UTF-16", "GBK", "ANSI"};
    List<String> FileNameList = StaticUtil.fileNameList;

    public List<String> FileNameList() {
        return FileNameList;
    }

    public TextMusic getTextMusicForList(String fileName, String Encoding) {
        // 文件路径处理
        URL url = TextMusicScoreUtil.class.getResource("/MusicScore/" + fileName);
        BufferedReader reader = null;
        String tempStr = "";
        StringBuilder stringBuffer = new StringBuilder();
        // 读取文件内容
        try {
            InputStream input = url.openStream();
            InputStreamReader read = new InputStreamReader(input, Encoding);
            reader = new BufferedReader(read);
            while ((tempStr = reader.readLine()) != null) {
                stringBuffer.append(tempStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //处理掉文件当中不合理的[];
        String data = stringBuffer.toString();
        data = data.substring(0, data.length() - 1).replaceFirst("\\[", "");
        ;

        return JSON.parseObject(data, TextMusic.class);
    }

    public TextMusic getFileNameList(String fileName) {
        //用来匹配正确的编码格式
        for (String s : EncodingList) {
            TextMusic textMusic = null;
            try {
                textMusic = getTextMusicForList(fileName, s);
                System.out.println(fileName);
            } catch (Exception e) {
                // 只为了catch
            }
            if (textMusic != null) {
                return textMusic;
            }
        }
        return null;
    }

    public void playTextMusic(String musicText) {
        PlayRunnable playRunnable = null;
        Thread playThread = null;
        if (StaticUtil.playRunnable == null) {
            playRunnable = new PlayRunnable();
            StaticUtil.playRunnable = playRunnable;
        }
        StaticUtil.playRunnable.setMusicName(musicText);
        playThread = new Thread(StaticUtil.playRunnable);
        StaticUtil.playThread = playThread;
        StaticUtil.playThread.start();

    }

}

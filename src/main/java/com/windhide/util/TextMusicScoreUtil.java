package com.windhide.util;


import com.alibaba.fastjson.JSON;
import com.windhide.Start;
import com.windhide.entity.MusicType.TextMusic;
import com.windhide.entity.Tap.KeyTap;
import com.windhide.runnable.PlayRunnable;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class TextMusicScoreUtil {

    static String keyTapFileName = "KeyboardMapping.json";
    String[] EncodingList = new String[]{"UTF-8", "UTF-16", "GBK", "ANSI"};
    List<String> FileNameList = StaticUtil.fileNameList;

    public List<String> FileNameList() {
        return FileNameList;
    }

    public TextMusic getTextMusicForList(String fileName, String Encoding) throws MalformedURLException {
        // 文件路径处理
        URL url = null;
        if(StaticUtil.isSystemPlay)
            url = TextMusicScoreUtil.class.getResource("/MusicScore/" + fileName);
        else
            url = new URL("file:\\"+fileName);
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

    /**
     * 调试模式使用的方法
     *
     * @param fileName
     * @return
     */
//    public TextMusic getFileNameList(String fileName) {
//        //用来匹配正确的编码格式
//        for (String s : EncodingList) {
//            TextMusic textMusic = null;
//            try {
//                textMusic = getTextMusicForList(fileName, s);
//                System.out.println(fileName);
//            } catch (Exception e) {
//                // 只为了catch
//            }
//            if (textMusic != null) {
//                return textMusic;
//            }
//        }
//        return null;
//    }
    public TextMusic getFileNameList(String fileName) {

        //用来匹配正确的编码格式
        for (String s : EncodingList) {
            TextMusic textMusic = null;
            try {
                textMusic = getTextMusicForList(fileName, s);
                System.out.println(fileName);
            } catch (Exception e) {
                // 只为了catch 妹妹的芝士很有韵味
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
            StaticUtil.playRunnable.setMusicName(musicText);
            playThread = new Thread(StaticUtil.playRunnable);
            StaticUtil.playThread = playThread;
            StaticUtil.playThread.start();
        } else {
            JOptionPane.showMessageDialog(null, "已有正在播放的歌曲，请先停止！", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * 加载键盘缓存
     */
    public static void getKeyTapInCache() {

        File file = new File(getJarNowPath());
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuilder KeyTapJson = new StringBuilder();
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                KeyTapJson.append(tempString);
            }
            KeyTap keyTap = JSON.parseObject(KeyTapJson.toString(), KeyTap.class);
            if (keyTap != null) {
                StaticUtil.keyTap = keyTap;
                JOptionPane.showMessageDialog(null, "历史按键加载成功！", "芜湖~", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 放置缓存
     */
    public static void putKeyTapInCache(KeyTap keyTap) throws IOException {
        String cacheData = JSON.toJSONString(keyTap);

        FileWriter fw = null;
        try {
            File file = new File(getJarNowPath());
            if (!file.exists()) {
                file.createNewFile();
            }else{
                file.delete();
                file.createNewFile();
            }
            fw = new FileWriter(getJarNowPath());
            fw.write(cacheData);
            JOptionPane.showMessageDialog(null, "软件会在目录下生成键盘的配置文件，这样下次运行就不用重新设置啦~", "芜湖~", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 拿到jar包当前路径
     *
     * @return
     */
    public static String getJarNowPath() {
        String path = Start.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (System.getProperty("os.name").contains("dows")) {
            path = path.substring(1, path.length());
        }
        if (path.contains("jar")) {
            path = path.substring(0, path.lastIndexOf(".")).substring(0, path.lastIndexOf("/")).replace("target/classes/", "") + "/" + keyTapFileName;
        }
        return path;
    }
}

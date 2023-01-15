package com.windhide.util;

import com.windhide.entity.Music;
import com.windhide.entity.Tap.KeyTap;
import com.windhide.frame.MainFrame;
import com.windhide.runnable.PlayBarRunnable;
import com.windhide.runnable.PlayRunnable;

import java.awt.*;
import java.util.List;

public class StaticUtil {
    public static KeyTap keyTap;
    public static PlayRunnable playRunnable;
    public static Thread playThread;
    public static MainFrame mainFrame;

    public static PlayBarRunnable playBarRunnable;
    public static Thread playBarThread;

    public static List<Music> nowPlayMusic;
    public static Long nowPlayTime;

    public static Image iconImage;

    public static List<String> fileNameList;

    public static boolean isSystemPlay;
}

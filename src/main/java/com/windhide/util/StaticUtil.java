package com.windhide.util;

import com.windhide.entity.Tap.KeyTap;
import com.windhide.frame.MainFrame;
import com.windhide.runnable.PlayRunnable;

public class StaticUtil {
    public static KeyTap keyTap;
    public static PlayRunnable playRunnable;
    public static Thread playThread;
    public static MainFrame mainFrame;

    public static long nowPlayTime;

    public static long endPlayTime;
}

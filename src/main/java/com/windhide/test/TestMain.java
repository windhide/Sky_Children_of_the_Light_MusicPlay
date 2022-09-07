package com.windhide.test;

import com.windhide.entity.Tap.KeyTap;
import com.windhide.util.MusicScoreUtil;

import java.awt.*;
import java.util.List;

public class TestMain {
    private Robot robot = null;

    public TestMain(){
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);
        TestMain playUtil = new TestMain();

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
        List<String> list = MusicScoreUtil.Music1.musicMapOrder(keyTapUtil);


        for (String s : list) {
            if(s.length()>1){
                for (int i = 0; i < s.length(); i++) {
                    playUtil.robot.keyPress(s.charAt(i));
                    playUtil.robot.keyRelease(s.charAt(i));
                }
            }else{
                playUtil.robot.keyPress(s.charAt(0));
                playUtil.robot.keyRelease(s.charAt(0));
            }

            Thread.sleep(350);
        }
    }

}

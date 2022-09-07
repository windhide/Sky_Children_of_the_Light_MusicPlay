package com.windhide.util;

import com.windhide.entity.Tap.KeyTap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public enum MusicScoreUtil {
//    Music1(1, "小兔子乖乖", "5h1655\n3_56h155\n65322\n35_32_31\n6565\n365\n55321\n112131");
    Music1(1, "小兔子乖乖", "123125536666553111665352223213212312555366h1765111655322232121"),
    Music2(2,"晴天","3333455H1123333367H2H166667H1H25H1H2H3H3H3H2H2H1H3H223333455H2H1H1H2H3H3H3H4H3H27H2H1766667H1H255H1H2H3666H3H4H3H25H4H3H2H1H1H17H1H2H355H17655432345555666673H1H17H1H2H2H2H1H3H4H2555H3H3H4H3H27H1H17H1H5H5H4H3H2H4H3H2H2555H3H3H4H3H27H1H2H3H7H6H3H5H4H3H4H3H2H1");


    private Integer musicId;
    private String musicName;
    private String musicTap;


    MusicScoreUtil(Integer musicId, String musicName, String musicTap) {
        this.musicId = musicId;
        this.musicName = musicName;
        this.musicTap = musicTap;
    }

//    public MusicMap getMusicMap(String musicName){
//        for (MusicMap musicMap : MusicMap.values()) {
//            if(musicName.equals(musicMap.musicName)){
//                return musicMap;
//            }
//        }
//        return null;
//    }

    public List<String> musicMapOrder(KeyTap keyTapUtil) {
        keyTapUtil.hashMapInstall();
        String music = musicTap.replaceAll("h1", keyTapUtil.H_Do)      // 高Do
                .replaceAll("h2", keyTapUtil.H_Re)   // 高Re
                .replaceAll("h3", keyTapUtil.H_Mi)   // 高Mi
                .replaceAll("h4", keyTapUtil.H_Fa)   // 高Fa
                .replaceAll("h5", keyTapUtil.H_So)   // 高So
                .replaceAll("h6", keyTapUtil.H_La)   // 高La
                .replaceAll("h7", keyTapUtil.H_Xi)   // 高xi
                .replaceAll("hh", keyTapUtil.HH_Do) // 高Ti
                .replaceAll("H1", keyTapUtil.H_Do)      // 高Do
                .replaceAll("H2", keyTapUtil.H_Re)   // 高Re
                .replaceAll("H3", keyTapUtil.H_Mi)   // 高Mi
                .replaceAll("H4", keyTapUtil.H_Fa)   // 高Fa
                .replaceAll("H5", keyTapUtil.H_So)   // 高So
                .replaceAll("H6", keyTapUtil.H_La)   // 高La
                .replaceAll("H7", keyTapUtil.H_Xi)   // 高xi
                .replaceAll("Hh", keyTapUtil.HH_Do) // 高Ti
                .replaceAll("HH", keyTapUtil.HH_Do); // 高Ti

        for (int i = 0; i < music.length(); i++) {
            String childString = String.valueOf(music.charAt(i));
            if(keyTapUtil.hashMap.containsKey(childString)){
                music = music.replace(childString,keyTapUtil.hashMap.get(childString));
            }
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < music.length(); i++) {
            list.add(String.valueOf(music.toUpperCase().charAt(i)));
        }


        while(list.stream().anyMatch("_"::equals)){
            for (int i = 0; i < list.size(); i++) {
                if("_".equals(list.get(i))){
                    list.set(i-1,list.get(i-1)+list.get(i+1));
                    list.remove(i);
                    list.remove(i);
                }
            }
        }

        return list;
    }
}

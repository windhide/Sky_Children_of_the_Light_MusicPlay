package com.windhide.util;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarLoaderUtil {

    public void setIconImage() {
        URL url = JarLoaderUtil.class.getResource("/images/icon.jpg");
        try {
            StaticUtil.iconImage = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFileNameList() throws IOException {
        // 读取jar包内的文件列表
        List<String> fileNameList = new ArrayList<>();
        URL url = JarLoaderUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
        JarFile file = new JarFile(filePath);

        Enumeration<JarEntry> entrys = file.entries();
        while (entrys.hasMoreElements()) {
            JarEntry jar = entrys.nextElement();
            String musicName = jar.getName();
            if (musicName.contains("MusicScore/") && musicName.contains(".txt")) {
                fileNameList.add(musicName.replaceAll("MusicScore/", "").replaceAll(".txt", ""));
            }
        }
        StaticUtil.fileNameList = fileNameList;
        file.close();
    }
}

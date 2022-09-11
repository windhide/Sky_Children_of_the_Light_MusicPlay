package com.windhide.util;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class JarLoaderUtil {

    public void setIconImage() {
        URL url = JarLoaderUtil.class.getResource("/images/icon.jpg");
        Image img = null;
        try {
            InputStream input = url.openStream();
            StaticUtil.iconImage = javax.imageio.ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

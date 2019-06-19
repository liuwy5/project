package com.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class ImageConverter {
    private static void replaceColor(String srcFile, String dstFile) {
        try {
            Color color = new Color(255, 195, 195);
            replaceImageColor(srcFile, dstFile, color, Color.WHITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceImageColor(String file, String dstFile, Color srcColor, Color targetColor) throws IOException {
        URL http;
        if (file.trim().startsWith("https")) {
            http = new URL(file);
            HttpsURLConnection conn = (HttpsURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        } else if (file.trim().startsWith("http")) {
            http = new URL(file);
            HttpURLConnection conn = (HttpURLConnection) http.openConnection();
            conn.setRequestMethod("GET");
        } else {
            http = new File(file).toURI().toURL();
        }

//        http = new File(file).toURI().toURL();

        BufferedImage bi = ImageIO.read(http.openStream());
        if(bi == null){
            return;
        }

        Color wColor = new Color(255, 255, 255);
        for (int i = 0; i < bi.getWidth(); i++) {
            for (int j = 0; j < bi.getHeight(); j++) {
                //System.out.println(bi.getRGB(i, j));
                int color = bi.getRGB(i, j);
                Color oriColor = new Color(color);
                int red = oriColor.getRed();
                int greed = oriColor.getGreen();
                int blue = oriColor.getBlue();

                if (red >= 180 && greed >= red - 5 && greed <= red + 5 && blue >= red - 5 && blue <= red + 5) {
                    bi.setRGB(i, j, wColor.getRGB());
                }

                //粉色
//                if (greed < 190 || blue < 190) {
//
//                } else {
//                    if (red == 255 && greed > 180 && blue > 180) {
//                        bi.setRGB(i, j, wColor.getRGB());
//                    }
//                }
            }
        }
        String type = file.substring(file.lastIndexOf(".") + 1, file.length());
        Iterator<ImageWriter> it = ImageIO.getImageWritersByFormatName(type);
        ImageWriter writer = it.next();
        File f = new File(dstFile);
        f.getParentFile().mkdirs();
        ImageOutputStream ios = ImageIO.createImageOutputStream(f);
        writer.setOutput(ios);
        writer.write(bi);
        bi.flush();
        ios.flush();
        ios.close();
    }

    public static void main(String[] args) {
        String srcFile = "http://admin.wdzj.com/wdzj/attached/image/2018/10/12/20181012180907_110s_.png";
        String dstFile = "E:\\data\\tmp\\img\\13.png";
        replaceColor(srcFile, dstFile);
    }
}

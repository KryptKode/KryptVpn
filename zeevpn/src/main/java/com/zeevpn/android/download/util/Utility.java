package com.zeevpn.android.download.util;

import android.support.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Utility {

    public enum FileType {
        VPN,
        UNKNOWN
    }

    public static String formatBytes(long bytes) {
        if (bytes < 1024) {
            return String.format("%d B", bytes);
        } else if (bytes < 1024 * 1024) {
            return String.format("%.2f kB", (float) bytes / 1024);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", (float) bytes / 1024 / 1024);
        } else {
            return String.format("%.2f GB", (float) bytes / 1024 / 1024 / 1024);
        }
    }

    public static String formatSpeed(float speed) {
        if (speed < 1024) {
            return String.format("%.2f B/s", speed);
        } else if (speed < 1024 * 1024) {
            return String.format("%.2f kB/s", speed / 1024);
        } else if (speed < 1024 * 1024 * 1024) {
            return String.format("%.2f MB/s", speed / 1024 / 1024);
        } else {
            return String.format("%.2f GB/s", speed / 1024 / 1024 / 1024);
        }
    }

    public static void writeToFile(String fileName, String content) {
        try {
            writeToFile(fileName, content.getBytes("UTF-8"));
        } catch (Exception e) {

        }
    }

    public static void writeToFile(String fileName, byte[] content) {
        File f = new File(fileName);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {

            }
        }

        try {
            FileOutputStream opt = new FileOutputStream(f, false);
            opt.write(content, 0, content.length);
            opt.close();
        } catch (Exception e) {

        }
    }

    public static String readFromFile(String file) {
        try {
            File f = new File(file);

            if (!f.exists() || !f.canRead()) {
                return null;
            }

            BufferedInputStream ipt = new BufferedInputStream(new FileInputStream(f));

            byte[] buf = new byte[512];
            StringBuilder sb = new StringBuilder();

            while (ipt.available() > 0) {
                int len = ipt.read(buf, 0, 512);
                sb.append(new String(buf, 0, len, "UTF-8"));
            }

            ipt.close();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    public static String getFileExt(String url) {
        int index;
        if ((index = url.indexOf("?")) > -1) {
            url = url.substring(0, index);
        }

        index = url.lastIndexOf(".");
        if (index == -1) {
            return null;
        } else {
            String ext = url.substring(index);
            if ((index = ext.indexOf("%")) > -1) {
                ext = ext.substring(0, index);
            }
            if ((index = ext.indexOf("/")) > -1) {
                ext = ext.substring(0, index);
            }
            return ext.toLowerCase();
        }
    }

    public static FileType getFileType(String file) {
        if (file.endsWith(".ovpn")){
            return FileType.VPN;
        } else{
            return FileType.UNKNOWN;
        }
    }

}

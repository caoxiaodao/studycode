package com.study.demo.work;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取系统当前时间
 */
public class SystemCurrentTime {
    public static void main(String[] args) {
        SystemCurrentTime demo = new SystemCurrentTime();
        long currentTime = demo.getRealCurrentTime();
        System.out.println("System last time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime));

    }

    private long getRealCurrentTime() {
        long current = System.currentTimeMillis();
        File[] dirs = new File[]{};
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            File dir = new File(System.getProperty("user.home") + File.separator + "AppData" + File.separator + "Local" + File.separator + "Temp");
            if (!dir.exists()) {
                for (File file : File.listRoots()) {
                    dir = new File(file, "Windows" + File.separator + "Temp");
                    if (dir.exists()) {
                        break;
                    }
                }
            }
            dirs = new File[]{dir};
        }
        for (File dir : dirs) {
            if (dir.exists()) {
                try {
                    long lastModified = Math.abs(Files.walk(dir.toPath(), 1, FileVisitOption.FOLLOW_LINKS).map(f -> -f.toFile().lastModified()).sorted().findFirst().orElse(System.currentTimeMillis()));
                    System.out.println("System last time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastModified)));
                    if (lastModified > current) {
                        current = lastModified;
                    }
                } catch (IOException e) {
                }
            }
        }
        return current;
    }
}

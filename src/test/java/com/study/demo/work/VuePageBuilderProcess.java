package com.study.demo.work;

import org.nutz.lang.Files;

import java.io.File;

/**
 * @author caonan
 * @createtime 2023/4/7 11:24
 * @Description TODO
 * @Version 1.0
 */
public class VuePageBuilderProcess {
    public static void processDir(File dir){
        for(File file:dir.listFiles()){
            processFile(file);
        }
    }

    public static void processFile(File file){
        //重命名
        String srcMainRoute = file.getAbsolutePath() + File.separator +"index.html";
        String targetMainRoute = file.getAbsolutePath() + File.separator +"index.jsp";
        new File(srcMainRoute).renameTo(new File(targetMainRoute));
        System.out.println(srcMainRoute+"\n rename to \n"+targetMainRoute);

        //
        File pageFile = new File(targetMainRoute);
        StringBuilder pageText = new StringBuilder(Files.read(pageFile).replaceAll("\\$\\{","\\\\\\$\\{"));

        pageText.insert(pageText.indexOf("<meta"),
                "<meta name=\"_csrf\" content=\"${_csrf.token}\"/>\n" +
                        "<meta name=\"_csrf_header\" content=\"${_csrf.headerName}\"/>\n" +
                        "<meta name=\"_csrf_parameter\" content=\"${_csrf.parameterName}\"/>\n" +
                        "<meta name=\"decorator\" content=\"\"/>");
        Files.write(pageFile,pageText);
    }


    public static void main(String[]args) {
        processFile(new File("E:\\tmp3\\bjdx\\bjdx20230711\\dist20230712"));
    }
}

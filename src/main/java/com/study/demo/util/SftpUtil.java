package com.study.demo.util;

import com.jcraft.jsch.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * @author caonan
 * @Date 2022/12/2 15:56
 * @Description
 */
public class SftpUtil {
    public static void main(String[] args) {
        JSch jSch = new JSch();
        try {
            Session session = jSch.getSession("root", "10.44.237.90", 22);
            session.setPassword("cybersky");
            Properties config = new Properties();
            config.put("PreferredAuthentications","publickey,keyboard-interactive,password");
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp = (ChannelSftp)channel;
            String[] files = new String[] {"/asset_info","/flow","/malware_sample","/attachment"};
            for (String type:files){
                //删除
                try {
                    String path = "/CSA_nx"+type;
                    Vector ls = sftp.ls(path);
                    for (int i = 0; i < ls.size(); i++) {
                        ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) ls.get(i);
                        long mTime = (long)entry.getAttrs().getMTime()*1000;
                        if (!".".equals(entry.getFilename()) && !"..".equals(entry.getFilename()) && System.currentTimeMillis()- mTime  > 5*60*1000L){
                            delDir(sftp,path+"/"+entry.getFilename());
                        }
                    }
                } catch (SftpException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //递归删除文件
    private static void delDir(ChannelSftp sftp,String path) throws SftpException {
        if (isDir(sftp, path)){//文件夹
            sftp.cd(path);
            Vector<ChannelSftp.LsEntry> ls = sftp.ls(".");
            for (ChannelSftp.LsEntry entry : ls){
                if (!".".equals(entry.getFilename()) && !"..".equals(entry.getFilename())){
                    delDir(sftp, path+"/"+entry.getFilename());
                }
            }
            sftp.cd("..");
            sftp.rmdir(path);
        }else{
            sftp.rm(path);
        }
    }

    /**
     * 判断是文件夹还是文件
     * @param sftp
     * @param path
     * @return
     */
    private static boolean isDir(ChannelSftp sftp,String path) throws SftpException {
        return sftp.stat(path).isDir();
    }
}

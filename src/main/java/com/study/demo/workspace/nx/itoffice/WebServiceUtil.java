package com.study.demo.workspace.nx.itoffice;

import cn.hutool.crypto.SecureUtil;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.tar.TarOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class WebServiceUtil {
    private static final Logger log = LoggerFactory.getLogger(WebServiceUtil.class);

    /**
     * 获取随机20位字符串方法,length一般为20
     *
     * @param length
     * @return 作为randVal
     */

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取PwdHash工具方法 , 默认MD5+Base64
     *
     * @param randomStr 20位的随机字符串
     * @param pwd       用户令牌（token）
     * @param timeStamp 指令时间
     * @return 作为pwdHash参数
     */
    public static String getPwdHash(String randomStr, String pwd, String timeStamp) {
        String res = SecureUtil.md5(pwd + randomStr + timeStamp);
        return new String(Base64.encode(res.getBytes()));
    }

    /**
     * 获取PwdHash工具方法 , 默认MD5+Base64
     *
     * @param randomStr     20位的随机字符串
     * @param pwd           用户令牌（token）
     * @param timeStamp     指令时间
     * @param hashAlgorithm 0-无hash，1-MD5，2-SHA-1
     * @return 作为pwdHash参数
     */
    public static String getPwdHash(String randomStr, String pwd, String timeStamp, Integer hashAlgorithm) {
        String concatStr = pwd + randomStr + timeStamp;
        String res = "";
        if (hashAlgorithm == 1) {
            res = SecureUtil.md5(concatStr);
        } else if (hashAlgorithm == 2) {
            res = SecureUtil.sha1(concatStr);
        } else {
            // 无hash算法
            res = concatStr;
        }
        return new String(Base64.encode(res.getBytes()));
    }

    //command  ---对指令内容进行压缩的方法,判断是否压缩,是否AES加密,最后BASE64转码  返回值作为command参数
    //content 指令内容、compressFlag 压缩标识，useAes Aes加密标识，
    //encryptionKey AES加密秘钥
    public static String readCommand(String content, int compressFlag, int useAes, String encryptionKey, String dataOffset) {
        byte[] tarRes = null;
        if (compressFlag == 1) {
            tarRes = doTargz(content.getBytes());
        } else {
            tarRes = content.getBytes();
        }
        byte[] aes = tarRes;
        if (useAes == 1) {
            aes = new AES().encrypt(tarRes, encryptionKey.getBytes(), dataOffset.getBytes());
        }
        return Base64.encode(aes);
    }

    //对加密的commond进行base64反解码
    //然后采用参数encryptAlgorithm指定的加密算法进行解密处理 得到data
    public static byte[] dataOfDecryptCommand(String commond, int useAes, String encryptionKey,
                                              String dataOffset) {
        byte[] aes = null;
        byte[] decode = Base64.decode(commond);
        if (useAes == 1) {
            aes = new AES().decrypt(decode, encryptionKey.getBytes(), dataOffset.getBytes());
        } else {
            aes = decode;
        }

        return aes;
    }

    //对加密的commond进行base64反解码
    //然后采用参数encryptAlgorithm指定的加密算法进行解密处理
    //按照compressionFormat指定的压缩格式对data进行解压后即得到指令信息
    public static String decryptCommand(String commond, int compressFlag, int useAes, String encryptionKey,
                                        String dataOffset) {
        byte[] aes = null;
        byte[] decode = Base64.decode(commond);
        if (useAes == 1) {
            aes = new AES().decrypt(decode, encryptionKey.getBytes(), dataOffset.getBytes());
        } else {
            aes = decode;
        }

        byte[] tarRes = aes;
        if (compressFlag == 1) {
            tarRes = unTargz(aes);
        } else {
            tarRes = aes;
        }

        return new String(tarRes);
    }

    public static String getOriginCommand(byte[] cmdBytes, int compressionFormat) {

        byte[] tarRes = cmdBytes;
        if (compressionFormat == 1) {
            tarRes = unTargz(cmdBytes);
        }
        return new String(tarRes);
    }

    //写一个md5加密的方法
    public static String md5(byte[] plainText) {
        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(plainText);
            //获得加密后的数据
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    //压缩
    public static byte[] doTargz(byte[] data) {
        byte[] b = null;
        try {
            //先进行tar打包
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            TarOutputStream tar = new TarOutputStream(bos);
            TarEntry tarEntry = new TarEntry("tar");
            tarEntry.setSize(data.length);
            tar.putNextEntry(tarEntry);
            tar.write(data);
            tar.closeEntry();
            tar.close();

            byte[] tarRes = bos.toByteArray();
            bos.close();

            //gzip压缩
            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bos2);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(gzipOutputStream);
            bufferedOutput.write(tarRes);

            bufferedOutput.close();
            gzipOutputStream.close();

            b = bos2.toByteArray();

            bos2.close();

        } catch (Exception ex) {
            log.error("压缩error", ex);
            ex.printStackTrace();
        }
        return b;
    }

    //解压
    public static byte[] unTargz(byte[] data) {
        byte[] b = null;
        ByteArrayInputStream bis = null;
        GZIPInputStream gzipInputStream = null;
        TarInputStream tarIn = null;
        try {
            bis = new ByteArrayInputStream(data);
            gzipInputStream = new GZIPInputStream(bis);
            tarIn = new TarInputStream(gzipInputStream);
            while (tarIn.getNextEntry() != null) {
                ByteArrayOutputStream baos = null;
                try {
                    byte[] buf = new byte[1024];
                    int num = -1;
                    baos = new ByteArrayOutputStream();
                    while ((num = tarIn.read(buf, 0, buf.length)) != -1) {
                        baos.write(buf, 0, num);
                    }
                    b = baos.toByteArray();
                    baos.flush();
                } finally {
                    if (baos != null) {
                        baos.close();
                    }
                }

            }

        } catch (Exception ex) {
            log.error("解压失败", ex);

        } finally {
            if (tarIn != null) {
                try {
                    tarIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (gzipInputStream != null) {
                try {
                    gzipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return b;
    }

    //获取commandHash工具方法,tar压缩+MD5+Base64转码
    public static String getCommandHash(byte[] content, String key, int hashAlgorithm) {
//         String content = FileUtil.readString(path, "utf-8");
        byte[] tarRes = content;
        byte[] keyByte = key.getBytes();
        byte[] all = new byte[tarRes.length + keyByte.length];
        for (int i = 0; i < tarRes.length; i++) {
            all[i] = tarRes[i];
        }
        for (int i = 0; i < keyByte.length; i++) {
            all[tarRes.length + i] = keyByte[i];
        }
        String md5Str = null;
        //只有md5的
        if (hashAlgorithm == 1) {
            md5Str = md5(all);
        } else if (hashAlgorithm == 2) {
            md5Str = SecureUtil.sha1(new String(all));
        } else {
            md5Str = new String(all);
        }
        return Base64.encode(md5Str.getBytes());
    }

    /**
     * 获取PwdHash工具方法 , 默认MD5+Base64
     *
     * @param randomStr 20位的随机字符串
     * @param pwd       用户令牌（token）
     * @return 作为pwdHash参数
     */
    public static String getGroupPwdHash(String randomStr, String pwd) {
        String res = SecureUtil.md5(pwd + randomStr);
        return new String(Base64.encode(res.getBytes()));
    }
}

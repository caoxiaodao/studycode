package com.study.demo.work;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author caonan
 * @createtime 2023/7/21 13:40
 * @Description TODO
 * @Version 1.0
 */
public class UrlTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://127.0.0.1:16520/sap/vuln/artificial,http://127.0.0.1:16520/sap/vuln/artificial");
            System.out.println(url.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

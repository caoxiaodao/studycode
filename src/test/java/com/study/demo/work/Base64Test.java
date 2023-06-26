package com.study.demo.work;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * @author caonan
 * @createtime 2023/4/23 14:39
 * @Description TODO
 * @Version 1.0
 */
public class Base64Test {
    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
        //url地址获取分解信息
        URL url = new URL("http://127.0.0.1:16520/las/auth/page");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getProtocol());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        /**
         * base64编解
         */
        String base64Str = Base64.getEncoder().encodeToString("china is good ???".getBytes(StandardCharsets.US_ASCII));
        byte[] str = Base64.getDecoder().decode("RGdHgTkBcD0VjvM1gQAABQgARQAFNH/WQAA9BodoCotDLwrs2N8AFozhp4j0nl2PhUCAGADjCYAAAAEBCAq50mm3vShkmAAABPwKFOqqlCw6F7ZzfbjECU2ey1wAAAFAY3VydmUyNTUxOS1zaGEyNTYsY3VydmUyNTUxOS1zaGEyNTZAbGlic3NoLm9yZyxlY2RoLXNoYTItbmlzdHAyNTYsZWNkaC1zaGEyLW5pc3RwMzg0LGVjZGgtc2hhMi1uaXN0cDUyMSxkaWZmaWUtaGVsbG1hbi1ncm91cC1leGNoYW5nZS1zaGEyNTYsZGlmZmllLWhlbGxtYW4tZ3JvdXAxNi1zaGE1MTIsZGlmZmllLWhlbGxtYW4tZ3JvdXAxOC1zaGE1MTIsZGlmZmllLWhlbGxtYW4tZ3JvdXAtZXhjaGFuZ2Utc2hhMSxkaWZmaWUtaGVsbG1hbi1ncm91cDE0LXNoYTI1NixkaWZmaWUtaGVsbG1hbi1ncm91cDE0LXNoYTEsZGlmZmllLWhlbGxtYW4tZ3JvdXAxLXNoYTEAAABBc3NoLXJzYSxyc2Etc2hhMi01MTIscnNhLXNoYTItMjU2LGVjZHNhLXNoYTItbmlzdHAyNTYsc3NoLWVkMjU1MTkAAACvY2hhY2hhMjAtcG9seTEzMDVAb3BlbnNzaC5jb20sYWVzMTI4LWN0cixhZXMxOTItY3RyLGFlczI1Ni1jdHIsYWVzMTI4LWdjbUBvcGVuc3NoLmNvbSxhZXMyNTYtZ2NtQG9wZW5zc2guY29tLGFlczEyOC1jYmMsYWVzMTkyLWNiYyxhZXMyNTYtY2JjLGJsb3dmaXNoLWNiYyxjYXN0MTI4LWNiYywzZGVzLWNiYwAAAK9jaGFjaGEyMC1wb2x5MTMwNUBvcGVuc3NoLmNvbSxhZXMxMjgtY3RyLGFlczE5Mi1jdHIsYWVzMjU2LWN0cixhZXMxMjgtZ2NtQG9wZW5zc2guY29tLGFlczI1Ni1nY21Ab3BlbnNzaC5jb20sYWVzMTI4LWNiYyxhZXMxOTItY2JjLGFlczI1Ni1jYmMsYmxvd2Zpc2gtY2JjLGNhc3QxMjgtY2JjLDNkZXMtY2JjAAAA1XVtYWMtNjQtZXRtQG9wZW5zc2guY29tLHVtYWMtMTI4LWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTItMjU2LWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTItNTEyLWV0bUBvcGVuc3NoLmNvbSxobWFjLXNoYTEtZXRtQG9wZW5zc2guY29tLHVtYWMtNjRAb3BlbnNzaC5jb20sdW1hYy0xMjhAb3BlbnNzaC5jb20saG1hYy1zaGEyLTI1NixobWFjLXNoYTItNTEyLGhtYWMtc2hhMQAAANV1bWFjLTY0LWV0bUBvcGVuc3NoLmNvbSx1bWFjLTEyOC1ldG1Ab3BlbnNzaC5jb20saG1hYy1zaGEyLTI1Ni1ldG1Ab3BlbnNzaC5jb20saG1hYy1zaGEyLTUxMi1ldG1Ab3BlbnNzaC5jb20saG1hYy1zaGExLWV0bUBvcGVuc3NoLmNvbSx1bWFjLTY0QG9wZW5zc2guY29tLHVtYWMtMTI4QG9wZW5zc2guY29tLGhtYWMtc2hhMi0yNTYsaG1hYy1zaGEyLTUxMixobWFjLXNoYTEAAAAVbm9uZSx6bGliQG9wZW5zc2guY29tAAAAFW5vbmUsemxpYkBvcGVuc3NoLmNvbQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(new String(str,"UTF-8"));

        List<Integer> list = new ArrayList<>();
//        List<Integer> list1 = new ArrayList<>()[8,9,20,5,3,7];
        int[] list1 = {8,9,20,5,3,7};
       /* List<Integer> ints = Arrays.asList(list1);
        for (Integer i: ints) {
           if (!Lang.isEmpty()){
               for (int j = 0; j < list.size(); j++) {
                   if (i<j){
                       list.add(j,i);
                   }else {
                       continue;
                   }
               }
           }else {
               list.add(i);
           }
        }*/
    }
}

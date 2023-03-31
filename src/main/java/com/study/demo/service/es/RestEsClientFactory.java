package com.study.demo.service.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author caonan
 * @createtime 2023/2/13 16:57
 * @Description TODO
 * @Version 1.0
 */
public class RestEsClientFactory {
    private static RestHighLevelClient client;
    private RestEsClientFactory(){}
    private static RestEsClientFactory me = new RestEsClientFactory();
    public static RestEsClientFactory getMe(){
        return me;
    }
    /**
     * 获取client
     * @return
     */
    public synchronized RestHighLevelClient getClient(){
        if (client == null){
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 31600, "http"),
                            new HttpHost("localhost", 31601, "http")));
        }
        return client;
    }


}

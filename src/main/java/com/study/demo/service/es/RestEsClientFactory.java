package com.study.demo.service.es;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

/**
 * @author caonan
 * @createtime 2023/2/13 16:57
 * @Description TODO
 * @Version 1.0
 */
public class RestEsClientFactory {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 31600, "http"),
                        new HttpHost("localhost", 31601, "http")));
        GetRequest getRequest = new GetRequest("blog1", "_doc", "1");
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        client.getAsync(getRequest, RequestOptions.DEFAULT,new ActionListener<GetResponse>(){

            @Override
            public void onResponse(GetResponse documentFields) {
                documentFields.getSource();
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("打印错误");
            }
        });
        System.out.println(2);
//        System.out.println(getResponse.getSource());
    }

}

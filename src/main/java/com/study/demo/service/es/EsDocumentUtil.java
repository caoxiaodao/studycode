package com.study.demo.service.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author caonan
 * @createtime 2023/2/15 11:42
 * @Description TODO
 * @Version 1.0
 */
public class EsDocumentUtil {
    /**
     * 根据索引，id获取文档
     */
    public static void getById(String index,String id){
        GetRequest getRequest = new GetRequest(index, "_doc", id);
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        RestEsClientFactory.getMe().getClient().getAsync(getRequest, RequestOptions.DEFAULT,new ActionListener<GetResponse>(){

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
    }

    /**
     * 新增文档
     * @param index
     * @param context
     * @throws IOException
     */
    private static void putDoc(String index, String id,String context) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index,"_doc",id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("date",System.currentTimeMillis());
        HashMap<String, Long> longRange = new HashMap<>();
        longRange.put("gte",1L);
        longRange.put("lte",10L);
        map.put("longRange",longRange);
        map.put("textKeyword","北京真的非常美丽，这是一个靠谱的城市");
        map.put("text","北京真的非常美丽，这是一个靠谱的城市");
        map.put("keyword","北京真的非常美丽，这是一个靠谱的城市");
        indexRequest.source(JSON.toJSONString(map), XContentType.JSON);
        IndexResponse response = RestEsClientFactory.getMe().getClient().index(indexRequest, RequestOptions.DEFAULT);
        DocWriteResponse.Result result = response.getResult();
    }
    /**
     * 批量新增文档
     * @param index
     * @param list
     * @throws IOException
     */
    private static void putDocs(String index, List<Map<String,Object>> list) throws IOException {
        BulkRequest request = new BulkRequest();
        list  = new ArrayList<>();
        for (long i = 21; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",i);
            map.put("date",System.currentTimeMillis());
            map.put("number",i);
            HashMap<String, Long> longRange = new HashMap<>();
            longRange.put("gte",i);
            longRange.put("lte",i+10);
            map.put("longRange",longRange);
            map.put("textKeyword","北京真的非常美丽，这是一个靠谱的城市"+i);
            map.put("text","北京真的非常美丽，这是一个靠谱的城市"+i);
            map.put("keyword","北京真的非常美丽，这是一个靠谱的城市"+i);
            list.add(map);
        }
        for (Map<String,Object> map:list) {
            request.add(new IndexRequest(index,"_doc",map.get("id").toString()).source(JSON.toJSONString(map),XContentType.JSON));
            BulkResponse bulk = RestEsClientFactory.getMe().getClient().bulk(request, RequestOptions.DEFAULT);
            bulk.hasFailures();
            for (BulkItemResponse bulkItemResponse:bulk) {
                bulkItemResponse.getFailure();
            }
        }
    }
    /**
     * 批量新增文档
     * @param index
     * @param list
     * @throws IOException
     */
    private static void putDocsByProcessor(String index, List<Map<String,Object>> list) throws IOException {
        list  = new ArrayList<>();
        for (int i = 20; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",i);
            map.put("test1","test"+i);
            map.put("test2","test"+i);
            list.add(map);
        }
        BulkRequest bulkRequest = new BulkRequest();
        BulkProcessor.Listener listener = new BulkProcessor.Listener() {

            @Override
            public void beforeBulk(long l, BulkRequest bulkRequest) {
                System.out.println("bulk之前执行");
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, BulkResponse bulkResponse) {
                System.out.println("bulk之后执行");
            }

            @Override
            public void afterBulk(long l, BulkRequest bulkRequest, Throwable throwable) {
                System.out.println("bulk错误执行");
            }
        };
        BiConsumer<BulkRequest, ActionListener<BulkResponse>> bulkConsumer =
                (request, bulkListener) ->
                        RestEsClientFactory.getMe().getClient().bulkAsync(request, RequestOptions.DEFAULT, bulkListener);
        BulkProcessor bulkProcessor =
                BulkProcessor.builder(bulkConsumer, listener).build();



        for (Map<String,Object> map:list) {
            bulkProcessor.add(new IndexRequest(index,"_doc",map.get("id").toString()).source(map));
        }
        bulkProcessor.close();
    }

    /**
     * 获取doc
     * @param index
     * @param id
     * @throws IOException
     */
    public static void getDoc(String index,String id) throws IOException {
        GetRequest getRequest = new GetRequest(index,"_doc",id);
        GetResponse getResponse = RestEsClientFactory.getMe().getClient().get(getRequest, RequestOptions.DEFAULT);
        getResponse.isExists();
    }
    public static void main(String[] args) throws IOException {
        putDoc("test1","3",null);
//        putDocs("test1",null);
//        putDocsByProcessor("twitter",null);
    }
}

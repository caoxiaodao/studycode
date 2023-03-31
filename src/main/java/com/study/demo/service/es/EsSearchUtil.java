package com.study.demo.service.es;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @author caonan
 * @createtime 2023/2/17 10:19
 * @Description es的search查询的api
 * @Version 1.0
 */
public class EsSearchUtil {
    public void getDocBySearch(String search){
        // 可以限制索引，也可以不限制索引
        SearchRequest searchRequest = new SearchRequest("twitter");
        // 限制只使用open状态的索引（关于通配符怎么扩展）
        searchRequest.indicesOptions(IndicesOptions.LENIENT_EXPAND_OPEN);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
    }
}

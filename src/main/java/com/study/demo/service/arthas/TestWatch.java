package com.study.demo.service.arthas;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/4/1 10:50
 * @Description TODO
 * @Version 1.0
 */
@Service
public class TestWatch {
    public Map<String,Object> testMap(String str, Map<String,Object> map){
//        Map<String, Object> res = new HashMap<>();
//        ArthasVO arthasVO = new ArthasVO();
//        arthasVO.setUserId(1L);
//        arthasVO.setUsername("wangh");
//        res.put("1",arthasVO);
        return ArthasCache.getInstance().getCache();
    }
}

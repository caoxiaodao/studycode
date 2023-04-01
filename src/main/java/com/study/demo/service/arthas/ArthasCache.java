package com.study.demo.service.arthas;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caonan
 * @createtime 2023/4/1 11:41
 * @Description TODO
 * @Version 1.0
 */
public class ArthasCache {

    private Map<String,Object> map = new HashMap<>();
    private static ArthasCache arthasCache;
    private ArthasCache() {
    }
    public static ArthasCache getInstance(){
        if (!ObjectUtils.isEmpty(arthasCache)){
            arthasCache = new ArthasCache();
        }
        return arthasCache;
    }
    public void reloadCache(){
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i),i);
        }
    }
}

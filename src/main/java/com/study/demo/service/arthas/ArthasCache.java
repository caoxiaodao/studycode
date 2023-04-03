package com.study.demo.service.arthas;

import com.study.demo.vo.ArthasVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger  = LoggerFactory.getLogger(ArthasCache.class);
    private Map<String,Object> map = new HashMap<>();
    private static ArthasCache arthasCache;
    private ArthasCache() {
    }
    public static ArthasCache getInstance(){
        if (ObjectUtils.isEmpty(arthasCache)){
            arthasCache = new ArthasCache();
        }
        return arthasCache;
    }
    public void reloadCache(){
        logger.info("===加载缓存=====");
        for (int i = 0; i < 10; i++) {
            ArthasVO arthasVO = new ArthasVO();
            arthasVO.setUserId((long) i);
            arthasVO.setUsername("wangh"+i);
            map.put(String.valueOf(i),arthasVO);
        }
    }
    public Map<String, Object> getCache(){
        return map;
    }
}

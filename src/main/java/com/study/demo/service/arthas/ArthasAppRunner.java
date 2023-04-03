package com.study.demo.service.arthas;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author caonan
 * @createtime 2023/4/1 15:57
 * @Description TODO
 * @Version 1.0
 */
@Component
public class ArthasAppRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ArthasCache.getInstance().reloadCache();
    }
}

package com.study.demo.vo;/**
 * @author caonan
 * @Date 2022/8/21 7:50
 * @Description
 */

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author caonan
 * @Date 2022/8/21 7:50
 * @Description
 */
@Repository
public class SecurityCommmandVo {
    private Long odId;
    private String test;

    public Long getOdId() {
        return odId;
    }

    public void setOdId(Long odId) {
        this.odId = odId;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
}

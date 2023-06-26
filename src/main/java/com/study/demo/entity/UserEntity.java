package com.study.demo.entity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author caonan
 * @createtime 2023/4/25 17:10
 * @Description TODO
 * @Version 1.0
 */
@Entity
@Table(name = "demo_user")
@Data
public class UserEntity {
    @Id
    @Column
    private Long id;
    private String name;
    private String desc;
}

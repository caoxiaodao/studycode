package com.study.demo.repository;

import com.study.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author caonan
 * @createtime 2023/4/25 17:54
 * @Description TODO
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<UserEntity,Long> {
}

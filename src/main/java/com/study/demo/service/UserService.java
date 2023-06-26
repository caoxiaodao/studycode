package com.study.demo.service;

import com.study.demo.entity.UserEntity;
import com.study.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author caonan
 * @createtime 2023/4/25 17:56
 * @Description TODO
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity findById(Long id) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }
}

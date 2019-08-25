package com.example.springcloudtest.ctrl;

import com.example.springcloudtest.pojo.User;
import com.example.springcloudtest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanglaijie
 * @since 2019-08-22
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable  Long id){
        User one = this.userRepository.findOne(id);
        return one;
    }
}

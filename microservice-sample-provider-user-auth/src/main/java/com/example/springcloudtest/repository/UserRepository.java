package com.example.springcloudtest.repository;

import com.example.springcloudtest.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhanglaijie
 * @since 2019-08-22
 */
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
}

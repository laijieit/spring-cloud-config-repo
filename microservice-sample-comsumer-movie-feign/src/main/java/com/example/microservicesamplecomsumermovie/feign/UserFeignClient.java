package com.example.microservicesamplecomsumermovie.feign;

import com.example.microservicesamplecomsumermovie.pojo.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhanglaijie
 * @since 2019-08-24
 */
@FeignClient(value = "microservice-provider-user",fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping("/{id}")
    User findById(@PathVariable("id") Long id);
}

@Component
class UserFeignClientFallBack implements UserFeignClient{

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-5L);
        return user;
    }
}

@Component
@Slf4j
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient>{

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
               log.info("fallback; reason was:",throwable);
                User user = new User();
                user.setId(-6L);
                return user;
            }
        };
    }
}

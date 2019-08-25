package com.example.zuul.aggregation.service;

import com.example.zuul.aggregation.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

import java.security.PublicKey;

/**
 * @author zhanglaijie
 * @since 2019-08-25
 */
@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public rx.Observable<User> getUserById(Long id){
        return Observable.create(observer->{
            //请求用户微服务的/{id}端点
            User user = restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserById(Long id){
        return Observable.create(observer->{
            //请求用户微服务的/{id}端点
            User user = restTemplate.getForObject("http://micriservice-consumer-movie/user2/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    public User fallback(Long id){
        User user = new User();
        user.setId(-1L);
        return user;
    }
}

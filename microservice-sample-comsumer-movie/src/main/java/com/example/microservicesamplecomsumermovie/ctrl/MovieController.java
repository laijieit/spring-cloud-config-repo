package com.example.microservicesamplecomsumermovie.ctrl;

import com.example.microservicesamplecomsumermovie.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhanglaijie
 * @since 2019-08-22
 */
@RestController
@Slf4j
public class MovieController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        User forObject = this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
        return forObject;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        List<ServiceInstance> instances = this.discoveryClient.getInstances("microservice-provider-user");
        return instances;
    }

    @GetMapping("/log-instance")
    public String logUserInstance(){
        ServiceInstance choose = this.loadBalancerClient.choose("microservice-provider-user");
        log.info("{}:{}:{}",choose.getServiceId(),choose.getHost(),choose.getPort());
        return String.valueOf(choose.getPort());
    }

}

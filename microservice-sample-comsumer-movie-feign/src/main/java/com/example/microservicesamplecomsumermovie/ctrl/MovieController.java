package com.example.microservicesamplecomsumermovie.ctrl;

import com.example.microservicesamplecomsumermovie.feign.UserFeignClient;
import com.example.microservicesamplecomsumermovie.pojo.User;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zhanglaijie
 * @since 2019-08-22
 */
@RestController
@Slf4j
//@Import(FeignClientsConfiguration.class)
public class MovieController {

//    private UserFeignClient userUserFeignClient;
//    private UserFeignClient adminUserFeignClient;
//
//    @Autowired
//    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
//        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1"))
//                .target(UserFeignClient.class,"http://microservice-provider-user/");
//        this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
//                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2"))
//                .target(UserFeignClient.class,"http://microservice-provider-user/");
//    }
//
//    @GetMapping("/user-user/{id}")
//    public User findByIdUser(@PathVariable("id") long id){
//        return this.userUserFeignClient.findById(id);
//    }
//
//    @GetMapping("/user-admin/{id}")
//    public User findByIdAdmin(@PathVariable long id){
//        return this.adminUserFeignClient.findById(id);
//    }

//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable Long id){
//        User forObject = this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
//        return forObject;
//    }

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/user2/{id}")
    public User findById2(@PathVariable Long id){
        User byId = this.userFeignClient.findById(id);
        return byId;
    }

//    @GetMapping("/user-instance")
//    public List<ServiceInstance> showInfo(){
//        List<ServiceInstance> instances = this.discoveryClient.getInstances("microservice-provider-user");
//        return instances;
//    }
//
//
//    @GetMapping("/log-instance")
//    public String logUserInstance(){
//        ServiceInstance choose = this.loadBalancerClient.choose("microservice-provider-user");
//        log.info("{}:{}:{}",choose.getServiceId(),choose.getHost(),choose.getPort());
//        return String.valueOf(choose.getPort());
//    }

}

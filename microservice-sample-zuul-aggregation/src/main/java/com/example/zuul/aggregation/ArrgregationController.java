package com.example.zuul.aggregation;

import com.example.zuul.aggregation.pojo.User;
import com.example.zuul.aggregation.service.AggregationService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

import java.util.HashMap;

/**
 * @author zhanglaijie
 * @since 2019-08-25
 */
@RestController
@Slf4j
public class ArrgregationController {
    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/aggregation/{id}")
    public DeferredResult<HashMap<String,User>> aggregate(@PathVariable Long id){
        Observable<HashMap<String, User>> hashMapObservable = this.aggregateObservable(id);
        return this.tozDeferredResult(hashMapObservable);
    }


    public Observable<HashMap<String,User>> aggregateObservable(Long id){
        return Observable.zip(
          this.aggregationService.getUserById(id),
          this.aggregationService.getMovieUserById(id),
                (user,movie)->{
              HashMap<String,User> map = Maps.newHashMap();
              map.put("user",user);
              map.put("movieUser",movie);
              return map;
                }
        );
    }

    public DeferredResult<HashMap<String,User>> tozDeferredResult(Observable<HashMap<String,User>> details){
        DeferredResult<HashMap<String,User>> objectDeferredResult = new DeferredResult<>();
        //订阅
        Subscription subscribe = details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                log.info("完成...");
            }

            @Override
            public void onError(Throwable e) {
                log.error("发生错误...",e);
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                objectDeferredResult.setResult(stringUserHashMap);
            }
        });
        return objectDeferredResult;
    }
}

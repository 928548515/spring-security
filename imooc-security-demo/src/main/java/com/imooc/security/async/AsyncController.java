package com.imooc.security.async;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

@RestController
public class AsyncController {


    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;


    @Autowired
    private DeferredResultHolder deferredResultHolder;




    @GetMapping("/order/{id}")
    public Callable<String> order(@PathVariable String id)throws  Exception {
        logger.info("主线程开始。。。");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始。。。");
                Thread.sleep(1000);
                logger.info("副线程返回。。。");
                return "success";
            }
        };
        String futer = callable.call();

        logger.info("主线程返回。。。");
        logger.info("result :" + JSONObject.toJSONString(callable));
        return callable;
    }


    @PostMapping("/order/{id}")
    public DeferredResult<String> order1(@PathVariable String id)throws  Exception {
        logger.info("主线程开始。。。");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);
        logger.info("主线程返回。。。");
        return result;
    }

}

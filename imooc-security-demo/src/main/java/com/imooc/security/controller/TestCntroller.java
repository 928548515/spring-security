package com.imooc.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.annotation.MyFirstAnnotation;
import com.imooc.security.entity.User;
import com.imooc.security.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class TestCntroller {

    @GetMapping("/hello")
    public String hello(){
        return  "hello security";
    }

     private Logger logger = LoggerFactory.getLogger(getClass());


    @MyFirstAnnotation(value = "xxx")
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> getUser(@RequestParam(required = false) String username){
        System.out.println(username);
        List<User> users = new ArrayList<>();
        users.add(new User("1","aaa","1111"));
        users.add(new User("2","bbb","2222"));
        users.add(new User("3","ccc","3333"));
        return  users;
    }

    @GetMapping("/{id}")
    @JsonView(User.UserDeatilView.class)
    public User getInfo(@PathVariable String id){
//        throw new UserNotExistException(id);
//        throw new RuntimeException("user is null");
        User user = new User();
        user.setUsername("popo");
        user.setPassword("xxxxxx");
        return  user;
    }


    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public User CreateUser(@Valid @RequestBody User user, BindingResult errors){
        //如果验证不通过的处理
        if (errors.hasErrors()){
                //打印错误
                errors.getAllErrors().stream().forEach(error -> logger.info(error.getDefaultMessage()) );
        }
        return  user;
    }



}

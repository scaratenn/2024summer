package com.example.controller;

import com.example.pojo.User;
import com.example.usermapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/daping")
@Slf4j

public class DapingController {
    @Resource
    private UserMapper userMapper;
    @RequestMapping("/student")
    public List<User> list(){
        return userMapper.selectList(null);
    }
}

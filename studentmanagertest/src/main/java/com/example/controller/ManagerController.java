package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.managermeth.ManagerLogin;
import com.example.pojo.Manager;
import com.example.pojo.User;
import com.example.usermapper.ManagerMapper;
import com.example.usermeth.UserLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerMapper managerMapper;

    @GetMapping("/sayhello")
    public String sayhello(){
        log.info("链接manager");
        return "sayhello2";
    }
    @PostMapping("/login")
    public boolean login(@RequestBody ManagerLogin managerLogin){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId,managerLogin.getId()));
        if(o==null){
            return false;
        }
        if(!o.getPassword().equals(managerLogin.getPassword())){
            return false;
        }
        log.info("id:{},password:{}",managerLogin.getId(),managerLogin.getPassword());
        log.info("o:{}",o);
        return true;


    }
}

package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.pojo.User;
import com.example.usermapper.UserMapper;
import com.example.usermeth.UserLogin;
import com.example.usermeth.UserRegister;
import com.example.usermeth.userupdate.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserMapper userMapper;
    @GetMapping("/sayhello")
    public String sayhello(){
        return "hello";
    }
    @PostMapping("/login")
    public boolean login(@RequestBody UserLogin userLogin){
            User o= userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userLogin.getId()));
            if(o==null){
                return false;
            }
            if(!o.getPassword().equals(userLogin.getPassword())){
                return false;
            }
            log.info("id:{},password:{}",userLogin.getId(),userLogin.getPassword());
            log.info("o:{}",o);
            return true;


    }

    @PostMapping("/update/code")
    public boolean updatecode(@RequestBody UserUpdateCode userUpdateCode){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateCode.getId()));
        String code=userUpdateCode.getCode();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdateCode.getCode());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/address")
    public boolean updateaddress(@RequestBody UserUpdateAddress userUpdateAddress){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateAddress.getId()));
        String address=userUpdateAddress.getAddress();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdateAddress.getAddress());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/email")
    public boolean updateemail(@RequestBody UserUpdateemail userUpdateemail){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateemail.getId()));
        String email=userUpdateemail.getEmail();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdateemail.getEmail());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/password")
    public boolean updatepassword(@RequestBody UserUpdatePassword userUpdatePassword){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdatePassword.getId()));
        String address=userUpdatePassword.getPassword();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdatePassword.getPassword());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/etele")
    public boolean updateaddress(@RequestBody UserUpdateEtele userUpdateEtele){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateEtele.getId()));
        String address=userUpdateEtele.getEtele();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdateEtele.getEtele());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/register")
    public boolean register(@RequestBody UserRegister userRegister){
       String code = userRegister.getCode();
       String password=userRegister.getPassword();
       String repassword=userRegister.getRepassword();
       String email =userRegister.getEmail();
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userRegister.getId()));
        if(Objects.isNull(user)){
            log.info("未找到此账号");
            return false;
        }
        if(!code.equals(user.getCode())){
            log.info("身份证号与学号不匹配");
            return false;
        }
        if(!password.equals(repassword)){
            log.info("密码设置错误");
            return false;
        }
        user.setEmail(email);
        user.setPassword(password);
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

}

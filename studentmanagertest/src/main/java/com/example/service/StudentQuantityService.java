package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.User;
import com.example.usermapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

public interface StudentQuantityService  extends IService<User> {
}

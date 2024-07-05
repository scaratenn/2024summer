package com.example.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pojo.User;
import com.example.service.StudentQuantityService;
import com.example.usermapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentQuantityImpl extends ServiceImpl<UserMapper, User> implements StudentQuantityService {
}
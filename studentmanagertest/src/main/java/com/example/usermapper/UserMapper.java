package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Major;
import com.example.pojo.User;
import com.example.usermeth.UserShow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}

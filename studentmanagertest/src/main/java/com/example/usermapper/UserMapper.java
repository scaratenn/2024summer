package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

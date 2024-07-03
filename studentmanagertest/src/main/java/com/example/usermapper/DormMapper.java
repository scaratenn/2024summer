package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Dorm;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DormMapper extends BaseMapper<Dorm> {
}

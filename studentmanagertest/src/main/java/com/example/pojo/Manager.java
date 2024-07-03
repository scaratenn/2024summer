package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("manager")
public class Manager {
    private Integer id;
    private String name;
    private String password;
    private String email;//可改
    private String tele;//可改
}

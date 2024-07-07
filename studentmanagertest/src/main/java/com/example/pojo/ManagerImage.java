package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("managerimager")
public class ManagerImage {
    private Integer id;
    private String image;
}

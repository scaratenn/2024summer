package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class Aclass {
    private Integer id;
    private String name;
    private String teacher;
    private String classroom;
    private String major;
    private String time;
    private String volumn;
}

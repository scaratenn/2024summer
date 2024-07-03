package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dorm")
public class Dorm {
    private String building;
    private Integer host1;
    private Integer host2;
    private Integer host3;
    private Integer host4;
}

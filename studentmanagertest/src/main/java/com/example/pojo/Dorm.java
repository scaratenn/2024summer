package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dorm")
public class Dorm {
   // private Integer id;
 @TableId("building")
    private String building;
    private String areanum;
    private String buildnum;
    private Integer host1;
    private Integer host2;
    private Integer host3;
    private Integer host4;
    private Integer count1;
}

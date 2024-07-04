package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("consult")
public class Consult {
    private Integer id;
    private String content;
    private String answer;
    private Integer stuId;
}

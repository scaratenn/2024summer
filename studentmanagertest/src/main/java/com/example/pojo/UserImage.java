package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userimage")
public class UserImage {
    private Integer id;
    private String image;
   // private String md5Image;
}

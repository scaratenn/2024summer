package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

//@ApiModel(value="signdate",description="报到日期")
public class SignDate {
    private String signdate;
    private Integer count1;
}

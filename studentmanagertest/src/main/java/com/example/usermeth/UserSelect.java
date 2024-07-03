package com.example.usermeth;

import lombok.Data;

@Data
public class UserSelect {
    private Integer id;
    private String addr;
    private String email;
    private String dorm;
    private String name;
    private String etele; //紧急联系人
    private String code; //身份证
    private String college; //学校
    private Integer aclass;//班级
    private String major;//专业
    private String signdate; //报到时间
}

package com.example.usermeth;

import lombok.Data;

@Data
public class UserShow {
    private String name;
    private String email;
    private Integer id;
    private String dorm;
    private String addr;
    private String etele; //紧急联系人
    private String code; //身份证
    private String college; //学校
    private Integer aclass;//班级
    private String major;//专业
}

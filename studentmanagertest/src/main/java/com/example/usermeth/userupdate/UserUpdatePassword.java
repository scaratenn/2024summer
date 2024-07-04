package com.example.usermeth.userupdate;

import lombok.Data;

@Data
public class UserUpdatePassword {
    private Integer id;
    private String password;
    private String oldpassword;
    //private String renewpassword;
}

package com.example.usermeth;

import com.mysql.cj.util.DnsSrv;
import lombok.Data;

@Data
public class UserRegister {
    private String name;
    private String code;
    private String email;
    private Integer id;
    private String password;
    private String repassword;
    private String etele;
}

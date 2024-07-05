package com.example.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Toemail implements Serializable {

    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
}

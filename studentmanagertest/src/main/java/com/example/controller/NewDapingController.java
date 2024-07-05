package com.example.controller;

import com.example.pojo.User;
import com.example.service.StudentQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/newdp")
public class NewDapingController {
   /* @Autowired*/
   // private StudentQuantityService studentQuantityService;


    @RequestMapping("/index")
    public String index(){
        //List<User> list = studentQuantityService.list();
        //  model.("StudentQuantityList",list);
        return "index";
    }

    @RequestMapping("/line")
    public String line(){
        return "line";
    }

    @RequestMapping("/today")
    public String today(){
        return "today";
    }

    @RequestMapping("/ao")
    public String ao(){
        return "ao";
    }

    @RequestMapping("/map")
    public String map(){
        return "map";
    }

    @RequestMapping("/collegedao")
    public String collegedao(){
        return "collegedao";
    }

    @RequestMapping("/doom")
    public String doom(){
        return "doom";
    }

    @RequestMapping("/empty")
    public String empty(){
        return "empty";
    }

    @RequestMapping("/living")
    public String living(){
        return "living";
    }
}

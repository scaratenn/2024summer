package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.managermeth.*;
import com.example.pojo.College;
import com.example.pojo.Major;
import com.example.pojo.Manager;
import com.example.pojo.User;
import com.example.usermapper.ManagerMapper;
import com.example.usermapper.UserMapper;
import com.example.usermeth.userupdate.UserUpdateemail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
//@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private UserMapper userMapper;



    @GetMapping("/sayhello")
    public String sayhello(){
        log.info("链接manager");
        return "sayhello2";
    }
    @PostMapping("/login")
    public boolean login(@RequestBody ManagerLogin managerLogin){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId,managerLogin.getId()));
        if(o==null){
            log.info("错误");
            return false;
        }
        if(!o.getPassword().equals(managerLogin.getPassword())){
            log.info("错误1");
            return false;
        }
        log.info("id:{},password:{}",managerLogin.getId(),managerLogin.getPassword());
        log.info("o:{}",o);

        return true;


    }

    @GetMapping("/select")
    public User select(@RequestParam Integer id){
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
    }

    @PostMapping("/update")
    public boolean update(@RequestParam ManagerUpdate managerUpdate){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId,managerUpdate.getId()));
        //String code=userUpdate.getCode();
        String tele=managerUpdate.getTele();
        String email=managerUpdate.getEmail();

        if(Objects.isNull(o)) {
            log.info("no found manager");
            return false;
        }
        o.setEmail(email);
        o.setTele(tele);

        managerMapper.updateById(o);
        log.info("manager:{}",o);
        return true;
    }

    @PostMapping("/update/email")
    public boolean updateemail(@RequestBody ManagerUpdateEmail managerUpdateEmail){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId, managerUpdateEmail.getId()));
        String email=managerUpdateEmail.getEmail();
        if(Objects.isNull(o)){
            log.info("no found user");
            return false;
        }
        o.setEmail(email);
        managerMapper.updateById(o);
        log.info("user:{}",o);
        return true;

    }

    @PostMapping("/update/password")
    public boolean updattele(@RequestBody ManagerUpdatePassword managerUpdatePassword){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId, managerUpdatePassword.getId()));
        String password=managerUpdatePassword.getPassword();
        String oldpassword=managerUpdatePassword.getOldpassword();

       /* if(Objects.isNull(o)){
            log.info("no found user");
            return false;
        }*/
        String oldpasswordin=o.getPassword();
        if(oldpasswordin.equals(oldpassword)){
            log.info("新旧密码一致");
            return false;
        }

        o.setPassword(password);
        managerMapper.updateById(o);
        log.info("user:{}",o);
        return true;

    }

    @PostMapping("/update/tele")
    public boolean updatetele(@RequestBody ManagerUpdateTele managerUpdateTele){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId, managerUpdateTele.getId()));
        String tele=managerUpdateTele.getTele();
        if(Objects.isNull(o)){
            log.info("no found user");
            return false;
        }
        o.setTele(tele);
        managerMapper.updateById(o);
        log.info("user:{}",o);
        return true;

    }

    @GetMapping("/showdata")
    public ManagerShow showdata(@RequestParam Integer id){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId,id));
        if(Objects.isNull(o)){
            log.info("not found user");

        }
        ManagerShow managerShow = new ManagerShow();
        managerShow.setId(id);
        managerShow.setName(o.getName());
        managerShow.setTele(o.getTele());
        return  managerShow;
    }

    @GetMapping("/countMajor")//查询各专业人数
    public List<Major> countMajor(){
        return managerMapper.countMajor();
    }
  //  @ApiOperation(value = "查询已完成信息收集的人数")
    @GetMapping("/countNumber")
    public int countNumber(){
        return managerMapper.countNumber();
    }
   // @ApiOperation(value = "查询各学院人数")
    @GetMapping("/countCollege")
    public List<College> countCollege() {
        return managerMapper.countCollege();
    }
    //@ApiOperation(value = "查询不同报到日期人数")
    @GetMapping("/countSigndate")
    public List<College> countSi() {
        return managerMapper.countCollege();
    }

}

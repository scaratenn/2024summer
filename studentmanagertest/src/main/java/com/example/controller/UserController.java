package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.coursemeth.CourseSelect;
import com.example.pojo.Aclass;
import com.example.pojo.Consult;
import com.example.pojo.Dorm;
import com.example.pojo.User;
import com.example.usermapper.AclassMapper;
import com.example.usermapper.DormMapper;
import com.example.usermapper.UserMapper;
import com.example.usermeth.UserLogin;
import com.example.usermeth.UserRegister;
import com.example.usermeth.UserShow;
import com.example.usermeth.userupdate.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
//@CrossOrigin
@Slf4j
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AclassMapper aclassMapper;
    @Resource
    private DormMapper dormMapper;
    @GetMapping("/sayhello")
    public String sayhello(){
        return "hello";
    }
    @PostMapping("/login")
    public boolean login(@RequestBody UserLogin userLogin){
            User o= userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userLogin.getId()));
            if(o==null){
                log.info("没有此用户");
                return false;
            }
            if(!o.getPassword().equals(userLogin.getPassword())){
                log.info("密码错误");
                return false;
            }
            log.info("id:{},password:{}",userLogin.getId(),userLogin.getPassword());
            log.info("o:{}",o);
            return true;


    }
    @PostMapping("/update")
    public boolean update(@RequestBody UserUpdate userUpdate){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdate.getId()));
        //String code=userUpdate.getCode();
        String address=userUpdate.getAddress();
        String email=userUpdate.getEmail();
        String password=userUpdate.getPassword();
        String etele=userUpdate.getEtele();
        if(Objects.isNull(user)) {
            log.info("no found user");
            return false;
        }
        user.setEmail(email);
        user.setEtele(etele);
        user.setPassword(password);
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;
    }



    @PostMapping("/update/address")
    public boolean updateaddress(@RequestBody UserUpdateAddress userUpdateAddress){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateAddress.getId()));
        String address=userUpdateAddress.getAddress();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setCode(userUpdateAddress.getAddress());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/email")
    public boolean updateemail(@RequestBody UserUpdateemail userUpdateemail){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateemail.getId()));
        String email=userUpdateemail.getEmail();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setEmail(userUpdateemail.getEmail());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/update/password")
    public Integer updatepassword(@RequestBody UserUpdatePassword userUpdatePassword){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdatePassword.getId()));
        String password=userUpdatePassword.getPassword();
        String oldpassword=userUpdatePassword.getOldpassword();
       // String renewpassword=userUpdatePassword.getRenewpassword();
        if(!oldpassword.equals(user.getPassword())){
            return 100;//新旧密码一致
        }
        //if(userUpdatePassword.getRenewpassword().equals(userUpdatePassword.getPassword())){
         //   return 200;//两次输入密码不一致
        //}
       else {
            user.setPassword(userUpdatePassword.getPassword());
            userMapper.updateById(user);
            log.info("user:{}", user);
            return 300;//修改成功
        }
    }

    @PostMapping("/update/etele")
    public boolean updateaddress(@RequestBody UserUpdateEtele userUpdateEtele){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userUpdateEtele.getId()));
        String address=userUpdateEtele.getEtele();
        if(Objects.isNull(user)){
            log.info("no found user");
            return false;
        }
        user.setEtele(userUpdateEtele.getEtele());
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }

    @PostMapping("/register")
    public boolean register(@RequestBody UserRegister userRegister){
       String code = userRegister.getCode();
       String password=userRegister.getPassword();
       String repassword=userRegister.getRepassword();
       String email =userRegister.getEmail();
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,userRegister.getId()));
        if(Objects.isNull(user)){
            log.info("未找到此账号");
            return false;
        }
        if(!code.equals(user.getCode())){
            log.info("身份证号与学号不匹配");
            return false;
        }
        if(!password.equals(repassword)){
            log.info("密码设置错误");
            return false;
        }
        user.setEmail(email);
        user.setPassword(password);
        userMapper.updateById(user);
        log.info("user:{}",user);
        return true;

    }
    @GetMapping("/selectallcourse")//查所有课

    public List<Aclass> selectcourse(){
       
            return aclassMapper.selectList(null);
        
    }

    @GetMapping("/selectcourse")
    public List<Aclass> selectmycourse(@RequestParam String major){

        return  aclassMapper.selectList(Wrappers.lambdaQuery(Aclass.class).eq(Aclass::getMajor, major));

    }

    @GetMapping("/selectallstudenttest") //测试用例
    public List<User> selectstudent(){
        return  userMapper.selectList(null);
    }

    @GetMapping("/selectalldorm") //查询所有寝室信息
    public List<Dorm> selectdorm(){
        return  dormMapper.selectList(null);
    }

    @GetMapping("/showdata")
    public  UserShow usershow(@RequestParam Integer id){
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,id));
        UserShow userShow = new UserShow();
        userShow.setDorm(user.getDorm());
        userShow.setAddr(user.getAddr());
        userShow.setCollege(user.getCollege());
        userShow.setId(user.getId());
        userShow.setAclass(user.getAclass());
        userShow.setCode(user.getCode());
        userShow.setName(user.getName());
        userShow.setEtele(user.getEtele());
        userShow.setEmail(user.getEmail());
        userShow.setMajor(user.getMajor());
        return userShow;
    }

    @GetMapping("/SelectDorm")
    public List<Dorm> selectDorm(@RequestParam(value = "dormId", required = true)String dormId){
        return userMapper.selectDorm(dormId);
    }

    @PostMapping("/newConsult")
    public boolean newConsult(@RequestParam Integer stuId, @RequestParam String content) {
        int affectedRows = userMapper.newConsult(stuId, content);
        return affectedRows > 0;//提问成功返回1
    }

    @PostMapping("searchQuestionsByKeyword")
    public List<Consult> searchQuestionsByKeyword(@RequestParam String keyword) {
        return userMapper.searchQuestionsByKeyword(keyword);//关键词搜索已有问题
    }
}

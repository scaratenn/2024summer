package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.coursemeth.CourseSelect;
import com.example.pojo.*;
import com.example.usermapper.AclassMapper;
import com.example.usermapper.DormMapper;
import com.example.usermapper.ManagerMapper;
import com.example.usermapper.UserMapper;
import com.example.usermeth.UserLogin;
import com.example.usermeth.UserRegister;
import com.example.usermeth.UserShow;
import com.example.usermeth.userupdate.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private AclassMapper aclassMapper;
    @Resource
    private DormMapper dormMapper;
    @Resource
    private ManagerMapper managerMapper;
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
        String oldpassword=userUpdatePassword.getOldpassword();//改之前的密码
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
       String etele=userRegister.getEtele();
       String name=userRegister.getName();
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
        user.setEtele(etele);
        user.setName(name);
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

    @GetMapping("/selectidcourse")
    public List<Aclass> selectidcourse(@RequestParam Integer id){
        log.info("id:{}",id);
        log.info("{}",aclassMapper.selectList(Wrappers.lambdaQuery(Aclass.class).eq(Aclass::getId, id)));
        return  aclassMapper.selectList(Wrappers.lambdaQuery(Aclass.class).eq(Aclass::getId, id));

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
    public boolean newConsult(@RequestBody Consult newconsult) {
        int affectedRows = userMapper.newConsult(newconsult.getStuId(), newconsult.getContent());
        if(affectedRows>0){
            log.info("已提问");
            return true;
        }         // 提问成功返回 true
        else{
            log.info("提问失败");
            return false;
        }
    }
    @PostMapping("searchQuestionsByKeyword")
    public List<Consult> searchQuestionsByKeyword(@RequestBody KeyWord consultKeyword) {
        List<Consult> qus=userMapper.searchQuestionsByKeyword(consultKeyword.getConsultKeyword());
        log.info("找到相似");
        return qus; // 提问成功返回qus
    }

    @PostMapping("/updateAnswerById")
    public boolean updateAnswerById(@RequestBody Consult ansconsult) {
        int affectedRows = managerMapper.updateAnswerById(ansconsult.getId(), ansconsult.getAnswer());
        log.info("回复成功");
        if(affectedRows > 0){
            return true;
        } // 如果影响的行数大于0，则认为回复成功
        else {
            log.info("回复失败");
            return false;
        }


}
    @PostMapping("update/StuDorm")
    //学生选宿舍，更新学生表
    public boolean updateStuDorm(@RequestBody SelectBednum selcBednum) {
        // 尝试更新学生表
        int flag = userMapper.updateStuDorm(selcBednum.getDormId(), selcBednum.getStuId());
        if (flag <= 0) {
            log.info("学生表更新失败");
            return false; // 如果学生表更新失败，则直接返回false
        }
        log.info("学生表已更新");
        String bednum= selcBednum.getBednum();
        // 学生表更新成功后，尝试更新宿舍表
        if(bednum.equals("host1")){
            return userMapper.updateDormId1(selcBednum.getDormId(), selcBednum.getStuId());
        }
        else if(bednum.equals("host2")){
            return userMapper.updateDormId2(selcBednum.getDormId(), selcBednum.getStuId());
        }
        else if(bednum.equals("host3")){
            return userMapper.updateDormId3(selcBednum.getDormId(), selcBednum.getStuId());
        }
        else if(bednum.equals("host4")){
            return userMapper.updateDormId4(selcBednum.getDormId(), selcBednum.getStuId());
        }
        else{
            log.info("宿舍已满，更新失败");
            return false;
        }
    }
}

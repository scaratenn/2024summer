package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.managermeth.*;
import com.example.pojo.*;
import com.example.usermapper.AclassMapper;
import com.example.usermapper.DormMapper;
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
@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private DormMapper dormMapper;
    @Resource
    private AclassMapper aclassMapper;


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
    public User select(@RequestParam(value = "id", required = false)Integer id){
        User o=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId, id));
        log.info("id:{}",id);
        log.info("o:{}",o);
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
    public boolean updatpassword(@RequestBody ManagerUpdatePassword managerUpdatePassword){
        Manager o= managerMapper.selectOne(Wrappers.<Manager>lambdaQuery().eq(Manager::getId, managerUpdatePassword.getId()));
        String password=managerUpdatePassword.getPassword();
        String oldpassword=managerUpdatePassword.getOldpassword();

       /* if(Objects.isNull(o)){
            log.info("no found user");
            return false;
        }*/
        String oldpasswordin=o.getPassword();
        if(!oldpasswordin.equals(oldpassword)){
            log.info("新旧密码bu一致");
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
        managerShow.setEmail(o.getEmail());
        return  managerShow;
    }

    @GetMapping("/getQuestions")
    public List<Consult> getQuestions(){
        return managerMapper.getQuestions();
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
    @GetMapping("/selectalldorm") //查询所有寝室信息
    public List<Dorm> selectdorm(){
        return  dormMapper.selectList(null);
    }

    @GetMapping("/SelectDorm")
    public Dorm selectDorm(@RequestParam(value = "dormId", required = false)String dormId){
        //log.info("查询成功");
        //Dorm dorm=userMapper.selectDorm1(dormId);
        log.info("dormid:{}",dormId);

        Dorm o= dormMapper.selectOne(Wrappers.<Dorm>lambdaQuery().eq(Dorm::getBuilding,dormId));
        log.info("o:",o);
        return o;
    }

   @PostMapping("/update/dorm/host1")
   public boolean updatehost1(@RequestBody ManagerUpdateDorm managerUpdateDorm){
       Dorm o= dormMapper.selectOne(Wrappers.<Dorm>lambdaQuery().eq(Dorm::getBuilding,managerUpdateDorm.getBuilding()));
       Integer host1=managerUpdateDorm.getHost();
       log.info("host1:{}",host1);
       if(Objects.isNull(o)){
           log.info("o:{}",o);
           log.info("no found dorm");
           return false;
       }
       o.setHost1(host1);
       dormMapper.updateById(o);
       log.info("dorm:{}",o);
       User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,managerUpdateDorm.getHost()));

       user.setDorm(managerUpdateDorm.getBuilding());
       userMapper.updateById(user);
       return true;

   }
    @PostMapping("/update/dorm/host2")
    public boolean updatehost2(@RequestBody ManagerUpdateDorm managerUpdateDorm){
        Dorm o= dormMapper.selectOne(Wrappers.<Dorm>lambdaQuery().eq(Dorm::getBuilding,managerUpdateDorm.getBuilding()));
        Integer host2=managerUpdateDorm.getHost();
        if(Objects.isNull(o)){
            log.info("o:",o);
            log.info("no found dorm");
            return false;
        }
        o.setHost2(host2);
        dormMapper.updateById(o);
        log.info("dorm:{}",o);
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,managerUpdateDorm.getHost()));
        user.setDorm(managerUpdateDorm.getBuilding());
        userMapper.updateById(user);
        return true;

    }
    @PostMapping("/update/dorm/host3")
    public boolean updatehost3(@RequestBody ManagerUpdateDorm managerUpdateDorm){
        Dorm o= dormMapper.selectOne(Wrappers.<Dorm>lambdaQuery().eq(Dorm::getBuilding,managerUpdateDorm.getBuilding()));
        Integer host3=managerUpdateDorm.getHost();
        if(Objects.isNull(o)){
            log.info("o:",o);
            log.info("no found dorm");
            return false;
        }
        o.setHost3(host3);
        dormMapper.updateById(o);
        log.info("dorm:{}",o);
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,managerUpdateDorm.getHost()));
        user.setDorm(managerUpdateDorm.getBuilding());
        userMapper.updateById(user);
        return true;

    }

    @PostMapping("/update/dorm/host4")
    public boolean updatehost4(@RequestBody ManagerUpdateDorm managerUpdateDorm){
        Dorm o= dormMapper.selectOne(Wrappers.<Dorm>lambdaQuery().eq(Dorm::getBuilding,managerUpdateDorm.getBuilding()));
        Integer host4=managerUpdateDorm.getHost();
        if(Objects.isNull(o)){
            log.info("o:",o);
            log.info("no found dorm");
            return false;
        }
        o.setHost4(host4);
        dormMapper.updateById(o);
        log.info("dorm:{}",o);
        User user=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,managerUpdateDorm.getHost()));
        user.setDorm(managerUpdateDorm.getBuilding());
        userMapper.updateById(user);
        return true;

    }

    @GetMapping("/delCourse")
    public boolean delCourse(@RequestParam Integer id){
        Aclass a=aclassMapper.selectOne(Wrappers.<Aclass>lambdaQuery().eq(Aclass::getId,id));
        if(Objects.isNull(a)){
            log.info("未查找到此课程");
            return false;
        }
        aclassMapper.deleteById(a);
        return true;

    }

    @PostMapping("/addcourse")
    public boolean addCourse(@RequestBody Aclass addcourse) {
        int affectedRows = managerMapper.addCourse(addcourse.getId(),addcourse.getName(),addcourse.getTeacher(),addcourse.getClassroom(),addcourse.getMajor(),addcourse.getTime(),addcourse.getVolumn());
        log.info("增加成功");
        if(affectedRows > 0){
            return true;
        } // 如果影响的行数大于0，则认为增加成功
        else {
            log.info("增加失败");
            return false;
        }
    }
    @PostMapping("update/course")
        public boolean updatecourse(@RequestBody Aclass a){
            log.info("id:{}",a.getId());
            Aclass b= aclassMapper.selectOne(Wrappers.<Aclass>lambdaQuery().eq(Aclass::getId,a.getId()));
            log.info("b:{}",b);
            if(Objects.isNull(b)){
                return false;
            }
            if(a.getClassroom()!=null){
            b.setClassroom(a.getClassroom());}
            if(a.getTime()!=null){
            b.setTime(a.getTime());}
            if(a.getTeacher()!=null){
            b.setTeacher(a.getTeacher());}
            if(a.getVolumn()!=null){
                b.setVolumn(a.getVolumn());
            }
            aclassMapper.updateById(b);
            log.info("b:{}",b);
            return true;
        }

}

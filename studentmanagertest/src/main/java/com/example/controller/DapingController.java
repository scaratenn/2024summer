package com.example.controller;

import com.example.pojo.*;
import com.example.usermapper.ManagerMapper;
import com.example.usermapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/daping")
@Slf4j

public class DapingController {
    @Resource
    private UserMapper userMapper;
    @Resource
    private ManagerMapper managerMapper;
    @RequestMapping("/student")
    public List<User> list(){
        return userMapper.selectList(null);
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

   // @ApiOperation(value = "查询不同报到日期人数")
    @GetMapping("/countSigndate")
    public List<SignDate> countSigndate() {
        return managerMapper.countSigndate();
    }

  //  @ApiOperation(value = "查询今日到校人数",notes = "根据提供的日期缩写（如'01'表示9月1日）查询当日到校人数，如果日期缩写不符合预期，则返回默认日期的到校人数。")
    @GetMapping("/countTodaySigndate")
    public List<SignDate> countTodaySigndate(@RequestParam(value = "signdate", required = true)String signdate) {
        switch (signdate) {
            case "31": return managerMapper.countOneSigndate("8.31");
            case "01": return managerMapper.countOneSigndate("9.01");
            case "02": return managerMapper.countOneSigndate("9.02");
            case "03": return managerMapper.countOneSigndate("9.03");
            case "04": return managerMapper.countOneSigndate("9.04");
            case "05": return managerMapper.countOneSigndate("9.05");
            default: return managerMapper.countSigndate();//如果都不是则返回默认天数
        }
    }

    //@ApiOperation(value = "查询各地区来源人数")
    @GetMapping("/countAddr")
    public List<Addr> countAddr() {
        List<Addr> numbers = new ArrayList<>();// 用于存储结果的addr对象列表
        List<String> addrStrs = managerMapper.queryAddr();
        Map<String, Integer> addrCounts = new HashMap<>();// 用于统计每个地区（市）的出现次数
        for (String addrStr: addrStrs) {
            String[] subStr = addrStr.split("-");// 使用"-"分割字符串，假设每个字符串包含多个地区信息，以"-"分隔
            for (String part: subStr) {
                if (part.endsWith("市")) {// 只统计以“市”结尾的地区
                    addrCounts.put(part, addrCounts.getOrDefault(part, 0) + 1);// 统计每个地区的出现次数
                }
            }
        }
        for (Map.Entry<String, Integer> entry : addrCounts.entrySet()) {
            numbers.add(new Addr(entry.getKey(), entry.getValue()));// 创建一个addr对象，包含地区名称和对应人数，并添加到列表中
        }
        return numbers;
    }

    //@ApiOperation(value = "查询已入住学生数")
    @GetMapping("/countBusyDorm")
    public int countBusybed(){
        return managerMapper.countBusyDorm();
    }

   // @ApiOperation(value = "查询空床位数")
    @GetMapping("/countAvailbed")
    public int countAvailBed(){
        int countDorm= managerMapper.countDorm();
        int busybed=countBusybed();
        return 4*countDorm-busybed;
    }

    //@ApiOperation(value = "查询总床位数")
    @GetMapping("/countbeds")
    public int countbeds(){
        int countDorm= managerMapper.countDorm();
        return 4*countDorm;
    }

}

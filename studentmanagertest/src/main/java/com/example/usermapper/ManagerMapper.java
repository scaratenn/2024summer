package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.College;
import com.example.pojo.Major;
import com.example.pojo.Manager;
import com.example.pojo.SignDate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("SELECT college, COUNT(*) AS count FROM student where college is not null GROUP BY college")
    List<College> countCollege();//查询各个学院人数

    @Select("SELECT COUNT(*) FROM student where id != 0")
    int countNumber();//查询总报到人数

    @Select("SELECT major, COUNT(*) AS count FROM student where major is not null GROUP BY major")
    List<Major> countMajor();//查询各个专业人数

    @Select("SELECT signdate, COUNT(*) AS count FROM student where signdate is not null GROUP BY signdate")
    List<SignDate> countSigndate();//查询不同日期报道人数（每天）

    @Select("SELECT signdate, COUNT(*) AS count FROM student where signdate = #{signdate} GROUP BY signdate")
    List<SignDate> countOneSigndate(String signdate);//具体某日到校人数

    @Select("SELECT addr from student where addr is not null")
    List<String> queryAddr();//查询并返回地址信息的字符串列表

    @Select("SELECT COUNT(*) AS totalCount FROM student WHERE dorm IS NOT NULL")
    int countBusyDorm();//查询已入住学生数

    @Select("SELECT COUNT(*) FROM dorm where building != 0 ")
    int countDorm();//查询宿舍数
}

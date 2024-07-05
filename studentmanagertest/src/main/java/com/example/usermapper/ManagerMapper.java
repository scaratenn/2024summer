package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ManagerMapper extends BaseMapper<Manager> {
    @Select("SELECT college, COUNT(*) AS count1 FROM student where college is not null GROUP BY college")
    List<College> countCollege();//查询各个学院人数

    @Select("SELECT COUNT(*) FROM student where id != 0")
    int countNumber();//查询总报到人数

    @Select("SELECT major, COUNT(*) AS count1 FROM student where major is not null GROUP BY major")
    List<Major> countMajor();//查询各个专业人数

    @Select("SELECT signdate, COUNT(*) AS count1 FROM student where signdate is not null GROUP BY signdate")
    List<SignDate> countSigndate();//查询不同日期报道人数（每天）

    @Select("SELECT signdate, COUNT(*) AS count1 FROM student where signdate = #{signdate} GROUP BY signdate")
    List<SignDate> countOneSigndate(String signdate);//具体某日到校人数

    @Select("SELECT addr from student where addr is not null")
    List<String> queryAddr();//查询并返回地址信息的字符串列表

    @Select("SELECT COUNT(*) AS totalCount FROM student WHERE dorm IS NOT NULL")
    int countBusyDorm();//查询已入住学生数

    @Select("SELECT COUNT(*) FROM dorm where building is not null ")
    int countDorm();//查询宿舍数

    @Select("select * from consult where answer is null or answer = ' '")
    List<Consult> getQuestions();//查询所有咨询问题（未解答的）

    @Update("UPDATE consult SET answer = #{answer} WHERE id = #{id}")
    int updateAnswerById(@Param("id") Integer id, @Param("answer") String answer);// 根据咨询ID更新回答

    @Select("SELECT buildnum,areanum,sum(count1) as totals FROM dorm where areanum is not null group by areanum,buildnum")
    List<Map<String, Object>> countAreanum();
}

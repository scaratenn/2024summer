package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.*;
import com.example.usermeth.UserShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

   /* @Select("SELECT signdate, COUNT(*) AS count FROM student where signdate is not null GROUP BY signdate")
    List<SignDate> countSigndate();//查询不同日期报道人数（每天）

    @Select("SELECT signdate, COUNT(*) AS count FROM student where signdate = #{signdate} GROUP BY signdate")
    List<SignDate> countOneSigndate(String signdate);//具体某日到校人数

    @Select("SELECT addr from student where addr is not null")
    List<String> queryAddr();//查询并返回地址信息的字符串列表

    @Select("SELECT COUNT(*) AS totalCount FROM student WHERE dorm IS NOT NULL")
    int countBusyDorm();//查询已入住学生数

    @Select("SELECT COUNT(*) FROM dorm where id != 0 ")
    int countDorm();//查询宿舍数*/
   @Select("SELECT * FROM dorm where building =#{dormID} ")
   List<Dorm> selectDorm(String dormID);//查询指定宿舍信息

   @Insert("INSERT INTO consult(stuId, content, answer) VALUES (#{stuId}, #{content}, null)")
   int newConsult(Integer stuId, String content);//学生发起提问
   @Select("SELECT * FROM consult WHERE content LIKE CONCAT('%', #{keyword}, '%')")
   List<Consult> searchQuestionsByKeyword(String keyword);
}

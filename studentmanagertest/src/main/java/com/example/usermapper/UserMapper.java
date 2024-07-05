package com.example.usermapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.*;
import com.example.usermeth.UserShow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

   @Select("SELECT * FROM dorm where building =#{dormID} ")
   Dorm selectDorm1(String dormID);

   @Insert("INSERT INTO consult(stuId, content, answer) VALUES (#{stuId}, #{content}, null)")
   int newConsult(Integer stuId, String content);//学生发起提问
   @Select("SELECT * FROM consult WHERE content LIKE CONCAT('%', #{keyword}, '%')")
   List<Consult> searchQuestionsByKeyword(String keyword);

   @Update("update student set dorm=#{dormId} where id =#{stuId} ")
   int updateStuDorm(String dormId,Integer stuId);//学生选宿舍，更新学生表

   @Update({
           "<script>",
           "UPDATE dorm",
           "SET ",
           "  <choose>",
           "    <when test=\"host1 = 0 or host1 = ''\">",
           "      host1 = #{stuId},",
           "      count = count + 1",
           "    </when>",
           "    <when test=\"host2 = 0 or host2 = ''\">",
           "      host2 = #{stuId},",
           "      count = count + 1",
           "    </when>",
           "    <when test=\"host3 = 0 or host3 = ''\">",
           "      host3 = #{stuId},",
           "      count = count + 1",
           "    </when>",
           "    <when test=\"host4 = 0 or host4 = ''\">",
           "      host4 = #{stuId},",
           "      count = count + 1",
           "    </when>",
           "  </choose>",
           "WHERE building = #{dormId}",
           "</script>"
   })
   int updateDormId(String dormId,Integer stuId);//学生选宿舍，更新宿舍表
}

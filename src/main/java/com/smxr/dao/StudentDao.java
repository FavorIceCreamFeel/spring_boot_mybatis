package com.smxr.dao;

import com.smxr.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @author LZY
 * @date 2019/8/23 11:27
 */
@Component
@Mapper
public interface StudentDao {
    @Select("select *from users")
//    @Results(id = "studentResults",value = {
//            @Result(property = "studentId",column = "student_Id"),
//            @Result(property = "studentName",column = "student_Name")
//    })            //声明数据关系 让实体类和数据库字段名一一对应   并起名以方便在此调用
    List<Student> queryStudent();
    @Select("select *from users where userName=#{param1}")
//    @ResultMap("studentResults")      //在此调用上面的对应关系
    Student queryStudentByName(String studentName);
    /**
     * 权限认证
     * @param accountId
     * @return
     */
    @Select("select  powerSign from power where" +
            " powerId in (select distinct powerId from role_power where" +
            " roleId in (select roleId from users_role where usersId=#{param1}))")
    List<String> queryPower(int accountId);
    /**
     * 权限授权
     */
    @Insert("insert into account_role values(#{param1},#{param2})")
    int insertAccount_role(int accountId,int roleId);
    /**
     * 注册账号
     */
    @Insert("insert into users values(null,#{userName},#{email},#{pwd},#{phoneNum},#{status})")
    int insertStudent(Student student);

    /**
     * 动态SQL where if 测试
     * @param student
     * @return
     */
    @Select("<script>"
            +"select *from users"
            +"<where>" +
            "<if test='userId != null and userId != &quot;&quot;'>"
            +"userId=#{userId}" +
            "</if>"
            +"<if test='userName != null and userName != &quot;&quot;'>"
            + "userName=#{userName}"+
            "</if>"
            + "</where>"
            +"</script>")
    Student queryStudentByTest(Student student);
}

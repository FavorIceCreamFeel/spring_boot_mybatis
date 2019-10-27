package com.smxr.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.dao.StudentDao;
import com.smxr.pojo.Student;
import com.smxr.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZY
 * @date 2019/8/23 11:31
 */
@Service
public class StudentServiceImpl implements StudentService{
    private static Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public PageInfo<Student> queryStudent(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Student> studentList = studentDao.queryStudent();
        System.out.println(studentList);
        return new PageInfo<Student>(studentList);
    }

    @Override
    public boolean insertStudent(Student student) {
        logger.info("用户注册====》start：");
        student.setPwd(passwordEncoder.encode(student.getPwd()));
        boolean boo = studentDao.insertStudent(student)==1?true:false;
        if (boo)
            logger.info("注册成功");
        else
            logger.info("注册失败");
        return boo;
    }

    /**
     * 权限授权
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Student student = studentDao.queryStudentByName(s);
        if (student!=null){
//            权限字符集
            StringBuilder stringBuilder = new StringBuilder();
//            查找用户权限字符集
            List<String> powerList = studentDao.queryPower(student.getUserId());
            for (int i=0;i<powerList.size();i++) {
                if (i==powerList.size()-1){
                    stringBuilder.append(powerList.get(i));
                }else {
                    stringBuilder.append(powerList.get(i));
                    stringBuilder.append(",");
                }
            }
            System.out.println(stringBuilder.toString());
            return new User(student.getUserName(),student.getPwd(), AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuilder.toString()));
        }
        return null;
          }
    /**
     * Test Controller
     * @return
     */
    @Override
    public ArrayList<Student> queryStudentByTest() {
        Student student1 = new Student();
        Student student2= new Student();
        student1.setUserId(1);
        student2.setUserName("liu");
        Student student3 = studentDao.queryStudentByTest(student1);
        Student student4 = studentDao.queryStudentByTest(student2);
        logger.info("测试接口数据1："+student3);
        logger.info("测试接口数据2："+student4);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student3);
        students.add(student4);
        return students;
    }

}

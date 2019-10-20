package com.smxr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smxr.pojo.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author LZY
 * @date 2019/8/23 11:30
 */
public interface StudentService extends UserDetailsService {
    PageInfo<Student> queryStudent(int pageNum, int pageSize);
    boolean insertStudent(Student student);
    ArrayList<Student> queryStudentByTest();
}

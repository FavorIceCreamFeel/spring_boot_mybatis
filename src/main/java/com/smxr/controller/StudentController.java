package com.smxr.controller;

import com.github.pagehelper.PageInfo;
import com.smxr.pojo.Student;
import com.smxr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author LZY
 * @date 2019/8/23 11:34
 */
@Controller
@RequestMapping("/Home")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping("/list")
    public String studentController2(@RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                         @RequestParam(name = "pageSize",required = false,defaultValue = "5") Integer pageSize ,
                                     Model model){
//        用户分页
        PageInfo<Student> studentPageInfo = studentService.queryStudent(pageNum, pageSize);
        model.addAttribute("studentPageInfo",studentPageInfo);
//        获取用户名字并传入页面
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName",name);
        model.addAttribute(hashMap);
        return "showTable";
    }

    /**
     * 动态SQL where if 测试接口
     * @return
     */
    @RequestMapping("/Test")
    @ResponseBody
    public ArrayList queryByTest(){
        ArrayList<Student> students = studentService.queryStudentByTest();
        return students;
    }
}

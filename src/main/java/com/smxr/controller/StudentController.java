package com.smxr.controller;

import com.github.pagehelper.PageInfo;
import com.smxr.pojo.Student;
import com.smxr.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger=LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @RequestMapping("/list")
    public String studentController2(@RequestParam(name = "pageNum",required = false,defaultValue = "1")Integer pageNum,
                                         @RequestParam(name = "pageSize",required = false,defaultValue = "5") Integer pageSize ,
                                     Model model){
        logger.info("分页请求到达：=========>>>>>>>>>>");
        logger.info("当前页数："+pageNum+"页面数量："+pageSize);
//        用户分页
        PageInfo<Student> studentPageInfo = studentService.queryStudent(pageNum, pageSize);
        logger.info("页面参数："+studentPageInfo);
        model.addAttribute("studentPageInfo",studentPageInfo);
//        获取用户名字并传入页面
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("获取用户名："+name);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName",name);
        model.addAttribute(hashMap);
        logger.info("请求完成返回页面模板========>>>>>>>>>");
        return "showTable";
    }

    /**
     * 动态SQL where if 测试接口
     * @return
     */
    @RequestMapping("/Test")
    @ResponseBody
    public ArrayList queryByTest(){
        logger.info("测试动态SQL接口====》start：");
        ArrayList<Student> students = studentService.queryStudentByTest();
        logger.info("测试结束======》end：");
        return students;
    }
}

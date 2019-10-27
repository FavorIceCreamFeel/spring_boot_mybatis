package com.smxr.controller;

import com.smxr.pojo.Student;
import com.smxr.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.LoggerListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Action;
import java.util.HashMap;
import java.util.NavigableMap;

/**
 * @author LZY
 * @date 2019/8/26 21:55
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private StudentService studentService;
    @RequestMapping("/login")
    public String studentErr(){
        logger.info("错误====》：重新登录");
        return "login";
    }
    @RequestMapping("/index")
    public String studentIndex(){
        logger.info("=======》进入首页");
        return "index";
    }
}

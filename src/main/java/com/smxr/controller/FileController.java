package com.smxr.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author LZY
 * @date 2019/10/21 21:54
 * 文件上传下载接口
 */
@Controller
@ResponseBody
@RequestMapping("/file")
public class FileController {
    private String filePath="/static/File";
    private String fileName=null;
    @RequestMapping("/upload")
    public String upload(@RequestParam(name = "filename") MultipartFile multipartFile){
        if (multipartFile == null){
            return "err";
        }
        System.out.println("---------------");
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }//判断文件是否存在，不存在就创建
        String originalFilename = multipartFile.getOriginalFilename();
        String replace = UUID.randomUUID().toString().replace("-", "");
        System.out.println("UUID修改后的随机生成：" + replace);
        fileName=originalFilename+replace;
        File saveFile = new File(filePath, fileName);
        try {
            multipartFile.transferTo(saveFile);
            System.out.println("333");
        } catch (IOException e) {
            return "err";
        }
        return "success";
    }
@RequestMapping("/download")
//public String download(String filePath,String fileName){
public String download(HttpServletResponse response){
    try {
         File file = new File(filePath+"/"+fileName);
         response.setCharacterEncoding("utf-8");
         response.setContentType("multipart/form-data");
        fileName = new String(fileName.getBytes(), "ISO-8859-1");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream inputStream = new FileInputStream(file);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int length;
        while ((length = inputStream.read(b)) > 0) {
            os.write(b, 0, length);
        }
        inputStream.close();
    } catch (Exception e) {
        return "err";
    }
    return "success";
}
}

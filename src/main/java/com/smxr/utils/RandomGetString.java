package com.smxr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author smxr
 * @date 2019/10/30
 * @time 12:08
 */
public class RandomGetString {
    private static Logger logger=LoggerFactory.getLogger(RandomGetString.class);
    /**
     * 获取指定长度的随机字符串
     */
    public static String getRandomString(int length){
        String str="qwertyuioplkjhgfdsazxcvbnm"+"0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<length;i++) {
            int i1 = random.nextInt(str.length());
            stringBuilder.append(str.charAt(i1));
        }
        return stringBuilder.toString();
    }
}

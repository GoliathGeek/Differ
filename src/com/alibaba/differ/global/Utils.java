/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.global;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * 类Utils.java的实现描述：TODO 类实现描述 
 * @author frank.shaof 2014年8月13日 下午6:10:17
 */
public class Utils {
    private static HashMap<String,Object> cacheMap=new HashMap<String,Object>();
    
    public static String getSizeText(long size){
        DecimalFormat df=new DecimalFormat("0.00");
        if(size>=0L && size<1024){
            return size+"B";
        }
        else if(size>=1024 && size<1048576){
            double fileSize=(double)size/1024;
            return df.format(fileSize)+"KB";
        }
        else{
            double fileSize=(double)size/1048576;
            return df.format(fileSize)+"MB";
        }
    }
    
    public static boolean isReadable(String fileName){
        int index = fileName.lastIndexOf('.');
        String extension="";
        if (index != -1) {
            extension=fileName.substring(index + 1).toLowerCase();
        }
        for(String suffix:UIConfig.READABLE_SUFFIX_ARR){
            if (extension.toLowerCase().equals(suffix)) {
                return true;
            }
        }
        return false;
    }
    
    public static String getExtension(String name) {
        int index = name.lastIndexOf('.');

        if (index == -1) {
            return "";
        } else {
            return name.substring(index + 1).toLowerCase();
        }
    }
    
    public static boolean isExpandable(String fileName){
        int index = fileName.lastIndexOf('.');
        String extension="";
        if (index != -1) {
            extension=fileName.substring(index + 1).toLowerCase();
        }
        for(String suffix:UIConfig.EXPANDABLE_SUFFIX_ARR){
            if (extension.toLowerCase().equals(suffix)) {
                return true;
            }
        }
        return false;
    }
    
    public static void addCache(String key, Object value){
        Utils.cacheMap.put(key, value);
    }
    
    public static Object getCache(String key){
        return Utils.cacheMap.get(key);
    }
}

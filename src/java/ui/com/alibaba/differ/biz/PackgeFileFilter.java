/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.alibaba.differ.ui.UIConfig;

/**
 * 类FileFilter.java的实现描述：文件过滤器
 * 
 * @author frank.shaof 2014年8月3日 下午3:11:13
 */
public class PackgeFileFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        for(String suffix:UIConfig.FILE_SUFFIX_ARR){
            if (extension.toLowerCase().equals(suffix)) {
                return true;
            }
        }
        return false;
    }

    private String getExtension(File f) {
        String name = f.getName();
        int index = name.lastIndexOf('.');

        if (index == -1) {
            return "";
        } else {
            return name.substring(index + 1).toLowerCase();
        }
    }

    public String getDescription() {
        return "包文件（WAR、JAR）";
    }
}

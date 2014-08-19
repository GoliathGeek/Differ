/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.global.Utils;

/**
 * ��FileFilter.java��ʵ���������ļ�������
 * 
 * @author frank.shaof 2014��8��3�� ����3:11:13
 */
public class PackgeFileFilter extends FileFilter {

    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = Utils.getExtension(f.getName());
        for(String suffix:UIConfig.OPENABLE_SUFFIX_ARR){
            if (extension.toLowerCase().equals(suffix)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return "���ļ���WAR��JAR��";
    }
}

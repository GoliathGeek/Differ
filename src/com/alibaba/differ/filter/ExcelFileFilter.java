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

import com.alibaba.differ.global.Utils;

/**
 * ��ExcelFileFilter.java��ʵ��������TODO ��ʵ������ 
 * @author frank.shaof 2014��8��8�� ����3:00:03
 */
public class ExcelFileFilter  extends FileFilter{
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return false;
        }

        String extension = Utils.getExtension(f.getName());
        if("xls".equals(extension)){
            return true;
        }
        else{
            return false;
        }
    }

    public String getDescription() {
        return "Excel";
    }
}

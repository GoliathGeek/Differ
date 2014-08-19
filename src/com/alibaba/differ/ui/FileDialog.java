/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.ui;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.zip.ZipEntry;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.global.Utils;

/**
 * ��FilePanel.java��ʵ���������ļ����ݿ� 
 * @author frank.shaof 2014��8��4�� ����5:41:38
 */
public class FileDialog extends JDialog{
    private JLabel jlblName;
    private JLabel jlblSize;
    private JLabel jlblModified;
    
    public FileDialog(ZipEntry entry){
        super();
        Box box=Box.createVerticalBox();
        jlblName=new JLabel("�ļ�ȫ����"+entry.getName());
        jlblSize=new JLabel("�ļ���С��"+Utils.getSizeText(entry.getSize()));
        jlblModified=new JLabel("�޸�ʱ�䣺"+new Date(entry.getTime()));
        box.add(Box.createVerticalStrut(20));
        box.add(jlblName);
        box.add(Box.createVerticalStrut(20));
        box.add(jlblSize);
        box.add(Box.createVerticalStrut(20));
        box.add(jlblModified);
        this.setTitle(entry.getName()+"����");
        this.setSize(UIConfig.FILE_PANEL_WIDTH, UIConfig.FILE_PANEL_HEIGHT);
        this.getContentPane().add(box);
        this.setModal(true);
    }
}

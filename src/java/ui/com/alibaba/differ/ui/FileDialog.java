/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.ui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 类FilePanel.java的实现描述：文件內容框 
 * @author frank.shaof 2014年8月4日 下午5:41:38
 */
public class FileDialog extends JDialog{
    private JTextArea jta;
    
    public FileDialog(String text){
        super();
        jta=new JTextArea();
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);
        jta.setText(text);
        jta.setEditable(false);
        JScrollPane jsp=new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(UIConfig.FILE_PANEL_WIDTH,UIConfig.FILE_PANEL_HEIGHT));
        this.setTitle("文件查看器");
        this.setSize(700, 500);
        this.getContentPane().add(jsp);
        this.setModal(true);
    }
}

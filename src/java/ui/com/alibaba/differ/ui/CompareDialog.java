/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.alibaba.differ.biz.TableExporter;
import com.alibaba.differ.global.UIConfig;

/**
 * 类CompareDialog.java的实现描述：TODO 类实现描述 
 * @author frank.shaof 2014年8月8日 下午2:14:37
 */
public class CompareDialog extends JDialog{
    private String[] columnNames;
    private JButton exportBtn;
    private JTable resultTable;
    
    public CompareDialog(Object[][] data){
        super();
        columnNames=new String[]{"包文件1","包文件2","操作","备注"};
        exportBtn=new JButton("导出结果");
        resultTable=new JTable(data,columnNames);
        resultTable.setRowHeight(25);
        JScrollPane jsp=new JScrollPane(resultTable);
        Box box=Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));
        box.add(jsp);
        box.add(Box.createVerticalStrut(20));
        box.add(exportBtn);
        box.add(Box.createVerticalStrut(20));
        this.setTitle("包文件比较结果");
        this.setSize(UIConfig.RESULT_PANEL_WIDTH, UIConfig.RESULT_PANEL_HEIGHT);
        this.getContentPane().add(box);
        this.setModal(true);
        exportBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TableExporter exporter=new TableExporter(resultTable);  
                try {
                    exporter.export();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }            
        });
    }
}

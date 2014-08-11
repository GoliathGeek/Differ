/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.alibaba.diff.listener.FileTreeListener;
import com.alibaba.differ.biz.FileTreeBuilder;
import com.alibaba.differ.biz.PackgeFileFilter;

/**
 * 类MainPanel.java的实现描述：程序面板
 * 
 * @author frank.shaof 2014年7月30日 下午4:02:48
 */
public class MainPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -7257377411110865585L;
    private JButton      jbtFile1;
    private JButton      jbtFile2;
    private JScrollPane  treePanel1;
    private JScrollPane  treePanel2;
    private JButton      jbtCompare;
    private JRadioButton jarRadio;
    private JRadioButton classRadio;
    private JLabel       levelLabel;

    public MainPanel(){
        // init all widgets
        jbtFile1 = new JButton("打开包文件1");
        jbtFile2 = new JButton("打开包文件2");
        jbtCompare = new JButton("开始比较");
        jarRadio = new JRadioButton(".jar", true);
        classRadio = new JRadioButton(".class");
        levelLabel=new JLabel("解析级别：");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(jarRadio);
        btnGroup.add(classRadio);
        treePanel1 = new JScrollPane();
        treePanel2 = new JScrollPane();
        Box boxFile1 = Box.createVerticalBox();
        Box boxFile2 = Box.createVerticalBox();
        Box boxFile1Top=Box.createHorizontalBox();
        treePanel1.setPreferredSize(new Dimension(UIConfig.TREE_SIZE_WIDTH, UIConfig.TREE_SIZE_HEIGHT));
        treePanel2.setPreferredSize(new Dimension(UIConfig.TREE_SIZE_WIDTH, UIConfig.TREE_SIZE_HEIGHT));
        treePanel1.setBorder(BorderFactory.createTitledBorder("包文件1的结构"));
        treePanel2.setBorder(BorderFactory.createTitledBorder("包文件2的结构"));
        // set box for file1
        boxFile1Top.add(levelLabel);
        boxFile1Top.add(jarRadio);
        boxFile1Top.add(classRadio);
        boxFile1Top.add(Box.createHorizontalStrut(20));
        boxFile1Top.add(jbtFile1);
        boxFile1.add(boxFile1Top);
        boxFile1.add(Box.createVerticalStrut(20));
        boxFile1.add(treePanel1);
        // set box for file2
        boxFile2.add(jbtFile2);
        boxFile2.add(Box.createVerticalStrut(20));
        boxFile2.add(treePanel2);
        // set whole layout
        this.add(Box.createVerticalStrut(20));
        this.add(boxFile1);
        this.add(boxFile2);
        this.add(jbtCompare);
        // set click listeners, render tree
        jbtFile1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                File file=MainPanel.this.getFileByChooser();
                JTree tree=MainPanel.this.buildFileTree(file);
                if(tree!=null){
                    MainPanel.this.treePanel1.setViewportView(tree);
                    tree.addTreeSelectionListener(new FileTreeListener(file,tree));
                }
            }
        });
        jbtFile2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                File file=MainPanel.this.getFileByChooser();
                JTree tree=MainPanel.this.buildFileTree(file);
                if(tree!=null){
                    MainPanel.this.treePanel2.setViewportView(tree);
                    tree.addTreeSelectionListener(new FileTreeListener(file,tree));
                }
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setTitle("包文件比较器");
        frame.add(new MainPanel());
        frame.setResizable(false);
        frame.setSize(900, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // file chooser initialization
    private File getFileByChooser(){
        JFileChooser fc = new JFileChooser();
        fc.setFileHidingEnabled(true);
        fc.setFileFilter(new PackgeFileFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            return file;
        }
        else{
            return null;
        }
    }
    
    // build tree
    private JTree buildFileTree(File file) {
        JTree tree=null;
        //jar level tree
        if(jarRadio.isSelected()){
            tree=FileTreeBuilder.buildFileTreeTilJar(file);
        }
        //class level tree
        else{
            //TODO
        }
        return tree;
    }
}

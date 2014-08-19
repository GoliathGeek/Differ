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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.alibaba.differ.biz.FileTreeBuilder;
import com.alibaba.differ.biz.PackageComparator;
import com.alibaba.differ.filter.PackgeFileFilter;
import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.listener.FileReadListener;

/**
 * 类MainPanel.java的实现描述：程序面板
 * 
 * @author frank.shaof 2014年7月30日 下午4:02:48
 */
public class MainPanel extends JPanel {

    private JButton           jbtFile1;
    private JButton           jbtFile2;
    private JScrollPane       treePanel1;
    private JScrollPane       treePanel2;
    private JButton           jbtCompare;
    private JButton           jbtExit;
    private JRadioButton      jarRadio;
    private JRadioButton      classRadio;
    private JRadioButton      plainRadio;
    private JLabel            levelLabel;
    private FileTreeBuilder   treeBuilder = new FileTreeBuilder();
    private PackageComparator comparator  = new PackageComparator();

    public MainPanel(){
        // init all widgets
        jbtFile1 = new JButton("打开包文件1");
        jbtFile2 = new JButton("打开包文件2");
        jbtCompare = new JButton("开始比较");
        jarRadio = new JRadioButton(".jar", true);
        classRadio = new JRadioButton(".class");
        plainRadio = new JRadioButton(".*");
        levelLabel = new JLabel("解析级别：");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(jarRadio);
        btnGroup.add(classRadio);
        btnGroup.add(plainRadio);
        treePanel1 = new JScrollPane();
        treePanel2 = new JScrollPane();
        Box boxFile1 = Box.createVerticalBox();
        Box boxFile2 = Box.createVerticalBox();
        Box boxCompare = Box.createHorizontalBox();
        treePanel1.setPreferredSize(new Dimension(UIConfig.TREE_SIZE_WIDTH, UIConfig.TREE_SIZE_HEIGHT));
        treePanel2.setPreferredSize(new Dimension(UIConfig.TREE_SIZE_WIDTH, UIConfig.TREE_SIZE_HEIGHT));
        treePanel1.setBorder(BorderFactory.createTitledBorder("包文件1的结构"));
        treePanel2.setBorder(BorderFactory.createTitledBorder("包文件2的结构"));
        // set box for file1
        boxFile1.add(Box.createVerticalStrut(20));
        boxFile1.add(jbtFile1);
        boxFile1.add(Box.createVerticalStrut(20));
        boxFile1.add(treePanel1);
        // set box for file2
        boxFile1.add(Box.createVerticalStrut(20));
        boxFile2.add(jbtFile2);
        boxFile2.add(Box.createVerticalStrut(20));
        boxFile2.add(treePanel2);
        // set whole layout
        this.add(Box.createVerticalStrut(20));
        this.add(boxFile1);
        this.add(boxFile2);
        boxCompare.add(levelLabel);
        boxCompare.add(jarRadio);
        boxCompare.add(classRadio);
        boxCompare.add(jbtCompare);
        this.add(boxCompare);
        // set click listeners, render tree
        jbtFile1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                File file = MainPanel.this.getFileByChooser();
                JTree tree = treeBuilder.buildFileTree(file,UIConfig.JAR_CACHE_KEY_1,UIConfig.CLASS_CACHE_KEY_1);
                if (tree != null) {
                    MainPanel.this.treePanel1.setViewportView(tree);
                    tree.addMouseListener(new FileReadListener(tree));
                }
            }
        });
        jbtFile2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                File file = MainPanel.this.getFileByChooser();
                JTree tree = treeBuilder.buildFileTree(file,UIConfig.JAR_CACHE_KEY_2,UIConfig.CLASS_CACHE_KEY_2);
                if (tree != null) {
                    MainPanel.this.treePanel2.setViewportView(tree);
                    tree.addMouseListener(new FileReadListener(tree));
                }
            }
        });
        // compare two wars
        jbtCompare.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                Object[][] data = null;
                if(jarRadio.isSelected()){
                    data=comparator.compareByJar();
                }
                else{
                    data=comparator.compareByClass();
                }
                if(data==null){
                    JOptionPane.showMessageDialog(null, "没有可供比较的包文件数据！");
                    return;
                }
                CompareDialog compareDialog = new CompareDialog(data);
                compareDialog.setVisible(true);
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setTitle("包文件比较器");
        frame.add(new MainPanel());
        frame.setResizable(false);
        frame.setSize(UIConfig.TREE_SIZE_WIDTH * 2 + 100, UIConfig.TREE_SIZE_HEIGHT + 180);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // file chooser initialization
    private File getFileByChooser() {
        JFileChooser fc = new JFileChooser();
        fc.setFileHidingEnabled(true);
        fc.setFileFilter(new PackgeFileFilter());
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            return file;
        } else {
            return null;
        }
    }
}

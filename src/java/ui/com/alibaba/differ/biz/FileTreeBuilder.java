/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.TreeSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 类FileTreeBuilder.java的实现描述：构建包的文件树
 * 
 * @author frank.shaof 2014年8月3日 下午3:24:40
 */
public class FileTreeBuilder {

    public static JTree buildFileTreeTilJar(File file) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
        try {
            ZipFile zipFile = new ZipFile(file, ZipFile.OPEN_READ);
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
            TreeSet<String> entrySet = new TreeSet<String>();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                entrySet.add(entry.getName());
            }
            for (String entry : entrySet) {
                DefaultMutableTreeNode treeNode=new DefaultMutableTreeNode(entry);
                root.add(treeNode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JTree(root);
    }
    
    public static JTree buildFileTreeTilClass(File file){
        return null;
    }
}

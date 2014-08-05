/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.diff.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alibaba.differ.ui.FileDialog;
import com.alibaba.differ.ui.UIConfig;

/**
 * 类FileTreeListener.java的实现描述：文件树监听器 
 * @author frank.shaof 2014年8月4日 下午6:46:32
 */
public class FileTreeListener implements TreeSelectionListener{
    private File file;
    private JTree tree;
    
    public FileTreeListener(File file, JTree tree){
        this.file=file;
        this.tree=tree;
    }

    public void valueChanged(TreeSelectionEvent evt) {
        DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        try {
            ZipFile zipFile = new ZipFile(file, ZipFile.OPEN_READ);
            ZipEntry entry=zipFile.getEntry(selectedNode.getUserObject().toString());
            if(!entry.isDirectory() && isReadable(entry.getName())){
                BufferedReader reader=new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
                String text="",line;
                while((line=reader.readLine())!=null){
                    text+=line+"\n";
                }
                reader.close();
                FileDialog dialog=new FileDialog(text);
                dialog.setVisible(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean isReadable(String fileName){
        int index = fileName.lastIndexOf('.');
        String extension="";
        if (index != -1) {
            extension=fileName.substring(index + 1).toLowerCase();
        }
        for(String suffix:UIConfig.FILE_SUFFIX_ARR){
            if (extension.toLowerCase().equals(suffix)) {
                return false;
            }
        }
        return true;
    }

}

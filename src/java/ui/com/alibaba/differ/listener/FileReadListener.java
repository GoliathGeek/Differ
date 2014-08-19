/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.listener;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.zip.ZipEntry;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.global.Utils;
import com.alibaba.differ.model.TreeNodeData;
import com.alibaba.differ.ui.FileDialog;

/**
 * 类FileTreeListener.java的实现描述：文件树监听器 
 * @author frank.shaof 2014年8月4日 下午6:46:32
 */
public class FileReadListener extends MouseAdapter{
    private JTree tree;
    
    public FileReadListener(JTree tree){
        this.tree=tree;
    }

    public void mouseClicked(MouseEvent evt){
        if(evt.getModifiers()==InputEvent.BUTTON1_MASK && evt.getClickCount()==2){
            DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if(selectedNode==null){
                return;
            }
            if(selectedNode.isRoot()){
                return;
            }
            ZipEntry entry=((TreeNodeData)selectedNode.getUserObject()).getEntry();
            if(!entry.isDirectory() && Utils.isReadable(entry.getName())){
                FileDialog dialog=new FileDialog(entry);
                dialog.setVisible(true);
            }
        }
    }

}

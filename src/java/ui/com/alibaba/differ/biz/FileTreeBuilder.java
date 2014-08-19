/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.global.Utils;
import com.alibaba.differ.model.TreeNodeData;

/**
 * 类FileTreeBuilder.java的实现描述：构建包的文件树
 * 
 * @author frank.shaof 2014年8月3日 下午3:24:40
 */
public class FileTreeBuilder {
    public JTree buildFileTree(File file, String jarKey, String classKey) {
        long time1=System.currentTimeMillis();
        
        if (file == null) {
            return null;
        }
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getAbsolutePath());
        //缓存Jar级别数据
        HashMap<String,ZipEntry> jarDataCache=new HashMap<String,ZipEntry>();
        //缓存class级别数据
        HashMap<String,ZipEntry> classDataCache=new HashMap<String,ZipEntry>();
        try {
            ZipFile zipFile = new ZipFile(file);
            Enumeration<ZipEntry> entries = (Enumeration<ZipEntry>) zipFile.entries();
            TreeMap<String,TreeNodeData> entryMap = new TreeMap<String,TreeNodeData>();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name=entry.getName();
                entryMap.put(name,new TreeNodeData(name,entry));
                jarDataCache.put(name, entry);
                classDataCache.put(name, entry);
            }
            //存储节点的队列，用于广度遍历树，依次设置子节点
            LinkedList<DefaultMutableTreeNode> nodeQueue=new LinkedList<DefaultMutableTreeNode>();
            //存储InputStream的队列，用于广度遍历
            LinkedList<InputStream> inputStreamQueue=new LinkedList<InputStream>();
            for (Map.Entry<String, TreeNodeData> entry : entryMap.entrySet()) {
                DefaultMutableTreeNode treeNode=new DefaultMutableTreeNode(entry.getValue());
                root.add(treeNode);
                //将符合后缀要求的压缩文件压入队列，广度遍历
                if(Utils.isExpandable(entry.getKey())){
                    nodeQueue.push(treeNode);
                    inputStreamQueue.push(zipFile.getInputStream(entry.getValue().getEntry()));
                }
            }
            //广度遍历，将可以展开的节点依次给出子节点
            while(!nodeQueue.isEmpty()){
                InputStream parentInputStream=inputStreamQueue.pop();
                DefaultMutableTreeNode parentNode=nodeQueue.pop();
                ZipInputStream zipInputStream = new ZipInputStream(parentInputStream);
                ZipEntry entry = zipInputStream.getNextEntry();
                while (entry != null) {
                    String name=entry.getName();
                    DefaultMutableTreeNode node=new DefaultMutableTreeNode(new TreeNodeData(name,entry));
                    parentNode.add(node);
                    classDataCache.put(name, entry);
                    //将符合后缀要求的压缩文件压入队列，广度遍历下去
                    if(Utils.isExpandable(parentNode.getUserObject().toString())){
                        inputStreamQueue.push(getInputStream(zipInputStream));
                        nodeQueue.push(node);
                    }
                    entry = zipInputStream.getNextEntry();
                }
//                parentInputStream.close();
//                zipInputStream.close();
                Utils.addCache(jarKey, jarDataCache);
                Utils.addCache(classKey, classDataCache);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long time2=System.currentTimeMillis();
        System.out.println(time2-time1);
        return new JTree(root);
    }
    
    private InputStream getInputStream(InflaterInputStream zipInputStream) {
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            byte[] buf = null;
            int length = 0;

            while ((length = zipInputStream.read(temp, 0, 1024)) != -1) {
                bout.write(temp, 0, length);
            }

            buf = bout.toByteArray();
            bout.close();
            return new ByteArrayInputStream(buf);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

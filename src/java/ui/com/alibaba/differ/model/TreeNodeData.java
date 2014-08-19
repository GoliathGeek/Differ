/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.model;

import java.util.zip.ZipEntry;

/**
 * 类TreeNodeData.java的实现描述：文件树节点数据类
 * @author frank.shaof 2014年8月7日 下午6:14:23
 */
public class TreeNodeData {
    private String data;
    private ZipEntry entry;
    
    public TreeNodeData(String data, ZipEntry entry){
        this.data=data;
        this.entry=entry;
    }
    
    /**
     * @return the data
     */
    public String getData() {
        return data;
    }
    
    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * @return the entry
     */
    public ZipEntry getEntry() {
        return entry;
    }
    
    /**
     * @param entry the entry to set
     */
    public void setEntry(ZipEntry entry) {
        this.entry = entry;
    }
    
    @Override
    public String toString(){
        return this.data;
    }
}

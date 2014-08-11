/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.model;

import java.util.TreeMap;

/**
 * 类ZipFile.java的实现描述：压缩文件内容
 * @author xueliang.cxl 2014年8月7日 下午4:49:49
 */
public class ZipData extends TreeMap<String, ZipData> implements Cloneable {

    // 一般文件的模板
    private static final ZipData normalFileTemplete  = new ZipData();

    private String               name;

    private Long                 size;
    /**
     * 
     */
    private static final long    serialVersionUID = 1L;

    public static ZipData getCloneTemplate() {
        return normalFileTemplete;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}

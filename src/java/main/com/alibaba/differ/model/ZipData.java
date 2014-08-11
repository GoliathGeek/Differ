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
 * ��ZipFile.java��ʵ��������ѹ���ļ�����
 * @author xueliang.cxl 2014��8��7�� ����4:49:49
 */
public class ZipData extends TreeMap<String, ZipData> implements Cloneable {

    // һ���ļ���ģ��
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

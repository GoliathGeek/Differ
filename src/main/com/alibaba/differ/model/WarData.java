/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.model;

import java.io.Serializable;
import java.util.Map;

/**
 * ��WarData.java��ʵ��������TODO ��ʵ������
 * @author xueliang.cxl 2014��3��3�� ����8:43:18
 */
public class WarData implements Serializable {

    /**  */
    private static final long serialVersionUID = -6017967989181821224L;
    
    private Map<String,JarData> jarDataMap;

    /**
     * @return the jarDataMap
     */
    public Map<String,JarData> getJarDataMap() {
        return jarDataMap;
    }

    /**
     * @param jarDataMap the jarDataMap to set
     */
    public void setJarDataMap(Map<String,JarData> jarDataMap) {
        this.jarDataMap = jarDataMap;
    }

}

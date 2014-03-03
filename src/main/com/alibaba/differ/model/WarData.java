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
 * 类WarData.java的实现描述：TODO 类实现描述
 * @author xueliang.cxl 2014年3月3日 下午8:43:18
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

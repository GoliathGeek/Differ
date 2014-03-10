/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 类JarData.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年3月3日 下午8:44:58
 */
public class JarData implements Serializable {

    /**  */
    private static final long      serialVersionUID = -637417581507152560L;
    private Map<String, ClassData> calssDataMap;

    /**
     * @return the calssDataMap
     */
    public Map<String, ClassData> getCalssDataMap() {
        return calssDataMap;
    }

    /**
     * @param calssDataMap the calssDataMap to set
     */
    public void setCalssDataMap(Map<String, ClassData> calssDataMap) {
        this.calssDataMap = calssDataMap;
    }
}

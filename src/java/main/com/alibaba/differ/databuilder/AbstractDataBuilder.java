/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.databuilder;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;

/**
 * ��AbstractDataBuilder.java��ʵ��������TODO ��ʵ������
 * @author xueliang.cxl 2014��8��5�� ����1:28:36
 */
public abstract class AbstractDataBuilder<S,T> implements DataBuilder<S,T> {

    protected AnalysisLevel analysisLevel;
    
    public AbstractDataBuilder(AnalysisLevel analysisLevel){
        this.analysisLevel =  analysisLevel;
    }

}

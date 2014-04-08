/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.differ.biz.Analysisor;

/**
 * ��Differ.java��ʵ��������TODO ��ʵ������
 * 
 * @author xueliang.cxl 2014��2��28�� ����4:00:55
 */
public class AnalysisWar {

    private static Logger logger = LoggerFactory.getLogger(AnalysisWar.class);

    public static void main(String[] paramArr) {
        if (paramArr.length < 1) {
            logger.error("need warPath!");
            System.exit(0);
        }
        new Analysisor().doAnalysis(paramArr[0]);
        System.out.println("analysis fin");
    }
}

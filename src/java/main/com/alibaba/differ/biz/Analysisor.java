/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ��Analysior.java��ʵ��������TODO ��ʵ������
 * 
 * @author xueliang.cxl 2014��3��11�� ����10:50:12
 */
public class Analysisor {

    static Logger logger = LoggerFactory.getLogger(Analysisor.class);

    public void doAnalysis(String warFilePath) {
        File warFile = new File(warFilePath);
        if (!warFile.exists()) {
            logger.error("file not exists : " + warFilePath);
            return;
        }
    }
}

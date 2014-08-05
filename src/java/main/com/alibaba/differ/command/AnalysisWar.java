/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.command;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.Analysisor;
import com.alibaba.differ.model.WarData;

/**
 * 类Differ.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年2月28日 下午4:00:55
 */
public class AnalysisWar {

    public static void main(String[] args) {

        if (args == null || args.length < 1) {
            System.out.println("illegal param");
            System.exit(0);
        }

        String warFilePath = args[0].trim();
        List<String> paramList = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            paramList.add(args[i].trim());
        }
        WarData warData = null;
        AnalysisLevel analysisLevel = paramList != null && paramList.size() > 0 ? AnalysisLevel.getLevel(paramList.get(0)) : null;
        Analysisor analysisor = new Analysisor();
        if (analysisLevel == null) {
            warData = analysisor.doAnalysis(warFilePath);
        } else {
            warData = analysisor.doAnalysis(warFilePath, analysisLevel);
        }
        System.out.println(warData.toString());
    }
}

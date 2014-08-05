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

import com.alibaba.differ.Analysisor;
import com.alibaba.differ.model.WarData;

/**
 * ��Differ.java��ʵ��������TODO ��ʵ������
 * 
 * @author xueliang.cxl 2014��2��28�� ����4:00:55
 */
public class AnalysisWar {

    public static void main(String[] args) {

        if (args == null || args.length < 1) {
            System.out.println("illegal param");
            System.exit(0);
        }

        Analysisor analysisor = new Analysisor();
        String warFilePath = args[0].trim();
        List<String> paramList = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            paramList.add(args[i].trim());
        }
        WarData warData = analysisor.doAnalysis(warFilePath, paramList);

        System.out.println(warData.toString());

    }
}

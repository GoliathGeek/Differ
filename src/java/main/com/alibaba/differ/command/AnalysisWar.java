/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.Analysisor;
import com.alibaba.differ.model.JarData;
import com.alibaba.differ.model.WarData;
import com.alibaba.differ.model.ZipData;

/**
 * 类Differ.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年2月28日 下午4:00:55
 */
public class AnalysisWar {

    private static final String defaultOutPutPath = "D:/";

    public static void main(String[] args) {

        if (args == null || args.length < 1) {
            System.err.println("illegal param");
            System.exit(0);
        }

        String warFilePath = args[0].trim();

        File file = new File(warFilePath);
        String fileName = null;
        if (!file.exists()) {
            System.err.println(warFilePath + " not exists!");
            System.exit(0);
        }
        fileName = file.getName();

        List<String> paramList = new ArrayList<String>();
        for (int i = 1; i < args.length; i++) {
            paramList.add(args[i].trim());
        }
        WarData warData = null;
        AnalysisLevel analysisLevel = paramList != null && paramList.size() > 0 ? AnalysisLevel.getLevel(paramList.get(0)) : null;
        String outPutFilePath = paramList != null && paramList.size() > 1 ? paramList.get(1) : defaultOutPutPath
                                                                                               + fileName
                                                                                               + "_result.dat";
        Analysisor analysisor = new Analysisor();
        long startTime = System.currentTimeMillis();
        if (analysisLevel == null) {
            warData = analysisor.doAnalysis(warFilePath);
        } else {
            warData = analysisor.doAnalysis(warFilePath, analysisLevel);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("analysis cost:" + (endTime - startTime));
        printWarData(warData, outPutFilePath);
    }

    /**
     * @param warData
     * @param outPutFilePath 
     */
    private static void printWarData(WarData warData, String outPutFilePath) {
        File fileOutPut = new File(outPutFilePath);
        if (fileOutPut.exists()) {
            fileOutPut.delete();
        }
        BufferedWriter bufWriter = null;
        try {
            bufWriter = new BufferedWriter(new FileWriter(fileOutPut));
            bufWriter.write(warData.getName() + "\n");
            for (Entry<String, ZipData> entryInWar : warData.entrySet()) {
                bufWriter.write("--- " + entryInWar.getKey() + "\n");
                ZipData zipData = entryInWar.getValue();
                if (zipData instanceof JarData) {
                    for (Entry<String, ZipData> entryInJar : zipData.entrySet()) {
                        bufWriter.write("------ " + entryInJar.getKey() + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufWriter != null) {
                try {
                    bufWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

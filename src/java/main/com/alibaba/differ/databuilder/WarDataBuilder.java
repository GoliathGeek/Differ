/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.databuilder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.DataProcesser;
import com.alibaba.differ.model.JarData;
import com.alibaba.differ.model.WarData;
import com.alibaba.differ.processer.JdkUnZipProcesser;

/**
 * 类AnalysisorResultBuilder.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年3月10日 上午11:04:38
 */
public class WarDataBuilder implements DataBuilder<String, WarData> {

    private WarData                                 warData       = null;

    private AnalysisLevel                           analysisLevel;

    DataProcesser<InputStream, Map<String, byte[]>> dataProcesser = new JdkUnZipProcesser();

    public WarDataBuilder(AnalysisLevel analysisLevel){
        this.analysisLevel = analysisLevel;
    }

    public WarData getResult() {
        return warData;
    }

    public WarData buildData(String warFilePath) {
        WarData warData = new WarData();
        Map<String, byte[]> origeData;
        try {
            origeData = dataProcesser.process(new FileInputStream(new File(warFilePath)));
            for (Entry<String, byte[]> entry : origeData.entrySet()) {
                JarData jarData = this.buildJarData(entry);
                if (jarData != null) {
                    warData.put(jarData.getName(), jarData);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return warData;
    }

    private JarData buildJarData(Entry<String, byte[]> jarEntry) {
        if (this.analysisLevel.getWeight() >= AnalysisLevel.J.getWeight()) {
            InputStream jarInputStream = new ByteArrayInputStream(jarEntry.getValue());
            DataBuilder<InputStream, JarData> jarDataBuilder = new JarDataBuilder(new JdkUnZipProcesser(),
                                                                                  analysisLevel);
            JarData jarData = jarDataBuilder.buildData(jarInputStream);
            jarData.setName(jarEntry.getKey());
            return jarData;
        }
        return null;
    }

}

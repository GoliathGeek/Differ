/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.databuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.Processer;
import com.alibaba.differ.model.JarData;
import com.alibaba.differ.model.WarData;
import com.alibaba.differ.processer.UnZipProcesser;

/**
 * 类AnalysisorResultBuilder.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年3月10日 上午11:04:38
 */
@SuppressWarnings("unchecked")
public class WarDataBuilder implements DataBuilder {

	private Processer unZipProcesser = new UnZipProcesser();

	private WarData warData = null;

	private AnalysisLevel analysisLevel;

	public WarDataBuilder(AnalysisLevel analysisLevel) {
		this.analysisLevel = analysisLevel;
	}

	public WarData getResult() {
		return warData;
	}

	public void buildData(Object origeData) {
		warData = new WarData();
		Map<String, byte[]> jarHolder = (Map<String, byte[]>) origeData;
		for (Entry<String, byte[]> entry : jarHolder.entrySet()) {
			JarData jarData = this.buildJarData(entry);
			warData.put(jarData.getName(), jarData);
		}

	}

	private JarData buildJarData(Entry<String, byte[]> jarEntry) {
		if (this.analysisLevel.getWeight() >= AnalysisLevel.C.getWeight()) {
			InputStream jarInputStream = new ByteArrayInputStream(jarEntry.getValue());
			DataBuilder jarDataBuilder = new JarDataBuilder(analysisLevel);
			unZipProcesser.process(jarInputStream, jarDataBuilder);
			JarData jarData = (JarData) jarDataBuilder.getResult();
			jarData.setName(jarEntry.getKey());
			return jarData;
		}
		return null;
	}

}

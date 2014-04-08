package com.alibaba.differ.databuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.Processer;
import com.alibaba.differ.model.ClassData;
import com.alibaba.differ.model.JarData;
import com.alibaba.differ.processer.ClassLoaderProcesser;

@SuppressWarnings("unchecked")
public class JarDataBuilder implements DataBuilder {

	private JarData jarData;

	private AnalysisLevel analysisLevel;

	private Processer classContextProcesser = new ClassLoaderProcesser();

	public JarDataBuilder(AnalysisLevel analysisLevel) {
		this.analysisLevel = analysisLevel;
	}

	public void buildData(Object origeData) {
		Map<String, byte[]> classDataHolder = (Map<String, byte[]>) origeData;
		jarData = new JarData();
		for (Entry<String, byte[]> entry : classDataHolder.entrySet()) {
			ClassData classData = this.buildJClassData(entry);
			jarData.put(classData.getName(), classData);
		}

	}

	private ClassData buildJClassData(Entry<String, byte[]> entry) {
		if (this.analysisLevel.getWeight() >= AnalysisLevel.M.getWeight()) {
			InputStream classInputStream = new ByteArrayInputStream(entry.getValue());
			DataBuilder dataBuilder = new ClassDataBuilder(analysisLevel);
			classContextProcesser.process(classInputStream, dataBuilder);
			ClassData classData = (ClassData) dataBuilder.getResult();
			jarData.setName(entry.getKey());
			return classData;
		}
		return null;
	}

	public JarData getResult() {
		return jarData;
	}

}

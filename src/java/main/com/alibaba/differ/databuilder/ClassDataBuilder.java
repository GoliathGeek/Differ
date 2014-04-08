package com.alibaba.differ.databuilder;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.model.ClassData;

public class ClassDataBuilder implements DataBuilder {

	private ClassData classData;

	private AnalysisLevel analisisLevel;

	public ClassDataBuilder(AnalysisLevel analysisLevel) {
		this.analisisLevel = analysisLevel;
	}

	public void buildData(Object origeData) {
		classData = new ClassData();
	}

	public Object getResult() {
		return classData;
	}

}

package com.alibaba.differ.databuilder;

import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.DataProcesser;
import com.alibaba.differ.model.ClassData;
import com.alibaba.differ.model.JarData;

public class JarDataBuilder extends AbstractDataBuilder<InputStream, JarData> {

    private DataProcesser<InputStream, Map<String, byte[]>> dataProcesser;

    public JarDataBuilder(DataProcesser<InputStream, Map<String, byte[]>> dataProcesser, AnalysisLevel analysisLevel){
        super(analysisLevel);
        this.dataProcesser = dataProcesser;
    }

    public JarData buildData(InputStream inputStream) {
        JarData jarData = new JarData();
        Map<String, byte[]> origeData = dataProcesser.process(inputStream);
        for (Entry<String, byte[]> entry : origeData.entrySet()) {
            ClassData classData = this.buildClassData(entry);
            if (classData != null) {
                jarData.put(classData.getName(), classData);
            }
        }
        return jarData;
    }

    private ClassData buildClassData(Entry<String, byte[]> entry) {
        if (super.analysisLevel.getWeight() >= AnalysisLevel.C.getWeight()) {
            DataBuilder<byte[], ClassData> dataBuilder = new ClassDataBuilder(analysisLevel);
            ClassData classData = dataBuilder.buildData(entry.getValue());
            classData.setName(entry.getKey());
            return classData;
        }
        return null;
    }

}

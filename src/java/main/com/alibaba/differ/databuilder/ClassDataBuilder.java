package com.alibaba.differ.databuilder;

import com.alibaba.differ.AnalysisLevel;
import com.alibaba.differ.model.ClassData;

public class ClassDataBuilder extends AbstractDataBuilder<byte[], ClassData> {

    public ClassDataBuilder(AnalysisLevel analysisLevel){
        super(analysisLevel);
    }

    public ClassData buildData(byte[] origeData) {
        return new ClassData();
    }
}

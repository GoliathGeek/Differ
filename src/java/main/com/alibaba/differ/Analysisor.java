package com.alibaba.differ;

import java.io.File;

import com.alibaba.differ.databuilder.WarDataBuilder;
import com.alibaba.differ.model.WarData;

public class Analysisor {

    public WarData doAnalysis(String warFilePath, AnalysisLevel analysisLevel) {
        File warFile = new File(warFilePath);
        if (!warFile.exists()) {
            return null;
        }
        return this.doWork(warFilePath, analysisLevel);

    }

    private WarData doWork(String warFilePath, AnalysisLevel analysisLevel) {

        DataBuilder<String, WarData> warDataBuilder = new WarDataBuilder(analysisLevel);
        return warDataBuilder.buildData(warFilePath);
    }

    public WarData doAnalysis(String warFilePath) {
        return this.doAnalysis(warFilePath, AnalysisLevel.J);
    }

}

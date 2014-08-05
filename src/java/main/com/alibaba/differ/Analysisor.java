package com.alibaba.differ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.alibaba.differ.databuilder.WarDataBuilder;
import com.alibaba.differ.model.WarData;
import com.alibaba.differ.processer.JdkUnZipProcesser;

public class Analysisor {

    public WarData doAnalysis(String warFilePath, List<String> paramList) {
        int paramCount = paramList.size();
        switch (paramCount) {
            case 0:
                return this.doAnalysis(warFilePath);
            case 1:
                AnalysisLevel analysisLevel = AnalysisLevel.getLevel(paramList.get(0));
                if (analysisLevel == null) {
                    return null;
                }
                return this.doAnalysis(warFilePath, analysisLevel);
        }
        return null;
    }

    private WarData doAnalysis(String warFilePath, AnalysisLevel analysisLevel) {
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

    private WarData doAnalysis(String warFilePath) {
        return this.doAnalysis(warFilePath, AnalysisLevel.J);
    }

}

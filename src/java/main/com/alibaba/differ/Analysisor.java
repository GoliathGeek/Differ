package com.alibaba.differ;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.alibaba.differ.databuilder.WarDataBuilder;
import com.alibaba.differ.model.WarData;
import com.alibaba.differ.processer.UnZipProcesser;

public class Analysisor {

	public void doAnalysis(String warFilePath, List<String> paramList) {
		int paramCount = paramList.size();
		switch (paramCount) {
		case 0:
			this.doAnalysis(warFilePath);
			break;
		case 1:
			this.doAnalysis(warFilePath, paramList.get(1));
			break;
		case 2:
			this.doAnalysis(warFilePath, paramList.get(1), paramList.get(2));
			break;
		default:
			this.doAnalysis(warFilePath, paramList.get(1), paramList.get(2));
		}
	}

	private void doAnalysis(String warFilePath, String outputPath, String analysisLevelStr) {
		File warFile = new File(warFilePath);
		if (!warFile.exists()) {
			// warFile not Exists
			return;
		}

		File output = new File(outputPath);
		if (!output.isDirectory()) {
			// outputPath not dir
			return;
		}

		AnalysisLevel analysisLevel = AnalysisLevel.getLevel(analysisLevelStr);
		if (analysisLevel == null) {
			// analysisLevel illegal
			return;
		}

		this.doWork(warFilePath, outputPath, analysisLevel);

	}

	private void doWork(String warFilePath, String outputPath, AnalysisLevel analysisLevel) {

		try {
			DataBuilder warDataBuilder = new WarDataBuilder(analysisLevel);
			UnZipProcesser unZipProcesser = new UnZipProcesser();
			unZipProcesser.process(new FileInputStream(new File(warFilePath)), warDataBuilder);
			ResultWriter resultWriter = new FileResultWriter();
			resultWriter.write((WarData) warDataBuilder.getResult(), outputPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void doAnalysis(String warFilePath, String outputPath) {
		this.doAnalysis(warFilePath, outputPath, AnalysisLevel.M.toString());
	}

	private void doAnalysis(String warFilePath) {
		this.doAnalysis(warFilePath, Constants.default_output_path);
	}

}

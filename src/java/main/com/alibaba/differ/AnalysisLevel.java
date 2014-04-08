package com.alibaba.differ;

import java.util.HashMap;
import java.util.Map;

public enum AnalysisLevel {
	J(1), C(2), M(3);

	private static Map<String, AnalysisLevel> holder = new HashMap<String, AnalysisLevel>();

	static {
		holder.put(J.toString(), J);
		holder.put(C.toString(), C);
		holder.put(M.toString(), M);
	}

	private int weight;

	AnalysisLevel(int weight) {
		this.weight = weight;
	}

	public static AnalysisLevel getLevel(String analysisLevelStr) {
		return holder.get(analysisLevelStr);
	}

	public int getWeight() {
		return weight;
	}

}

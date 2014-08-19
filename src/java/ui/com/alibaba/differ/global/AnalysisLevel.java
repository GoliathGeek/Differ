package com.alibaba.differ.global;

import java.util.HashMap;
import java.util.Map;

public enum AnalysisLevel {
    /** Jar */
    J(1),
    /** Class */
    C(2),
    /** Method */
    M(3);

    private static Map<String, AnalysisLevel> holder = new HashMap<String, AnalysisLevel>();

    static {
        holder.put(J.toString(), J);
        holder.put(C.toString(), C);
        holder.put(M.toString(), M);
    }

    private int                               weight;

    AnalysisLevel(int weight){
        this.weight = weight;
    }

    public static AnalysisLevel getLevel(String analysisLevelStr) {
        return holder.get(analysisLevelStr);
    }

    public boolean canDo(AnalysisLevel bizAnalysisLevel) {
        return this.getWeight() >= bizAnalysisLevel.getWeight();
    }

    public int getWeight() {
        return weight;
    }

}

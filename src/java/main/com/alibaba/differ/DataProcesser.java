package com.alibaba.differ;

public interface DataProcesser<S, T> {

    public T process(S data);
}

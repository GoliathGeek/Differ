package com.alibaba.differ;

public interface DataBuilder<S, T> {

    public T buildData(S origeData);

}

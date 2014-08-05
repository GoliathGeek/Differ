package com.alibaba.differ.processer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.differ.DataProcesser;

public class ClassLoaderProcesser implements DataProcesser<InputStream, Map<String, String>> {

    public Map<String, String> process(InputStream inputStream) {
        return new HashMap<String, String>();
    }

}

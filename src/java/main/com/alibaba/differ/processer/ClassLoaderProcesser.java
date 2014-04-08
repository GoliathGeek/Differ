package com.alibaba.differ.processer;

import java.io.InputStream;

import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.Processer;

public class ClassLoaderProcesser implements Processer {

	public void process(InputStream inputStream, DataBuilder builder) {
		builder.buildData(inputStream);
	}

}

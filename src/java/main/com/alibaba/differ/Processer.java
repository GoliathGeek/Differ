package com.alibaba.differ;

import java.io.InputStream;

public interface Processer {
	
	public void process(InputStream inputStream, DataBuilder builder);
}

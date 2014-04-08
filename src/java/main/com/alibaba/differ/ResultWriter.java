/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ;

import com.alibaba.differ.model.WarData;


/**
 * 类ResultWriter.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年3月10日 上午11:19:45
 */
public interface ResultWriter {

	public void write(WarData data, String outputPath);
}

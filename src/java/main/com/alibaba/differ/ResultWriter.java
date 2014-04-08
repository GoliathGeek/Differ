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
 * ��ResultWriter.java��ʵ��������TODO ��ʵ������
 * 
 * @author xueliang.cxl 2014��3��10�� ����11:19:45
 */
public interface ResultWriter {

	public void write(WarData data, String outputPath);
}

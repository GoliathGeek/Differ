/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.model;

import java.util.HashMap;

/**
 * ��ClassData.java��ʵ��������TODO ��ʵ������
 * @author xueliang.cxl 2014��3��3�� ����8:47:10
 */
public class ClassData extends HashMap<String, MethodData> {

	/**  */
	private static final long serialVersionUID = -8475339024304898974L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

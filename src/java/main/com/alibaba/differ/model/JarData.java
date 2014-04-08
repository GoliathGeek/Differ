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
 * 类JarData.java的实现描述：TODO 类实现描述
 * 
 * @author xueliang.cxl 2014年3月3日 下午8:44:58
 */
public class JarData extends HashMap<String, ClassData> {

	private String name;
	/**  */
	private static final long serialVersionUID = -637417581507152560L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

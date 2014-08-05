package com.alibaba.differ.command;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AnalysisWarTest {

	@Test
	public void testMain() {
		List<String> paramList = new ArrayList<String>();
		paramList.add("D:/AliCode/jetty_server/channelcenter/target/root.war");
		paramList.add("C");
		AnalysisWar.main(paramList.toArray(new String[paramList.size()]));
	}

}

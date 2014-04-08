package com.alibaba.differ;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.differ.model.ClassData;
import com.alibaba.differ.model.JarData;
import com.alibaba.differ.model.WarData;

public class FileResultWriter implements ResultWriter {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(Constants.default_res_file_name);

	public void write(WarData data, String outputPath) {

		String fileName = sdf.format(new Date()) + Constants.default_res_file_ext;
		File file = new File(outputPath + File.separator + fileName);

		if (file.exists()) {
			file.delete();
		}
		BufferedWriter buffWriter = null;
		try {
			buffWriter = new BufferedWriter(new FileWriter(file));
			List<String> keyList = this.getSortedKeyList(data);
			for (String key : keyList) {
				JarData jarData = data.get(key);
				buffWriter.write("=== jar : " + jarData.getName() + "\r");
				this.writeJarData(buffWriter, jarData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (buffWriter != null) {
				try {
					buffWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private List<String> getSortedKeyList(HashMap<String, ?> data) {
		List<String> keyList = new ArrayList<String>();
		for (String key : data.keySet()) {
			keyList.add(key);
		}
		Collections.sort(keyList);
		return keyList;
	}

	private void writeJarData(BufferedWriter buffWriter, JarData jarData) throws IOException {
		List<String> keyList = this.getSortedKeyList(jarData);
		for (String key : keyList) {
			ClassData classData = jarData.get(key);
			buffWriter.write(classData.getName() + "\r");
		}
	}
}

package com.alibaba.differ.processer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import com.alibaba.differ.Constants;
import com.alibaba.differ.DataBuilder;
import com.alibaba.differ.Processer;

public class UnZipProcesser implements Processer {

	/**
	 * @param zipFileInputStream
	 * @return
	 */
	public void process(InputStream inputStream, DataBuilder builder) {

		Map<String, byte[]> dataHolder = new HashMap<String, byte[]>();

		ZipInputStream zipInputStream = null;
		try {
			zipInputStream = new ZipInputStream(inputStream);
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			while (zipEntry != null) {
				if (this.needs(zipEntry)) {
					byte[] data = this.getData(zipInputStream);
					dataHolder.put(zipEntry.getName(), data);
					builder.buildData(dataHolder);
				}
				zipEntry = zipInputStream.getNextEntry();
			}
		} catch (ZipException z) {
			z.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (zipInputStream != null)
					zipInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private byte[] getData(InflaterInputStream zipInputStream) {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] temp = new byte[1024];
			byte[] buf = null;
			int length = 0;

			while ((length = zipInputStream.read(temp, 0, 1024)) != -1) {
				bout.write(temp, 0, length);
			}

			buf = bout.toByteArray();
			bout.close();
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean needs(ZipEntry zipEntry) {
		return zipEntry.getName().endsWith(Constants.jarFileExt) || zipEntry.getName().endsWith(Constants.classFileExt);
	}
}

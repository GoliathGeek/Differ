/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.alibaba.differ.filter.ExcelFileFilter;
import com.alibaba.differ.global.UIConfig;

/**
 * 类TableExporter.java的实现描述：TODO 类实现描述
 * 
 * @author frank.shaof 2014年8月8日 下午2:56:32
 */
public class TableExporter {

    private JTable       table;
    private JFileChooser fc = new JFileChooser();

    public TableExporter(JTable table){
        this.table = table;
    }

    public void export() throws IOException {
        fc.setFileFilter(new ExcelFileFilter());
        fc.setFileHidingEnabled(true);
        fc.setAcceptAllFileFilterUsed(false);
        int returnValue = fc.showSaveDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File file = fc.getSelectedFile();
        if(file.exists()){
            JOptionPane.showMessageDialog(null, "该文件已经存在，请重新输入文件名！");
            return;
        }
        FileOutputStream fos = new FileOutputStream(file + ".xls");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hs = wb.createSheet();
        TableModel tm = table.getModel();
        int row = tm.getRowCount();
        int cloumn = tm.getColumnCount();
        HSSFCellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style1.setFillForegroundColor(HSSFColor.ORANGE.index);
        style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont font1 = wb.createFont();
        font1.setFontHeightInPoints((short) 15);
        font1.setBoldweight((short) 700);
        style1.setFont(font);

        for (int i = 0; i < row + 1; i++) {
            HSSFRow hr = hs.createRow(i);
            for (int j = 0; j < cloumn; j++) {
                if (i == 0) {
                    String value = tm.getColumnName(j);
                    hs.setColumnWidth(j, UIConfig.EXCEL_COLUMN_WIDTH);
                    HSSFRichTextString srts = new HSSFRichTextString(value);
                    HSSFCell hc = hr.createCell((short) j);
                    hc.setCellStyle(style1);
                    hc.setCellValue(srts);
                } else {
                    if (tm.getValueAt(i - 1, j) != null) {
                        String value = tm.getValueAt(i - 1, j).toString();
                        HSSFRichTextString srts = new HSSFRichTextString(value);
                        HSSFCell hc = hr.createCell((short) j);
                        hc.setCellStyle(style);

                        if (value.equals("") || value == null) {
                            hc.setCellValue(new HSSFRichTextString(""));
                        } else {
                            hc.setCellValue(srts);
                        }
                    }
                }
            }
        }
        wb.write(fos);
        fos.close();
        JOptionPane.showMessageDialog(null, "导出Excel完成！");
    }
}

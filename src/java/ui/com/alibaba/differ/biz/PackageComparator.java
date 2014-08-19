/*
 * Copyright 2014 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 */
package com.alibaba.differ.biz;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.zip.ZipEntry;

import com.alibaba.differ.global.UIConfig;
import com.alibaba.differ.global.Utils;

/**
 * 类WarDataComparator.java的实现描述：WarData比较类
 * 
 * @author frank.shaof 2014年8月12日 下午9:27:30
 */
public class PackageComparator {

    public Object[][] compareByJar(){
        if(Utils.getCache(UIConfig.JAR_CACHE_KEY_1)==null || Utils.getCache(UIConfig.JAR_CACHE_KEY_2)==null){
            return null;
        }
        HashMap<String,ZipEntry> cacheMap1=(HashMap<String,ZipEntry>)Utils.getCache(UIConfig.JAR_CACHE_KEY_1);
        HashMap<String,ZipEntry> cacheMap2=(HashMap<String,ZipEntry>)Utils.getCache(UIConfig.JAR_CACHE_KEY_2);
        return compareMaps(cacheMap1,cacheMap2);
    }
    
    public Object[][] compareByClass(){
        if(Utils.getCache(UIConfig.CLASS_CACHE_KEY_1)==null || Utils.getCache(UIConfig.CLASS_CACHE_KEY_2)==null){
            return null;
        }
        HashMap<String,ZipEntry> cacheMap1=(HashMap<String,ZipEntry>)Utils.getCache(UIConfig.CLASS_CACHE_KEY_1);
        HashMap<String,ZipEntry> cacheMap2=(HashMap<String,ZipEntry>)Utils.getCache(UIConfig.CLASS_CACHE_KEY_2);
        return compareMaps(cacheMap1,cacheMap2);
    }
    
    
    private Object[][] compareMaps(HashMap<String,ZipEntry> map1,HashMap<String,ZipEntry> map2){
        LinkedList<CompareRecord> compareRecords=new LinkedList<CompareRecord>();
        //以包1文件为基准，判断“删除”和“修改”操作
        for (Map.Entry<String, ZipEntry> entry : map1.entrySet()) {
            ZipEntry entry1 = entry.getValue();
            if(entry1==null){
                continue;
            }
            String name1=entry1.getName();
            long size1=entry1.getSize();
            ZipEntry entry2=map2.get(name1);
            if(entry2==null){
                CompareRecord record=new CompareRecord(name1,"","删除"+Utils.getExtension(name1)+"文件","--");
                compareRecords.add(record);
            }
            else{
                long size2=entry2.getSize();
                if(size1!=size2){
                    CompareRecord record=new CompareRecord(name1,name1,"修改"+Utils.getExtension(name1)+"文件","包1中大小："+Utils.getSizeText(size1)+"；包2中："+Utils.getSizeText(size2));                        
                    compareRecords.add(record);
                }
            }
        }
        //以包2为基准，判断“增加”情况
        for(Map.Entry<String,ZipEntry> entry:map2.entrySet()){
            ZipEntry entry2 = entry.getValue();
            if (entry2 == null) {
                continue;
            }
            String name2 = entry2.getName();
            ZipEntry entry1=map1.get(name2);
            if(entry1==null){
                CompareRecord record=new CompareRecord("",name2,"新增"+Utils.getExtension(name2)+"文件","--");
                compareRecords.add(record);
            }
        }
        if(compareRecords.size()==0){
            return null;
        }
        Object[][] data=new Object[compareRecords.size()][4];
        for(int i=0;i<compareRecords.size();i++){
            CompareRecord record=compareRecords.get(i);
            data[i][0]=record.getName1();
            data[i][1]=record.getName2();
            data[i][2]=record.getOperation();
            data[i][3]=record.getDesc();
        }
        return data;
    }
    
    private class CompareRecord {

        private String name1;
        private String name2;
        private String operation;
        private String desc;

        public CompareRecord(String name1, String name2, String operation, String desc){
            this.name1 = name1;
            this.name2 = name2;
            this.operation = operation;
            this.desc = desc;
        }

        public String getName1() {
            return name1;
        }

        public String getName2() {
            return name2;
        }

        public String getOperation() {
            return operation;
        }

        public String getDesc() {
            return desc;
        }
    }
}

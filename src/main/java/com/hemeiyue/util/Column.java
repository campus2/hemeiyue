package com.hemeiyue.util;

import java.util.ArrayList;
import java.util.List;

public class Column {  
	//单元格内容</span>  
    private String content;  
    //字段名称，用户导出表格时反射调用  
    private String fieldName;  
    //这个单元格的集合  
    private List<Column> listColumn = new ArrayList<Column>();  
      
    public Column(String content,String fieldName){  
        this.content = content;  
        this.fieldName = fieldName;  
    }  
      
    public String getContent() {  
        return content;  
    }  
  
    public void setContent(String content) {  
        this.content = content;  
    }  
  
    public String getFieldName() {  
        return fieldName;  
    }  
    public void setFieldName(String fieldName) {  
        this.fieldName = fieldName;  
    }  
  
    public List<Column> getListColumn() {  
        return listColumn;  
    }  
  
    public void setListColumn(List<Column> listColumn) {  
        this.listColumn = listColumn;  
    }  
}  
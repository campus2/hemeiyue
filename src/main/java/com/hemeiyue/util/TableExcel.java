package com.hemeiyue.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class TableExcel<T> {

	 //表格标题  
    private String title;  
    //单元格宽度  
    private int colWidth = 20;  
    //行高度  
    private int rowHeight = 20;  
      
    private HSSFWorkbook workbook;  
      
    //表头样式  
    private HSSFCellStyle styleHead;  
    //主体样式  
    private HSSFCellStyle styleBody;  
    //日期格式化,默认yyyy-MM-dd HH:mm:ss  
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
      
    public TableExcel(String title,int colWidth,int rowHeight,String dateFormat){  
        this.colWidth = colWidth;  
        this.rowHeight = rowHeight;  
        this.title = title;  
        workbook = new HSSFWorkbook();   
        sdf = new SimpleDateFormat(dateFormat);  
        init();  
    }  
      
    public TableExcel(String title,int colWidth,int rowHeight){  
        this.colWidth = colWidth;  
        this.rowHeight = rowHeight;  
        this.title = title;  
        workbook = new HSSFWorkbook();   
        init();  
    }  
    
    public TableExcel(String title){  
        this.title = title;  
        workbook = new HSSFWorkbook();   
        init();  
    }  
      
      
    private void init(){  
        // 生成一个样式    
        styleHead = workbook.createCellStyle();    
        // 设置这些样式    
        //styleHead.setFillForegroundColor(HSSFColor.SKY_BLUE.index);   
        styleHead.setFillForegroundColor(HSSFColor.AQUA.index);   
        styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
        styleHead.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        styleHead.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        styleHead.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        styleHead.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        // 生成一个字体    
        HSSFFont font = workbook.createFont();    
        font.setColor(HSSFColor.VIOLET.index);    
        font.setFontHeightInPoints((short) 12);    
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
        // 把字体应用到当前的样式    
        styleHead.setFont(font);    
        // 生成并设置另一个样式    
        styleBody = workbook.createCellStyle();    
        styleBody.setFillForegroundColor(HSSFColor.WHITE.index);    
        styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);    
        styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);    
        styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);    
        styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);    
        styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);    
        styleBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
        styleBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);    
        // 生成另一个字体    
        HSSFFont font2 = workbook.createFont();    
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);    
        // 把字体应用到当前的样式    
        styleBody.setFont(font2);   
    } 
    
    /** 
     * 拆分sheet，因为每个sheet不能超过6526，否则会报异常 
     * @param data 
     * @param listColumn 
     * void 
     */  
    private void splitDataToSheets(List<T> data,List<Column> listColumn){  
          
        int dataCount = data.size();  
        int maxColumn = 65535;  
        int pieces = dataCount/maxColumn;  
        for(int i = 1; i <= pieces;i++){  
            HSSFSheet sheet = workbook.createSheet(this.title+i);  
            List<T> subList = data.subList((i-1)*maxColumn, i*maxColumn);  
            writeSheet(sheet,subList,listColumn);  
        }  
          
        HSSFSheet sheet = workbook.createSheet(this.title+(pieces+1));  
        writeSheet(sheet, data.subList(pieces*maxColumn, dataCount),listColumn);  
    }  
      
    /** 
     * 导出Excel,适用于web导出excel 
     *  
     * @param sheet 
     * @param data 
     */  
    private void writeSheet(HSSFSheet sheet, List<T> data,List<Column> listColumn) {  
        try {  
            sheet.setDefaultColumnWidth(colWidth);  
            sheet.setDefaultRowHeightInPoints(rowHeight);  
              
            createHead(listColumn,sheet);  
            writeSheetContent(listColumn,data,sheet);  
        } catch (Exception e) {  
            throw new RuntimeException(e);  
        }  
    }  
      
      
      
    /** 
     * 导出表格 
     * @param listColumn 
     * @param datas 
     * @return 
     * @throws Exception  
     */  
    public InputStream exportExcel(List<Column> listColumn,List<T> datas) throws Exception {  
          
        splitDataToSheets(datas,listColumn);  
        return save(workbook);  
    }  
      
      
    /** 
     * 导出表格 
     * @param listColumn 
     * @param datas 
     * @param FilePath 
     * @throws FileNotFoundException 
     * @throws IOException 
     * void 
     */  
    public void exportExcel(List<Column> listColumn,List<T> datas,String FilePath) throws FileNotFoundException, IOException{  
        splitDataToSheets(datas,listColumn);  
        save(workbook,FilePath);  
    }  
      
      
    /** 
     * 把数据写入到单元格 
     * @param listColumn 
     * @param datas 
     * @param sheet 
     * @throws Exception 
     * void 
     */  
    private void writeSheetContent(List<Column> listColumn,List<T> datas,HSSFSheet sheet) throws Exception{  
        HSSFRow row = null;  
        List<Column> listCol = getColumnList(listColumn);  
          
        for(int i = 0 , index = 2; i < datas.size(); i++ , index++){  
            row = sheet.createRow(index);//创建行  
              
            for(int j = 0; j < listCol.size(); j++){  
                Column c = listCol.get(j);  
                createCol(row,c,datas.get(i),j);   
            }  
              
        }  
    }  
      
    /** 
     * 把column的columnList整理成一个list<column> 
     * @param listColumn 
     * @return 
     * List<Column> 
     */  
    private List<Column> getColumnList(List<Column> listColumn){  
          
        List<Column> listCol = new ArrayList<Column>();  
        for(int i = 0; i < listColumn.size(); i++){  
            List<Column> list = listColumn.get(i).getListColumn();  
            if(list.size() > 0){  
                for(Column c : list){  
                    if(c.getFieldName() != null){  
                        listCol.add(c);  
                    }  
                      
                }  
            }else{  
                if(listColumn.get(i).getFieldName() != null){  
                    listCol.add(listColumn.get(i));  
                }  
                  
            }  
        }  
          
          
        return listCol;  
    }  
      
    /** 
     * 保存Excel到InputStream，此方法适合web导出excel 
     *  
     * @param workbook 
     * @return 
     */  
    private InputStream save(HSSFWorkbook workbook) {  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        try {  
            workbook.write(bos);  
            InputStream bis = new ByteArrayInputStream(bos.toByteArray());  
            return bis;  
        } catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);  
        }  
    }  
      
      
    private void save(HSSFWorkbook workbook,String filePath) throws FileNotFoundException, IOException{  
          
            workbook.write(new FileOutputStream(filePath));  
    }  
      
    /** 
     * 创建行 
     * @param row 
     * @param column 
     * @param v 
     * @param j 
     * @return 
     * @throws Exception 
     */  
    public int createRowVal(HSSFRow row,Column column,T v,int j) throws Exception{  
        //便利标题  
        if(column.getListColumn() != null && column.getListColumn().size() > 0){  
            for(int i = 0; i < column.getListColumn().size(); i++){  
                createRowVal(row,column.getListColumn().get(i),v,j);  
            }  
        }else{  
            createCol(row,column,v,j++);  
              
        }  
        return j;  
          
    }  
      
    /** 
     * 创建单元格 
     * @param row 
     * @param column 
     * @param v 
     * @param j 
     * @throws Exception 
     */  
    public void createCol(HSSFRow row,Column column,T v,int j) throws Exception{  
        HSSFCell cell = row.createCell(j);  //创建单元格  
        cell.setCellStyle(styleBody); //设置单元格样式  
        Class cls = v.getClass();  
        Field field = cls.getDeclaredField(column.getFieldName());  
        field.setAccessible(true); //设置些属性是可以访问的   
        if(field.get(v) != null){  
            Object value = field.get(v);  
            if(value instanceof Date){  
                value = parseDate((Date)value);  
            }  
              
            HSSFRichTextString richString = new HSSFRichTextString(value.toString());  
            cell.setCellValue(richString);  
        }  
    }  
      
      
    /** 
     * 时间转换 
     * @param date 
     * @return 
     * String 
     */  
    private  String parseDate(Date date){  
        String dateStr = "";  
        try{  
            dateStr = sdf.format(date);  
        } catch (Exception e){  
            e.printStackTrace();  
        }  
          
        return dateStr;  
    }  
      
      
    /** 
     * 创建表头 
     * @param listColumn 
     */  
    public void createHead(List<Column> listColumn,HSSFSheet sheetCo){  
         HSSFRow row = sheetCo.createRow(0);    
         HSSFRow row2 = sheetCo.createRow(1);  
           
         for(short i = 0, n = 0; i < listColumn.size(); i++){//i是headers的索引，n是Excel的索引    
            HSSFCell cell1 = row.createCell(n);   
            cell1.setCellStyle(styleHead); //设置表头样式   
            HSSFRichTextString text = null;    
            List<Column> columns = listColumn.get(i).getListColumn();  
            if(columns != null && columns.size() > 0){//双标题  
                text = new HSSFRichTextString(listColumn.get(i).getContent());   
                sheetCo.addMergedRegion(new CellRangeAddress(0, n, 0, (short) (n + columns.size() -1)));//创建第一行大标题  
                  
                short tempI = n;   
                for(int j = 0; j < columns.size(); j++){//添加标题样式  
                    HSSFCell cellT = row.createCell(tempI++);    
                     cellT.setCellStyle(styleHead);   
                }  
                for(int j = 0; j < columns.size(); j++){  //给标题复制  
                    HSSFCell cell2 = row2.createCell(n++);    
                     
                    cell2.setCellStyle(styleHead);    
                    cell2.setCellValue(new HSSFRichTextString(columns.get(j).getContent()));     
               }  
            }else{//单标题  
                //sheetCo.setColumnWidth(i, columns.get(i).getContent().getBytes().length*2*256);   
                HSSFCell cell2 = row2.createCell(n);    
                cell2.setCellStyle(styleHead);    
                text = new HSSFRichTextString(listColumn.get(i).getContent());    
                sheetCo.addMergedRegion(new CellRangeAddress(0, n, 1, n));
                n++;  
            }  
            cell1.setCellValue(text);  
         }  
          
          
    }  
  
    public int getColWidth() {  
        return colWidth;  
    }  
  
    public void setColWidth(int colWidth) {  
        this.colWidth = colWidth;  
    }  
  
    public int getRowHeight() {  
        return rowHeight;  
    }  
  
    public void setRowHeight(int rowHeight) {  
        this.rowHeight = rowHeight;  
    }  
  
    public HSSFWorkbook getWorkbook() {  
        return workbook;  
    }  
  
    public void setWorkbook(HSSFWorkbook workbook) {  
        this.workbook = workbook;  
    }  
  
    public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public HSSFCellStyle getStyleHead() {  
        return styleHead;  
    }  
  
    public void setStyleHead(HSSFCellStyle styleHead) {  
        this.styleHead = styleHead;  
    }  
  
    public HSSFCellStyle getStyleBody() {  
        return styleBody;  
    }  
  
    public void setStyleBody(HSSFCellStyle styleBody) {  
        this.styleBody = styleBody;  
    }
}

package com.hemeiyue.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import com.hemeiyue.common.ActivityUserModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON转EXCEL
 * @author a2338
 *
 */
public class ExcelUtil {
	@SuppressWarnings("unchecked")
	public static JSONObject createExcel(String src, JSONObject json) {  
        JSONObject result = new JSONObject(); // 用来反馈函数调用结果  
        try {  
            // 新建文件  
            File file = new File(src);  
            file.createNewFile();  
  
            OutputStream outputStream = new FileOutputStream(file);// 创建工作薄  
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);  
            WritableSheet sheet = writableWorkbook.createSheet("First sheet", 0);// 创建新的一页  
            
            JSONArray jsonArray = json.getJSONArray("data");// 得到data对应的JSONArray
            Label label; // 单元格对象  
            int column = 0; // 列数计数  
  
            
            // 将第一行信息加到页中。如：姓名、年龄、性别  
            JSONObject first = jsonArray.getJSONObject(0);  
            Iterator<String> iterator = first.keys(); // 得到第一项的key集合  
            while (iterator.hasNext()) { // 遍历key集合  
                String key = (String) iterator.next(); // 得到key  
                label = new Label(column++, 0, key); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页  
            }  
		  
            // 遍历jsonArray  
            for (int i = 0; i < jsonArray.size(); i++) {  
                JSONObject item = jsonArray.getJSONObject(i); // 得到数组的每项  
                iterator = item.keys(); // 得到key集合  
                column = 0;// 从第0列开始放  
                while (iterator.hasNext()) {  
                    String key = iterator.next(); // 得到key  
                    String value = item.getString(key); // 得到key对应的value  
                    label = new Label(column++, (i + 1), value); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                    sheet.addCell(label); // 将单元格加到页  
                }  
            }  
            writableWorkbook.write(); // 加入到文件中  
            writableWorkbook.close(); // 关闭文件，释放资源  
        } catch (Exception e) {  
            result.put("result", "failed"); // 将调用该函数的结果返回  
            result.put("reason", e.getMessage()); // 将调用该函数失败的原因返回  
            return result;  
        }  
  
        result.put("result", "successed");  
        return result;  
    }  
	
	/**
	 * 导出活动参与表excel
	 * @param src
	 * @param json
	 * @return
	 */
	public static File createActivityExcel(String src, List<ActivityUserModel> list) {  
        File file = null;
        try {  
            // 新建文件  
            file = new File(src);  
            file.createNewFile();  
  
            OutputStream outputStream = new FileOutputStream(file);// 创建工作薄  
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(outputStream);  
            WritableSheet sheet = writableWorkbook.createSheet("预定表", 0);// 创建新的一页  
            
            Label label; // 单元格对象  
            int column = 0; // 列数计数  
            
            // 将第一行信息加到页中。如：姓名、年龄、性别  
            label = new Label(column++, 0, "报名者姓名"); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
            sheet.addCell(label);  						// 将单元格加到页 
            label = new Label(column++, 0, "学号");
            sheet.addCell(label);
            label = new Label(column++, 0, "联系方式");
            sheet.addCell(label);
            label = new Label(column++, 0, "班级");
            sheet.addCell(label);
            label = new Label(column++, 0, "是否签到");
            sheet.addCell(label);
            
            // 遍历jsonArray  
            for (int i = 0; i < list.size(); i++) {  
            	ActivityUserModel au = list.get(i); // 得到数组的每项  
                column = 0;// 从第0列开始放  
                label = new Label(column++, (i + 1), au.getUserName()); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页  
                label = new Label(column++, (i + 1), au.getStudentNum()); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页  
                label = new Label(column++, (i + 1), au.getPhone()); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页  
                label = new Label(column++, (i + 1), au.getClassRoom()); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页 
                label = new Label(column++, (i + 1), au.getStatus()==0?"已签到":"未签到"); // 第一个参数是单元格所在列,第二个参数是单元格所在行,第三个参数是值  
                sheet.addCell(label); // 将单元格加到页  
            }  
            writableWorkbook.write(); // 加入到文件中  
            writableWorkbook.close(); // 关闭文件，释放资源  
            return file; 
        } catch (Exception e) {  
            return null;  
        }  
    }  
	
	public static void main(String[] args) {
		
	}
}

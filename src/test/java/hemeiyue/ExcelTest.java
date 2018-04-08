package hemeiyue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hemeiyue.entity.ActivityUser;
import com.hemeiyue.entity.Users;
import com.hemeiyue.util.ExcelUtil;
import com.hemeiyue.util.JSONUtil;

import net.sf.json.JSONObject;

public class ExcelTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
//		List<Column> listColumn = new ArrayList<Column>();//用于存放第一行单元格  
//        
//        List<Column> list2 = new ArrayList<Column>();//用于存放第一列第二行的单元格  
//        list2.add(new Column("标题1","value1"));//创建一列，value1 表示这一列需要导出字段的值  
//        list2.add(new Column("标题2","value1"));  
//        list2.add(new Column("标题3","value1"));  
//        list2.add(new Column("标题4","value1"));  
//        list2.add(new Column("标题5","value1"));  
//          
//        List<Column> list3  = new ArrayList<Column>();//用于存放第二列第二行的单元格  
//        list3.add(new Column("标题6","value2"));  
//        list3.add(new Column("标题7","value2"));  
//        list3.add(new Column("标题8","value2"));  
//        list3.add(new Column("标题9","value2"));  
//          
//        Column c1 = new Column("标题1",null);//创建第一行大标题,大标题的fieldName 为 null  
//        c1.setListColumn(list2);//所属c1的单元格都赋值给c1  
//        Column c2 = new Column("标题2",null);  
//        c2.setListColumn(list3);  
//        listColumn.add(c1);  
//        listColumn.add(c2);  
//          
//          
//        List<Schools> valueList = new ArrayList<>();//需要导出的数据  
//        valueList.add(new Schools("广济寺", 1));  
//        valueList.add(new Schools("广济寺", 1));  
//        valueList.add(new Schools("广济寺", 1));  
//        valueList.add(new Schools("广济寺", 1));
//        valueList.add(new Schools("广济寺", 1));  
//        valueList.add(new Schools("广济寺", 1));
//          
//        TableExcel<Schools> ta = new TableExcel<Schools>("表格",15,20);  
//        ta.exportExcel(listColumn, valueList,"//media//cedo//644A01D2114857BF//outExcel.xls");
		
//		ExcelUtil.createExcel("//media//cedo//644A01D2114857BF//outExcel.xls", json);
	}

}

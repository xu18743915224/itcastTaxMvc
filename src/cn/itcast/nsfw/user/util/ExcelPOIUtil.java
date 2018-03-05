package cn.itcast.nsfw.user.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.nsfw.user.entity.User;

//POI操作  可以所有版本的excel(xls/xlsx)   导入 和导出  excel
public class ExcelPOIUtil {
/*-->对于只操作03版本即以前版本excel需要jar	(poi-3.11-20141221.jar)
 *-->如果需要同事对07及以后版本进行操作则需要jar (poi-ooxml-3.11-20141221.jar/poi-ooxml-schemas-3.11-20141221.jar)      
 *-->poi的基础包   一共加上面5个包都使用		(xmlbeans-2.6.0.jar/dom4j-1.6.1.jar)
 *
 * ---------------------------excel基础元素
 * 工作簿				xxx.xls
 * 工作表	(属于工作薄)	sheet
 * 行	(属于工作表)	row
 * 单元格	(属于行,由行和列确定的)cell
 * ---------------------------操作excel
 * 1.创建工作博 
 * 2.创建工作表
 * 3.创建行
 * 4.创建单元格
 * ---------------------------excel样式
 * 合并单元格对象(CellRangeAddress)属于工作薄,运作于工作表
 * CellRangeAddress(int firstRow,int lastRow,int firstCol,int lastCol); 起始行号	 结束行号	 起始列号	 结束列号
 * 
 * 样式是属于工作簿的,运用于单元格cell
 * 字体是属于工作簿的,加载于样式;运用于单元格cell
 * 
 */
	@Test//————————————————————————————————————————————————————————————————————————————————————————————————————创建03excel写入
	public void testWrite03Excel() throws IOException{									//创建excel并且写入数据
		//1创建工作薄xxx.xls
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2创建工作表sheet
		HSSFSheet sheet=workbook.createSheet("test");	//指定工作表明test
		//3创建行 (创建第四行
		HSSFRow row=sheet.createRow(3);
		//4创建单元格(创建第四列  即单元格的交汇处写入数据
		HSSFCell cell=row.createCell(3);
		cell.setCellValue("Hello World");
		//------此时此刻这个excel已经创建完了 但是在内存中我们看不见 需要内存中拿出来输出到指定位置   即 输出到硬盘
		//创建一个输出流FileOutputStream-->输出到硬盘(输出到指定硬盘位置并且起一个名字)  使用输出流将内存的数据 
		FileOutputStream outputStream=new FileOutputStream("D:\\itcast\\测试.xls");
		//将excel数据放到输出流上 输出到指定位置
		workbook.write(outputStream);
		//关闭工作薄  关闭输出流
		workbook.close();
		outputStream.close();
	}
	@Test//————————————————————————————————————————————————————————————————————————————————————————————————————创建07excel写入
	public void testWrite07Excel() throws IOException{									//创建excel并且写入数据
		//1创建工作薄xxx.xls
		XSSFWorkbook workbook=new XSSFWorkbook();
		//2创建工作表sheet
		XSSFSheet sheet=workbook.createSheet("test");	//指定工作表明test
		//3创建行 (创建第四行
		XSSFRow row=sheet.createRow(3);
		//4创建单元格(创建第四列  即单元格的交汇处写入数据
		XSSFCell cell=row.createCell(3);
		cell.setCellValue("Hello World");
		//------此时此刻这个excel已经创建完了 但是在内存中我们看不见 需要内存中拿出来输出到指定位置   即 输出到硬盘
		//创建一个输出流FileOutputStream-->输出到硬盘(输出到指定硬盘位置并且起一个名字)  使用输出流将内存的数据 
		FileOutputStream outputStream=new FileOutputStream("D:\\itcast\\测试.xlsx");
		//将excel数据放到输出流上 输出到指定位置
		workbook.write(outputStream);
		//关闭工作薄  关闭输出流
		workbook.close();
		outputStream.close();
	}
	@Test//————————————————————————————————————————————————————————————————————————————————————————————————————读取03或07excel
	public void testRead03And07Excel() throws IOException{									//指定excel并且读取该excel数据
		String fileName="D:\\itcast\\测试.xls";
		String houZhuiMing=fileName.substring(fileName.lastIndexOf("."));
		boolean is03Excel;
		if(".xls".equals(houZhuiMing)){
			is03Excel=true;
		}else{
			is03Excel=false;
		}
		//读取文件需要创建一个输入流FileInputStream
		FileInputStream inputStream=new FileInputStream(fileName);
		//1读取工作薄xxx.xls   接收输入流
		Workbook workbook=is03Excel?new HSSFWorkbook(inputStream):new XSSFWorkbook(inputStream);
		//2读取工作表sheet
		Sheet sheet=workbook.getSheetAt(0);	//读取第一个工作表sheet
		//3读取行 (读取第四行
		Row row=sheet.getRow(3);
		//4读取单元格(读取第四列  即单元格的交汇处读取数据
		Cell cell=row.getCell(3);
		System.out.println("测试.xls或.xlsx的第一个工作表的第三行和第三列的单元格数据是:"+cell.getStringCellValue());
		
		//关闭工作薄  关闭输入流
		workbook.close();
		inputStream.close();
	}
	@Test//————————————————————————————————————————————————————————————————————————————————————————————————————创建07excel写入样式css
	public void testWrite07ExcelStyle() throws IOException{									//创建excel并且写入数据
		//1创建工作薄xxx.xls
		XSSFWorkbook workbook=new XSSFWorkbook();
		//1.1创建合并单元格对象,合并第三行的第4列到第5列
		CellRangeAddress cellRangeAddress=new CellRangeAddress(3,3,3,4);//起始行号3	 结束行号3	 起始列号3	 结束列号4
		//1.2创建单元格样式
		XSSFCellStyle style=workbook.createCellStyle();
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3创建字体
		XSSFFont font=workbook.createFont();
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD); //加粗字体
		font.setFontHeightInPoints((short) 16);				  //设置字体大小为size 16
		//样式里面加载字体
		style.setFont(font);
		//1.4单元格背景
		//1.4.1设置填充模式
		style.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);  //根据设置前景色的颜色而改变即1.4.3  红色
		//1.4.2设置填充背景色
		style.setFillBackgroundColor(new XSSFColor(Color.RED));
		//1.4.3设置填充前景色
		style.setFillForegroundColor(new XSSFColor(Color.RED));
		
		//2创建工作表sheet
		XSSFSheet sheet=workbook.createSheet("test");	//指定工作表明test
		//2.1加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		//3创建行 (创建第四行
		XSSFRow row=sheet.createRow(3);
		//4创建单元格(创建第四列  即单元格的交汇处写入数据
		XSSFCell cell=row.createCell(3);
		//加载样式******属于工作簿运用于单元格cell
		cell.setCellStyle(style);
		cell.setCellValue("Hello World");
		//------此时此刻这个excel已经创建完了 但是在内存中我们看不见 需要内存中拿出来输出到指定位置   即 输出到硬盘
		//创建一个输出流FileOutputStream-->输出到硬盘(输出到指定硬盘位置并且起一个名字)  使用输出流将内存的数据 
		FileOutputStream outputStream=new FileOutputStream("D:\\itcast\\测试.xlsx");
		//将excel数据放到输出流上 输出到指定位置
		workbook.write(outputStream);
		//关闭工作薄  关闭输出流
		workbook.close();
		outputStream.close();
	}
/**
 * 
 * @param list
 * @param excelName
 */
	//导出excel~~~即从数据库拿出数据 存入指定硬盘~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static void daoChuExcel(List<User> list,String excelName,OutputStream out){ //list是数据库拿到的数据 OutputStream是输出到前台的输出流excelName是页面下载的名字
		try {
			//1前台点击导出按钮 后台实现action
	      	/*  function doExportExcel(){
	      		//打开一个新窗口然后 马上关闭
	      		window.open("${basePath}nsfw/user/user_exportExcel.action");
	      		}
	      	
	      	*/
			/*//2.将数据库拿到的数据 输出到前端页面response
			response.setContentType("application/x-execl");						//定义输出的类型type为什么格式
				//定义输出的到浏览器的head即用户下载看到的信息 头	excelName=用户列表.xls
			response.setHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes(),"ISO-8859-1"));
				//创建输出流将数据输出到前台
			OutputStream outputStream=response.getOutputStream();*/
			//1.创建工作博
			HSSFWorkbook workbook=new HSSFWorkbook();
				//1.1创建合并单元格对象
				CellRangeAddress cellRangeAddress=new CellRangeAddress(0,0,0,4);//起始行号0	 结束行号0	 起始列号0	 结束列号4
				//1.2头标题样式
				HSSFCellStyle style1=createCellStyle(workbook,(short)16);
				//1.3列标题样式
				HSSFCellStyle style2=createCellStyle(workbook,(short)13);
			//2.创建工作表
			HSSFSheet sheet=workbook.createSheet("用户列表");
				//2.1加载合并单元格对象
				sheet.addMergedRegion(cellRangeAddress);
				//2.2设置单元格之间的列宽
				sheet.setDefaultColumnWidth(25);
			//3.创建行和列
				//3.1创建头标题行,并且设置头标题
				HSSFRow row1=sheet.createRow(0);
				HSSFCell cell1=row1.createCell(0);
				//加载单元格样式
				cell1.setCellStyle(style1);
				cell1.setCellValue("用户列表");
				//3.2创建列标题行,并且设置列标题
				HSSFRow row2=sheet.createRow(1);
				String[] titles={"用户名","帐号","所属部门","性别","电子邮箱"};
				for(int i=0;i<titles.length;i++){
					HSSFCell cell2=row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);			
					cell2.setCellValue(titles[i]);
				}
			//4.操作单元格即将用户列表写入excel
			if(list!=null){
				for(int j=0;j<list.size();j++){
					HSSFRow row=sheet.createRow(j+2);
					HSSFCell cellA=row.createCell(0);
					cellA.setCellValue(list.get(j).getName());
					HSSFCell cellB=row.createCell(1);
					cellB.setCellValue(list.get(j).getAccount());
					HSSFCell cellC=row.createCell(2);
					cellC.setCellValue(list.get(j).getDept());
					HSSFCell cellD=row.createCell(3);
					cellD.setCellValue(list.get(j).getGender());
					HSSFCell cellE=row.createCell(4);
					cellE.setCellValue(list.get(j).getEmail());
				}
			}
			//转换为字节流
			workbook.write(out);
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//导入excel~~~即从指定的excel中拿数据导入数据库中~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public static List daoRuExcel(MultipartFile userExcel,String fileName,HttpServletResponse response){

		List list=new ArrayList();
		try {
			//读取之前先做一个判断是07还是03
			String houZhuiMing=fileName.substring(fileName.lastIndexOf("."));
			boolean is03Excel;
			if(".xls".equals(houZhuiMing)){
				is03Excel=true;
			}else{
				is03Excel=false;
			}
			//读取文件需要创建一个输入流FileInputStream
			/*FileInputStream inputStream=new FileInputStream(userExcel);*/
			//1读取工作薄xxx.xls   接收输入流
			Workbook workbook=is03Excel?new HSSFWorkbook(userExcel.getInputStream()):new XSSFWorkbook(userExcel.getInputStream());
			//2读取工作表sheet
			Sheet sheet=workbook.getSheetAt(0);	//读取第一个工作表sheet	
			//03.读取行 (做了一个判断 如果第3行有数据的话那么 程序执行否则 不执行
			if(sheet.getPhysicalNumberOfRows()>2){	
				User user=null;
				//因为excel的前两个都不是我们要的数据所以重第三个开始循环
				for(int k=2;k<sheet.getPhysicalNumberOfRows();k++){
					//04.读取单元格
					Row row=sheet.getRow(k);
					user=new User();
					
					Cell cell1=row.getCell(0);	//获取name
					//判断各个单元格是否为空  
					if(cell1==null||cell1.equals("")||cell1.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell1.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
						user.setName("");
					}else{
						user.setName(cell1.getStringCellValue());
					}
					Cell cell2=row.getCell(1);	//获取帐号
					if(cell2==null||cell2.equals("")||cell2.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell2.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
									//为空不去操作 
					}else{			//不为空则执行此操作
						user.setAccount(cell2.getStringCellValue());
					}
					
					Cell cell3=row.getCell(2);	//获取部门
					if(cell3==null||cell3.equals("")||cell3.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell3.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
									//为空不去操作 
					}else{			//不为空则执行此操作
						user.setDept(cell3.getStringCellValue());
					}		
					Cell cell4=row.getCell(3);	//获取性别
					if(cell4==null||cell4.equals("")||cell4.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell4.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
									//为空不去操作 
					}else{			//不为空则执行此操作
						user.setGender(cell4.getStringCellValue());
					}
					
					Cell cell5=row.getCell(4);	//获取 手机号  因为很有可能excel使用的是科学计数法 需要转换  但是 也很有可能是认为输入的字符串数字 所以当trycatch的时候
					String mobil="";			//如果是字符串 ok 如果是excel特有的科学计数的话 那么catch抓住 转换
					if(cell5==null||cell5.equals("")||cell5.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell5.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
						user.setMobile(mobil);
					}else{
						try{						//手机号   cell5.getNumericCellValue()
							mobil=cell5.getStringCellValue();
						}catch(Exception e){
							DecimalFormat df = new DecimalFormat("0");  		//网上查找的解决 先格式化 然后转为字符串		  
							mobil = df.format(cell5.getNumericCellValue()); 
							/*double dMobil=cell5.getNumericCellValue();		//目前测试有问题
							mobil=BigDecimal.valueOf(dMobil).toString();*/
							e.printStackTrace();
						}
						user.setMobile(mobil);
					}
					
					Cell cell6=row.getCell(5);	//设置电子邮箱
					if(cell6==null||cell6.equals("")||cell6.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell6.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
									//为空不去操作 
					}else{			//不为空则执行此操作
						user.setEmail(cell6.getStringCellValue());
					}
					
					Cell cell7=row.getCell(6);	//设置生日  Date类型	cell7.getDateCellValue()
					if(cell7==null||cell7.equals("")||cell7.getCellType() ==HSSFCell.CELL_TYPE_BLANK||cell7.getCellType() ==XSSFCell.CELL_TYPE_BLANK){
									//为空不去操作 
					}else{			//不为空则执行此操作
						if(cell7.getDateCellValue()!=null){
							user.setBirthday(cell7.getDateCellValue()+"");
						}
					}
					
					//默认用户密码为 123456
					user.setPassword("123456");
					//默认用户状态为有效
					user.setState(User.USER_STATE_VALID);
					//05.保存用户  即添加用户
					list.add(user);
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//*******************************************************************************************************************************
	/** 
	 * 对Excel的各个单元格的格式进行判断并转换 		这个方法可以将我们在数据库的格式不同转换不同 返回的结果可以是 String　Double int boolean 
	 */  
	private String getCellValue(HSSFCell cell) {   
	        String cellValue = "";   
	        DecimalFormat df = new DecimalFormat("#");   
	        switch (cell.getCellType()) {   
	        case HSSFCell.CELL_TYPE_STRING:   
	            cellValue =cell.getRichStringCellValue().getString().trim();   
	            break;   
	        case HSSFCell.CELL_TYPE_NUMERIC:   
	            cellValue =df.format(cell.getNumericCellValue()).toString();   
	            break;   
	        case HSSFCell.CELL_TYPE_BOOLEAN:   
	            cellValue =String.valueOf(cell.getBooleanCellValue()).trim();   
	            break;   
	        case HSSFCell.CELL_TYPE_FORMULA:   
	            cellValue =cell.getCellFormula();   
	            break;   
	        default:   
	            cellValue = "";   
	        }   
	        return cellValue;   
	    }   
//###############################################################################################################################
	//创建单元格的样式   通用方法
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,short fontSize){
		HSSFCellStyle style=workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
			//1.2.1创建字体
			HSSFFont font=workbook.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); //加粗字体
			font.setFontHeightInPoints(fontSize);				  //设置字体大小为size 16
			//1.2.2加载字体
			style.setFont(font);
		return style;
	}
}

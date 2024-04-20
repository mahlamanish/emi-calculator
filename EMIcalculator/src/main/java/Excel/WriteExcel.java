package Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Properties.SetProperties;

import ExtentReport.DateUtils;

public class WriteExcel {
	
	static Properties prop = SetProperties.getPropertiesFile();
	
	public static void setDataToExcel(String principal,String Interest) throws IOException {

		String excelName = DateUtils.timeStamp();

		String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\Excel\\LoanData.xlsx";


		XSSFWorkbook workbook  = new XSSFWorkbook();
		XSSFSheet sheet = workbook.getSheet("Data1");

		
		sheet.getRow(0).getCell(3).setCellValue(prop.getProperty("ROWNAME1"));
		sheet.getRow(0).getCell(4).setCellValue(prop.getProperty("ROWNAME"));

		//int j=0;
		//for(int i=1;i<=5;i++)
		//{
			
			
			sheet.getRow(1).getCell(3).setCellValue(principal);
			sheet.getRow(1).getCell(4).setCellValue(Interest);
			//j++;
		//}
		
		FileOutputStream fos=new FileOutputStream(excelPath);
		workbook.write(fos);
		workbook.close();		   
	}
	
	

}

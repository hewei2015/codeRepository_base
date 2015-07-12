package hw.learn.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * POI入门 ：简单读取excel数据
 * 
 * @author VintageYu
 *
 *         Vintage Yu 第 6 页 2011 -9-1
 */
public class ReadExcelTest {
	public static void read(InputStream inputStream) throws IOException {
		// 初始整个Excel
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		// 获取第一个Sheet表
		HSSFSheet sheet = workbook.getSheetAt(0);// 或者 HSSFSheet sheet =
		workbook.getSheet("Sheet1");
		// 获取第一行
		HSSFRow row0 = sheet.getRow(0);
		// 获取第一行的第一个单元格
		HSSFCell cell = row0.getCell(0);
		// 打印
		System.out.println(cell.getRichStringCellValue().getString());
	}

	public static void main(String[] args) {
		InputStream inputStream = null;
		try {
			// 读取文件流
			inputStream = new FileInputStream(new File("D:\\HwTemp.xls"));
			read(inputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
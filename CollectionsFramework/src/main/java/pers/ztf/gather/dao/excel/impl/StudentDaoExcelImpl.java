package main.java.pers.ztf.gather.dao.excel.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.dev.EFBiffViewer;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import main.java.pers.ztf.gather.dao.excel.StudentDaoExcel;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;

public class StudentDaoExcelImpl implements StudentDaoExcel{
	public List<Student> readStudentsFromXls(String pathname){
		List<Student> students=new ArrayList<>();
		File file=new File(pathname);
		
		try {
			HSSFWorkbook hssfWorkbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			HSSFSheet sheet=hssfWorkbook.getSheetAt(0);
			//忽略第一行
			int firstRowNumber=1;
			int lastRowNum=sheet.getLastRowNum();
			for(int i=firstRowNumber;i<=lastRowNum;i++) {
				HSSFRow row=sheet.getRow(i);
				int lastCellNum=row.getLastCellNum();
			
				String id=String.valueOf( (int)row.getCell(0).getNumericCellValue());
				String name=row.getCell(1).getStringCellValue();
				students.add(new Student(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}
}

package main.java.pers.ztf.gather.dao.excel.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import main.java.pers.ztf.gather.dao.excel.ScoreDaoExcel;
import main.java.pers.ztf.gather.po.Score;


public class ScoreDaoExcelImpl implements ScoreDaoExcel{
	public List<Score> readScoreFromXls(String pathname){
		List<Score> scoreList=new ArrayList<>();
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
				
				String student_id=String.valueOf((int)row.getCell(0).getNumericCellValue());
				String course=row.getCell(1).getStringCellValue();		
				String grade=String.valueOf((int)row.getCell(2).getNumericCellValue());
				scoreList.add(new Score(student_id, course, grade));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoreList;
	}
}

package main.java.pers.ztf.gather.dao.excel;

import java.util.List;

import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;

public interface StudentDaoExcel {
	public List<Student> readStudentsFromXls(String pathname);
	
}

package main.java.pers.ztf.gather.dao.hsqldb;


import java.util.List;

import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;

public interface StudentDaoHsql {
	public void insertStudents(List<Student> students);
	public void insertStudent(Student student);
	public List<Student> selectStudents();
	public Student selectById(String id);
	
}

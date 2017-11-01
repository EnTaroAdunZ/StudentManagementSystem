package main.java.pers.ztf.gather.service.Impl;

import java.io.File;
import java.util.List;

import main.java.pers.ztf.gather.dao.excel.ScoreDaoExcel;
import main.java.pers.ztf.gather.dao.excel.StudentDaoExcel;
import main.java.pers.ztf.gather.dao.excel.impl.ScoreDaoExcelImpl;
import main.java.pers.ztf.gather.dao.excel.impl.StudentDaoExcelImpl;
import main.java.pers.ztf.gather.dao.hsqldb.ScoreDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.StudentDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.impl.ScoreDaoHsqlImpl;
import main.java.pers.ztf.gather.dao.hsqldb.impl.StudentDaoHsqlImpl;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.service.StudentService;
import main.java.pers.ztf.gather.util.StudentUtil;
import main.java.pers.ztf.gather.view.LoadView;

public class StudentServiceImpl implements StudentService{
	private LoadView application;
	private ScoreDaoExcel scoreDaoExcel;
	private StudentDaoExcel studentDaoExcel;
	private ScoreDaoHsql scoreDaoHsql;
	private StudentDaoHsql studentDaoHsql;
	
	

	public StudentServiceImpl(LoadView application) {
		super();
		this.scoreDaoExcel = new ScoreDaoExcelImpl();
		this.studentDaoExcel = new StudentDaoExcelImpl();
		this.scoreDaoHsql = new ScoreDaoHsqlImpl();
		this.studentDaoHsql =new StudentDaoHsqlImpl(); 
		this.application=application;
	}


	@Override
	public List<StudentDto> getStudentsFromXls(File file) {
		List<Student> students=studentDaoExcel.readStudentsFromXls(file.getAbsolutePath());
		studentDaoHsql.insertStudents(students);
		return StudentUtil.PoToDto(students, application);
	}


	@Override
	public List<StudentDto> getStudentsHsql() {
		
		return StudentUtil.PoToDto(studentDaoHsql.selectStudents(), application);
	}


	@Override
	public StudentDto addStudent(Student student) {
		studentDaoHsql.insertStudent(student);
	
		return StudentUtil.PoToDto(student, application);
	}


	@Override
	public Student getStudentById(String id) {
		
		return studentDaoHsql.selectById(id);
	}

}

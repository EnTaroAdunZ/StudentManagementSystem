package main.java.pers.ztf.gather.service;

import java.io.File;
import java.util.List;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Student;

public interface StudentService {
	public List<StudentDto> getStudentsFromXls(File file);
	public List<StudentDto> getStudentsHsql();
	public StudentDto addStudent(Student student);
	public Student getStudentById(String id);
}

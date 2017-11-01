package main.java.pers.ztf.gather.dao.hsqldb.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.pers.ztf.gather.dao.hsqldb.StudentDaoHsql;
import main.java.pers.ztf.gather.po.Student;

public class StudentDaoHsqlImpl implements StudentDaoHsql{
	
//	static Connection connection=null;
//	static Statement stmt=null;;
	
	String TABLE_NAME = "student";
	String Create_FIELDS = "(id varchar(254),name varchar(254))";
    String INSERT_SQL = "insert into "+TABLE_NAME+" values(?,?)" ;
    String SELECT_SQL = "select * from "+TABLE_NAME ;
    String SELECT_WITHID=" where id=?";
    
	public void createStudentData() {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
			Statement  stmt = connection.createStatement();)
		{
	         String sql1 = "create table IF NOT EXISTS "+TABLE_NAME+Create_FIELDS;
	         stmt.executeUpdate(sql1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        
	}
	


	@Override
	public void insertStudents(List<Student> students) {
		
		try {
			createStudentData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement ps = connection.prepareStatement(INSERT_SQL);)
		{
			for(Student student:students) {
				ps.setString(1, student.getId());
				ps.setString(2, student.getName());
				ps.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Student> selectStudents() {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Student> students=new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    Statement state = connection.createStatement();)
		{
			ResultSet resultSet=state.executeQuery(SELECT_SQL);
			while(resultSet.next()) {
				students.add(new Student(resultSet.getString(1), resultSet.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}



	@Override
	public void insertStudent(Student student) {
		try {
			createStudentData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement ps = connection.prepareStatement(INSERT_SQL);)
		{
				ps.setString(1, student.getId());
				ps.setString(2, student.getName());
				ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public Student selectById(String id) {
		
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Student student=null;
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement state = connection.prepareStatement(SELECT_SQL+SELECT_WITHID);)
		{
			state.setString(1, id);
			ResultSet resultSet=state.executeQuery();
			if(resultSet.next()) {
				student=new Student(id, resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
	
//	public static void main(String[] args) {
//    	StudentDaoHsqlImpl studentDaoHsqlImpl=new StudentDaoHsqlImpl();
//    	studentDaoHsqlImpl.createStudentData();
//    	List<Student> list=new ArrayList<>();
//    	list.add(new Student("443", "233"));
//    	list.add(new Student("44", "3433"));
//    	studentDaoHsqlImpl.insertStudent(list);
//    	
//    	
//    	List<Student> list2=studentDaoHsqlImpl.selectStudents();
//    	for(Student stuent:list2) {
//    		System.out.println(stuent);
//    	}
//    	
//	}

}

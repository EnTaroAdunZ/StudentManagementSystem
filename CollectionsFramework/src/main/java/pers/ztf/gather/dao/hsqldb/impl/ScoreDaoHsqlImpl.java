package main.java.pers.ztf.gather.dao.hsqldb.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.STRING;

import main.java.pers.ztf.gather.dao.hsqldb.ScoreDaoHsql;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.util.ScoreUtil;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;

public class ScoreDaoHsqlImpl implements ScoreDaoHsql{
	
	String TABLE_NAME = "score";
	String Create_FIELDS = "(student_id varchar(254),course varchar(254),grade int)";
    String INSERT_SQL = "insert into "+TABLE_NAME+" values(?,?,?)" ;
    String SELECT_SQL = "select * from "+TABLE_NAME ;
    String SELECT_SQL_ID = "select * from "+TABLE_NAME+" where student_id=?" ;
    String DELETE_SQL="delete from "+TABLE_NAME+" where student_id=? and course=? and grade=?";
	
    
    public void createScoreData() {
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
	public void insertScores(List<Score> scores) {
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement ps = connection.prepareStatement(INSERT_SQL);)
		{
			for(Score score:scores) {
				ps.setString(2, score.getCourse());
				ps.setInt(3, Integer.valueOf(score.getGrade()));
				ps.setString(1, score.getStudent_id());
				ps.execute();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Score> selectScores() {
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Score> scores=new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    Statement state = connection.createStatement();)
		{
			ResultSet resultSet=state.executeQuery(SELECT_SQL);
			while(resultSet.next()) {
				scores.add(new Score(resultSet.getString(1), resultSet.getString(2),String.valueOf(resultSet.getInt(3))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}

	@Override
	public List<Score> selectScoresWithId(String student_id) {
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Score> scores=new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement state = connection.prepareStatement(SELECT_SQL_ID);)
		{
			state.setString(1, student_id);
			ResultSet resultSet=state.executeQuery();
			while(resultSet.next()) {
				scores.add(new Score(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}

	@Override
	public void insertScore(Score score) {
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement ps = connection.prepareStatement(INSERT_SQL);)
		{
				ps.setString(2, score.getCourse());
				ps.setInt(3, Integer.valueOf(score.getGrade()));
				ps.setString(1, score.getStudent_id());
				ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteScore(Score score) {
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
		    PreparedStatement ps = connection.prepareStatement(DELETE_SQL);)
		{
				ps.setString(1, score.getStudent_id());
				ps.setString(2, score.getCourse());
				ps.setInt(3, Integer.valueOf(score.getGrade()));
				ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Score> countScore(CountType countType) {
		String type=null;
		if(countType==CountType.AVG) {
			type=new String("avg");
		}else if(countType==CountType.MAX){
			type=new String("max");
		}else if(countType==CountType.MIN) {
			type=new String("min");
		}
		String COUNT_SQL="select course,"+type+"(grade) from "+TABLE_NAME+" group by course;";
		try {
			createScoreData();
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Score> scores=new ArrayList<>();
		try(Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:aname", "sa", "");
			    PreparedStatement ps = connection.prepareStatement(COUNT_SQL);)
		{
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()) {
				scores.add(new Score("", resultSet.getString(1),resultSet.getString(2)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	
//	public static void main(String[] args) {
//		ScoreDaoHsqlImpl scoreDaoHsqlImpl=new ScoreDaoHsqlImpl();
//		List<Score> list=new ArrayList<>();
//		list.add(new Score("443", "233","45645"));
//		list.add(new Score("4396", "233","45645"));
//		list.add(new Score("44", "3433","455"));
//		list.add(new Score("44", "3433445","456455"));
//		scoreDaoHsqlImpl.insertScore(list);
//		
//		
//		List<Score> list2=scoreDaoHsqlImpl.selectScoresWithId("45645");
//		for(Score stuent:list2) {
//			System.out.println("df"+stuent);
//		}
//
//	}

}

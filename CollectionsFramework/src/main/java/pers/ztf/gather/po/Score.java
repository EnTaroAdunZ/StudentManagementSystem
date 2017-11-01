package main.java.pers.ztf.gather.po;

import javafx.beans.property.SimpleStringProperty;

public  class Score {
	private  String course;
	private  String grade;
	private String student_id;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Score(String student_id,String course, String grade) {
		super();
		this.course = course;
		this.grade = grade;
		this.student_id = student_id;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	@Override
	public String toString() {
		return "Score [course=" + course + ", grade=" + grade + ", student_id=" + student_id + "]";
	}
	
	
	
	
//	private Score(String course, String grade) {
//		this.course = new SimpleStringProperty(course);
//		this.grade = new SimpleStringProperty(grade);
//	}
//	public String getCourse() {
//		return course.get();
//	}
//	public String getGrade() {
//		return grade.get();
//	}
//
//	public void setCourse(String cour) {
//		course.set(cour);
//	}
//	public void setGrade(String gra) {
//		grade.set(gra);
//	}
}

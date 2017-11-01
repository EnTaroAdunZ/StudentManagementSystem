package main.java.pers.ztf.gather.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ScoreDto {
	private StringProperty grade;
	private StringProperty course;
	private Button button ;
	
	public ScoreDto(String course, String grade,String id,EventHandler<ActionEvent> eventHandler) {
		this.grade=new SimpleStringProperty(grade);
		this.course=new SimpleStringProperty(course);
		button=new Button();
		this.button.setText("删除");
		button.setAccessibleHelp(id);
		button.setOnAction(eventHandler);
	}
	
	public String getGrade() {
		return grade.get();
	}
	public void setGrade(String grade) {
		this.grade.set(grade);
	}
	public String getCourse() {
		return course.get();
	}
	public void setCourse(String course) {
		this.course.set(course);
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	
	

}

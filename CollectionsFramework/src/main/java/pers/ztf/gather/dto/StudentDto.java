package main.java.pers.ztf.gather.dto;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import main.java.pers.ztf.gather.po.Score;

public class StudentDto {
	private StringProperty id;
	private StringProperty name;
	private Button button ;
	

	public StudentDto(String i, String n,EventHandler<ActionEvent> eventHandler) {
		id=new SimpleStringProperty(i);
		name=new SimpleStringProperty(n);
		button=new Button();
		this.button.setText("成绩管理");
		button.setAccessibleHelp(id.get());
		button.setOnAction(eventHandler);
	}


	public String getId() {
	    return id.get();
	}
	
	public void setId(String i) {
		id.set(i);
	}
	
	public String getName() {
	    return name.get();
	}
	
	public void setName(String na) {
	    name.set(na);
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}

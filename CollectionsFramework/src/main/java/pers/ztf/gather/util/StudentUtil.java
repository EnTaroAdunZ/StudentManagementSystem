package main.java.pers.ztf.gather.util;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.view.LoadView;

public class StudentUtil {
	public static List<StudentDto> PoToDto(List<Student> students,LoadView application){
	  
	    if(students==null||students.size()==0)
		return null;
	    
	    List<StudentDto> studentsDto =new ArrayList<>();
	    for(Student student:students) {
	    	studentsDto.add(new StudentDto(student.getId(),student.getName() , new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Button button = (Button) event.getSource();
					application.ScoreManage(button.getAccessibleHelp());
				}
			}));
	    }
	    return studentsDto;   
	}
	
	public static StudentDto PoToDto(Student student,LoadView application){
		  
	    if(student==null)
		return null;
	    
    	StudentDto studentsDto=new StudentDto(student.getId(),student.getName() , new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button button = (Button) event.getSource();
				application.ScoreManage(button.getAccessibleHelp());
			}
		});
	    
	    return studentsDto;   
	}
	
	public static List<Student> getInitData(){
		List<Student> students=new ArrayList<>();
		students.add(new Student("101", "张飞"));
		students.add(new Student("102", "李白"));
		students.add(new Student("103", "关羽"));
	    return students;
	}
	
}

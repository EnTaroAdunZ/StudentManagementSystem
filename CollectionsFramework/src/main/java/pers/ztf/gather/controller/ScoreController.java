package main.java.pers.ztf.gather.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hsqldb.lib.StringUtil;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.service.ScoreService;
import main.java.pers.ztf.gather.service.StudentService;
import main.java.pers.ztf.gather.service.Impl.ScoreServiceImpl;
import main.java.pers.ztf.gather.service.Impl.StudentServiceImpl;
import main.java.pers.ztf.gather.view.LoadView;

public class ScoreController implements Initializable{
    @FXML 
    private TextField tf_course;  
    @FXML 
    private TextField tf_grade;  
	@FXML
	private TableView tview_score;
	@FXML
	private TableColumn col_course;
	@FXML
	private TableColumn col_grade;
	@FXML
	private TableColumn scoreMan_btn;
	
	@FXML
	private Label lab_name;
	
	private StudentService studentService;
	private ScoreService scoreService;
	private String id;
	private LoadView application; 

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			initView();
		} catch (Exception ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private void initView() throws Exception {
		col_course.setCellValueFactory(new PropertyValueFactory("course"));// 映射
		col_grade.setCellValueFactory(new PropertyValueFactory("grade"));// 映射
		scoreMan_btn.setCellValueFactory(new PropertyValueFactory("button"));// 映射
	}
    
    @FXML  
    public void addScore(ActionEvent event) {  
	    String course=tf_course.getText();
	    String grade=tf_grade.getText();
	    if(!StringUtil.isEmpty(course)&&!StringUtil.isEmpty(grade)) {
			scoreService.addScore(new Score(id, course, grade));
			ObservableList<ScoreDto> list = FXCollections.observableArrayList();
			list.clear();
			list.addAll(scoreService.getScoresFromHsql(id));
			tview_score.setItems(list);
	    }
	    tf_course.setText("");
	    tf_grade.setText("");
    	
    }
	
    @FXML  
    public void gotomain(ActionEvent event) {  
    	application.gotoMain();
    }
    
    public void setApp(LoadView application,String id){  
        this.application = application;  
        this.id=id;
		studentService=new StudentServiceImpl(application);
		scoreService=new ScoreServiceImpl(application);
	    Student student=studentService.getStudentById(id);
	    if(student!=null&&student.getName()!=null) {
	    	lab_name.setText(student.getName()+"的成绩");
	    }
		
		ObservableList<ScoreDto> list = FXCollections.observableArrayList();
		List<ScoreDto> scoreDtos=scoreService.getScoresFromHsql(id);
		if(scoreDtos!=null) {
			list.addAll(scoreDtos);
			tview_score.setItems(list);
		}

    }  


}

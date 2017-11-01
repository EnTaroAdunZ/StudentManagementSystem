package main.java.pers.ztf.gather.controller;

import java.io.File;
import java.net.URL;
import java.security.Provider.Service;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hsqldb.lib.StringUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.service.ScoreService;
import main.java.pers.ztf.gather.service.StudentService;
import main.java.pers.ztf.gather.service.Impl.ScoreServiceImpl;
import main.java.pers.ztf.gather.service.Impl.StudentServiceImpl;
import main.java.pers.ztf.gather.util.StudentUtil;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;
import main.java.pers.ztf.gather.view.LoadView;

public class MainController implements Initializable {

	@FXML
	private TextField tf_studentId;
	@FXML
	private TextField tf_studentName;
	@FXML
	private TableView tview_student;
	@FXML
	private TableColumn col_studentId;
	@FXML
	private TableColumn col_studentName;
	@FXML
	private TableColumn scoreMan_btn;
	
	
	private StudentService studentService;
	private ScoreService scoreService;
	
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
		
		col_studentId.setCellValueFactory(new PropertyValueFactory("id"));// 映射
		col_studentName.setCellValueFactory(new PropertyValueFactory("name"));// 映射
		scoreMan_btn.setCellValueFactory(new PropertyValueFactory("button"));// 映射
		
	}

	public void setApp(LoadView application) {
		this.application = application;
		studentService=new StudentServiceImpl(application);
		scoreService=new ScoreServiceImpl(application);
		ObservableList<StudentDto> list = FXCollections.observableArrayList();
		List<StudentDto> studentDtos=studentService.getStudentsHsql();
		if(studentDtos!=null) {
			list.addAll(studentDtos);
			tview_student.setItems(list);
		}

	}

	@FXML
	public void addStudent(ActionEvent event) {
	    String id=tf_studentId.getText();
	    String name=tf_studentName.getText();
	    if(!StringUtil.isEmpty(id)&&!StringUtil.isEmpty(name)) {
			studentService.addStudent(new Student(id, name));
			ObservableList<StudentDto> list = FXCollections.observableArrayList();
			list.clear();
			list.addAll(studentService.getStudentsHsql());
			tview_student.setItems(list);
	    }
		tf_studentId.setText("");
		tf_studentName.setText("");
	}
	
	@FXML
	public void leadInStudentExcel(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择一个xls文件");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xls", "*.xls*"));
		File file=fileChooser.showOpenDialog(application.getStage());
		if(file!=null) {
			List<StudentDto> students=studentService.getStudentsFromXls(file);
			ObservableList<StudentDto> list=tview_student.getItems();
			if(students!=null)
			list.addAll(students);
		}

	}
	@FXML
	public void leadInScoreExcel(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("选择一个xls文件");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xls", "*.xls*"));
		File file=fileChooser.showOpenDialog(application.getStage());
		if(file!=null) {
			List<ScoreDto> scoreDtos=scoreService.getScoresFromXls(file);
		}
	}
	
	@FXML
	public void countAvgScore(ActionEvent event) {
		application.gotoCount(CountType.AVG);
	}
	@FXML
	public void countMaxScore(ActionEvent event) {
		application.gotoCount(CountType.MAX);
	}
	@FXML
	public void countMinScore(ActionEvent event) {
		application.gotoCount(CountType.MIN);
	}

}

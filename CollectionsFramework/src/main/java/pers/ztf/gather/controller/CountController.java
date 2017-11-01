package main.java.pers.ztf.gather.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.service.ScoreService;
import main.java.pers.ztf.gather.service.Impl.ScoreServiceImpl;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;
import main.java.pers.ztf.gather.view.LoadView;

public class CountController implements Initializable{
	
	@FXML
	private TableView tview_score;
	@FXML
	private TableColumn col_course;
	@FXML
	private TableColumn col_grade;
	@FXML
	private Label lab_name;
	
	private ScoreService scoreService;
	private LoadView application; 
	
    public void setApp(LoadView application,CountType countType){  
        this.application = application;  
		scoreService=new ScoreServiceImpl(application);
		if(countType==CountType.AVG) {
			lab_name.setText("平均成绩查询");
		}else if(countType==CountType.MAX){
			lab_name.setText("课程最高分查询");
		}else if(countType==CountType.MIN) {
			lab_name.setText("课程最低分查询");
		}
		ObservableList<ScoreDto> list = FXCollections.observableArrayList();
		List<ScoreDto> countScore = scoreService.countScore(countType);
		if(countScore!=null) {
			list.addAll(countScore);
			tview_score.setItems(list);
		}

    }  
	


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
	}
	
    @FXML  
    public void gotomain(ActionEvent event) {  
    	application.gotoMain();
    }

}

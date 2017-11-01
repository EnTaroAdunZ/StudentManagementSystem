package main.java.pers.ztf.gather.view;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.pers.ztf.gather.controller.CountController;
import main.java.pers.ztf.gather.controller.MainController;
import main.java.pers.ztf.gather.controller.ScoreController;
import main.java.pers.ztf.gather.dao.hsqldb.ScoreDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.StudentDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.impl.ScoreDaoHsqlImpl;
import main.java.pers.ztf.gather.dao.hsqldb.impl.StudentDaoHsqlImpl;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.util.ScoreUtil;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;
import main.java.pers.ztf.gather.util.StudentUtil;

public class LoadView  extends Application{
	
	private Stage stage;  
    private final double MINIMUM_WINDOW_WIDTH = 400.0;  
    private final double MINIMUM_WINDOW_HEIGHT = 250.0;  
    
    public Stage getStage() {
    	return stage;
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;  
        stage.setTitle("学生成绩管理系统");  
        gotoMain();
        stage.show();  
	}
    public void gotoMain(){  
        try {  
        	MainController mainController=(MainController) replaceSceneContent("/main/resource/fxml/FXML_MAIN.fxml");  
        	mainController.setApp(this);
          } catch (Exception ex) {  
              Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);  
          }  
      }  
    public void gotoScore(String id){  
        try {  
        	ScoreController scoreController=(ScoreController) replaceSceneContent("/main/resource/fxml/FXML_SCORE.fxml"); 
        	scoreController.setApp(this,id);
          } catch (Exception ex) {  
              Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);  
          }  
    }
    public void gotoCount(CountType countType){  
        try {  
        	CountController countController=(CountController) replaceSceneContent("/main/resource/fxml/FXML_COUNT.fxml"); 
        	countController.setApp(this,countType);
          } catch (Exception ex) {  
              Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);  
          }  
    }      
    
    private Initializable replaceSceneContent(String fxml) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream in = LoadView.class.getResourceAsStream(fxml);  
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(LoadView.class.getResource(fxml));  
        GridPane page;  
        try {  
            page = (GridPane) loader.load(in);  
        } finally {  
            in.close();  
        }   
        Scene scene = new Scene(page);  
        stage.setScene(scene);  
        stage.sizeToScene();  
        return (Initializable) loader.getController();  
    }  
	
    public void StudentManage(){  
    	gotoMain();
    }  
    public void ScoreManage(String id){
    	gotoScore(id);  
    }  
    public static void main(String[] args) {
    	initData();
        launch(args);  
    }

	private static void initData() {
		StudentDaoHsql studentDaoHsql=new StudentDaoHsqlImpl();
		ScoreDaoHsql scoreDaoHsql=new ScoreDaoHsqlImpl();
		studentDaoHsql.insertStudents(StudentUtil.getInitData());
		scoreDaoHsql.insertScores(ScoreUtil.getInitData());
	}  

}

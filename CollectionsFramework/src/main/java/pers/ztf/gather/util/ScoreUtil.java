package main.java.pers.ztf.gather.util;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.service.ScoreService;
import main.java.pers.ztf.gather.view.LoadView;

public class ScoreUtil {
	
	public static enum CountType {
	    AVG, MIN, MAX;
	}
	
	public static List<ScoreDto> PoToDto(List<Score> scores,LoadView application,ScoreService scoreService){
		  
	    if(scores==null||scores.size()==0)
		return null;
	    
	    List<ScoreDto> scoresDto =new ArrayList<>();
	    for(Score score:scores) {
	    	scoresDto.add(new ScoreDto(score.getCourse(), score.getGrade(),score.getStudent_id(),new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Button button = (Button) event.getSource();
					scoreService.deleteScore(new Score(button.getAccessibleHelp(), score.getCourse(), score.getGrade()));
					
					application.ScoreManage(button.getAccessibleHelp());
				}
			}));
	    }
	    return scoresDto;   
	}
	
	public static ScoreDto PoToDto(Score score,LoadView application){
		  
	    if(score==null)
		return null;
	    
	    

	    ScoreDto scoreDto=new ScoreDto(score.getCourse(), score.getGrade(), score.getStudent_id(),new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Button button = (Button) event.getSource();
				application.ScoreManage(button.getAccessibleHelp());
			}
		});
	    
	    return scoreDto;   
	}
	
	public static List<Score> getInitData(){
		List<Score> scores=new ArrayList<>();
		scores.add(new Score("101", "数学","66"));
		scores.add(new Score("102", "语文","99"));
		scores.add(new Score("103", "英语","22"));
		scores.add(new Score("103", "数学","66"));
		scores.add(new Score("102", "地理","99"));
		scores.add(new Score("101", "英语","22"));
	    return scores;
	}
}

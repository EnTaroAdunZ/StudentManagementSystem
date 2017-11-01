package main.java.pers.ztf.gather.service.Impl;

import java.io.File;
import java.util.List;

import main.java.pers.ztf.gather.dao.excel.ScoreDaoExcel;
import main.java.pers.ztf.gather.dao.excel.StudentDaoExcel;
import main.java.pers.ztf.gather.dao.excel.impl.ScoreDaoExcelImpl;
import main.java.pers.ztf.gather.dao.excel.impl.StudentDaoExcelImpl;
import main.java.pers.ztf.gather.dao.hsqldb.ScoreDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.StudentDaoHsql;
import main.java.pers.ztf.gather.dao.hsqldb.impl.ScoreDaoHsqlImpl;
import main.java.pers.ztf.gather.dao.hsqldb.impl.StudentDaoHsqlImpl;
import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.dto.StudentDto;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.service.ScoreService;
import main.java.pers.ztf.gather.util.ScoreUtil;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;
import main.java.pers.ztf.gather.view.LoadView;

public class ScoreServiceImpl implements ScoreService{
	private LoadView application;
	private ScoreDaoExcel scoreDaoExcel;
	private StudentDaoExcel studentDaoExcel;
	private ScoreDaoHsql scoreDaoHsql;
	private StudentDaoHsql studentDaoHsql;
	
	

	public ScoreServiceImpl(LoadView application) {
		super();
		this.scoreDaoExcel = new ScoreDaoExcelImpl();
		this.studentDaoExcel = new StudentDaoExcelImpl();
		this.scoreDaoHsql = new ScoreDaoHsqlImpl();
		this.studentDaoHsql =new StudentDaoHsqlImpl(); 
		this.application=application;
	}



	@Override
	public List<ScoreDto> getScoresFromXls(File file) {
		List<Score> scores=scoreDaoExcel.readScoreFromXls(file.getAbsolutePath());
		scoreDaoHsql.insertScores(scores);
		return ScoreUtil.PoToDto(scores, application,this);
	}



	@Override
	public List<ScoreDto> getScoresFromHsql(String id) {
		
		return ScoreUtil.PoToDto(scoreDaoHsql.selectScoresWithId(id), application,this);
	}



	@Override
	public ScoreDto addScore(Score score) {
		scoreDaoHsql.insertScore(score);
		return ScoreUtil.PoToDto(score, application);
	}



	@Override
	public void deleteScore(Score score) {
		scoreDaoHsql.deleteScore(score);
	}



	@Override
	public List<ScoreDto> countScore(CountType countType) {
		return ScoreUtil.PoToDto(scoreDaoHsql.countScore(countType), application, this);
	}

}

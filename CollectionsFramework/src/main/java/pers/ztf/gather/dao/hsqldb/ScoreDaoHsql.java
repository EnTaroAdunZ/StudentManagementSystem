package main.java.pers.ztf.gather.dao.hsqldb;

import java.util.List;

import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;

public interface ScoreDaoHsql {
	public void insertScores(List<Score> scores);
	public void insertScore(Score score);
	public List<Score> selectScores();
	public List<Score> selectScoresWithId(String student_id);
	public void deleteScore(Score score);
	public List<Score> countScore(CountType countType);
}

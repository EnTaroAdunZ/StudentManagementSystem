package main.java.pers.ztf.gather.service;


import java.io.File;
import java.util.List;

import main.java.pers.ztf.gather.dto.ScoreDto;
import main.java.pers.ztf.gather.po.Score;
import main.java.pers.ztf.gather.po.Student;
import main.java.pers.ztf.gather.util.ScoreUtil.CountType;

public interface ScoreService {
	public List<ScoreDto> getScoresFromXls(File file);
	public List<ScoreDto> getScoresFromHsql(String id);
	public ScoreDto addScore(Score score);
	public void deleteScore(Score score);
	public List<ScoreDto> countScore(CountType countType);
}

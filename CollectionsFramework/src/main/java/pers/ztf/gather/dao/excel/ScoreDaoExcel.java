package main.java.pers.ztf.gather.dao.excel;

import java.util.List;

import main.java.pers.ztf.gather.po.Score;

public interface ScoreDaoExcel {
	public  List<Score> readScoreFromXls(String pathname);
}

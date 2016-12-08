package game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Score implements Serializable{

	static List<Score> scoreTable = new ArrayList<Score>();
	
	static void sortScores() {
		Collections.sort(scoreTable, new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s2.getScore().compareTo(s1.getScore());
			}
		});
	}
	
	private Integer score;
	private String username;
	
	public Score(Integer s, String u) {
		score = s;
		username = u;
		if (scoreTable.size() < 10) {
			scoreTable.add(this);
			sortScores();
			return;
		}
		int min = Integer.MAX_VALUE;
		Score minScore = null;
		for(Score sc : scoreTable) {
			if(sc.getScore() < min) {
				min = sc.getScore();
				minScore = sc;
			}
		}
		if (score > min) {
			scoreTable.remove(minScore);
			scoreTable.add(this);
			sortScores();
		}
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	public String getUsername() {
		return this.username;
	}
}

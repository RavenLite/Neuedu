package com.neuedu.model.po;

import java.util.List;

public class Course {	
	private int cid;
	private String cname;
	private List<Scores> scores;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public List<Scores> getScores() {
		return scores;
	}
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
	
}

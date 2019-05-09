package com.neuedu.model.po;

import java.util.List;

public class Student {
	private int sid;
	private String sname;
	private List<Scores> scores;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public List<Scores> getScores() {
		return scores;
	}
	public void setScores(List<Scores> scores) {
		this.scores = scores;
	}
}

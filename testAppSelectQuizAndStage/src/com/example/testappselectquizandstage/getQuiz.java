package com.example.testappselectquizandstage;

public class getQuiz {
	private int id;
	private String level;
	private String stage;
	private String quiz;
	private int point;
	private String answer;
	
	public getQuiz(int id,String level,String stage,String quiz,int point,String answer){
		this.id = id;
		this.level = level;
		this.stage = stage;
		this.quiz = quiz;
		this.point = point;
		this.answer = answer;
	}
	
	public int getID(){
		return id;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getStage() {
		return stage;
	}
	public String getQuizs(){
		return quiz;
	}
	
	public int getPoint() {
		return point;
	}
	
	public String getAnswer(){
		return answer;
	}
}


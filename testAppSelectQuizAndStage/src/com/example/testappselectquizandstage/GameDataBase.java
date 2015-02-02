package com.example.testappselectquizandstage;

public class GameDataBase {
	private int id;
	private int live;
	private String level;
	private int stage;
	private int candy_level;
	private int score;
	private int highscore;
	
	public GameDataBase(int id,int live,String level,int stage,int candyLevel,int score,int highscore){
		this.id = id;
		this.live = live;
		this.level = level;
		this.stage = stage;
		this.candy_level = candyLevel;
		this.score = score;
		this.highscore = highscore;
	}
	
	public String getLevel(){
		return level;
	}
	
	public int getStage(){
		return stage;
	}
	
	public int getCandyLevel(){
		return candy_level;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getHighScore(){
		return highscore;
	}
	
	public int getLive(){
		return live;
	}
}

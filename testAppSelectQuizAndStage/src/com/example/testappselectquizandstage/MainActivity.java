package com.example.testappselectquizandstage;

import java.util.Random;
import java.util.Vector;

import android.R.bool;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	int[][] setQuiz = {{5, 0, 1},{7, 0, 8},{3, 8, 2},{5, 7, 3},{2, 7, 8},{9, 6, 5},{8, 6, 7},{0, 8, 7},{1, 6, 4},{1, 0, 8},{7, 3, 2},{0, 9, 3},{7, 9, 8},{5, 9, 6},{3, 8, 0},{1, 6, 4},{2, 6, 5},{3, 5, 6},{2, 8, 9},{2, 1, 7}};
	int Low = 0;
	int High = 5;
	int chooseSetQuiz = 0;
	int round = 0;
	int start = 0;
	String level = "easy";
	int stage = 1;
	int plusValueLevelID = 0;
	int plusValueStageID = 0;
	Random r = new Random();
	DataBase myquiz;
	Vector<getQuiz> quiz_list = new Vector<getQuiz>();
	Vector<GameDataBase> gameData = new Vector<GameDataBase>();
	boolean isGameOver = false;
	boolean isCorrect = true;
	int[] setSelectQuiz = new int[3];
	int perfect = 0;
	int live = 3;
	int candyLevel = 1;
	int score = 0;
	String ans = null;
	int highscore = 0;
//	boolean isHundred = false;
//	boolean isTen = false;
//	boolean isOne = false;
//	boolean isSym = false;
//	String ansHun = null;
//	String ansTen = null;
//	String ansOne = null;
//	String ansSym = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myquiz = new DataBase(getApplicationContext());
		
		selectQuiz();
		
		Button btnAns = (Button)findViewById(R.id.button1);
		Button btnCon = (Button)findViewById(R.id.button2);
		Button btnNew = (Button)findViewById(R.id.button3);
//		Button btnHun = (Button)findViewById(R.id.buttonHun);
//		Button btnTen = (Button)findViewById(R.id.ButtonTen);
//		Button btnOne = (Button)findViewById(R.id.ButtonOne);
//		Button btnSym = (Button)findViewById(R.id.buttonSym);
		
		btnNew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for(int i=0;i<gameData.size();i++){
					myquiz.deleteGameData(gameData.get(i).getLevel());
					Log.d("Game DataBase", "Level :: Size is "+myquiz.getGameData().size());
				}
				gameData.clear();
				level = "easy";
				score = 0;
				candyLevel = 1;
				stage = 1;
				highscore = 0;
				live = 3;
				TextView showScore = (TextView)findViewById(R.id.textShowScore);
				showScore.setText(""+score);
				selectQuiz();
			}
		});
		
		btnCon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myquiz.getWritableDatabase();
				gameData = myquiz.getGameData();
				if(gameData.size() != 0){
//					if(isCorrect){
					
						level = gameData.get(gameData.size()-1).getLevel();
						live = gameData.get(gameData.size()-1).getLive();
						if(isCorrect){
							stage = gameData.get(gameData.size()-1).getStage() + 1;
							score = gameData.get(gameData.size()-1).getScore();
						}
						else{
							stage = gameData.get(gameData.size()-1).getStage();
						}
						candyLevel = gameData.get(gameData.size()-1).getCandyLevel();
						TextView showScore = (TextView)findViewById(R.id.textShowScore);
						showScore.setText(""+score);
						TextView showLive = (TextView)findViewById(R.id.textShowLive);
						showLive.setText(""+live);
						selectQuiz();
//					}
				}
				else{
					level = "easy";
					stage = 1;
					candyLevel = 0;
					score = 0;
				}
			}
			
		});
		
		btnAns.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				if(ansSym != null){
//					ans = ansSym;
//					ansSym = null;
//				}
//				else{
//					if(ansHun != null){
//						ans = ansHun + "*" + ansTen + "*" + ansOne;
//						ansHun = null;
//						ansTen = null;
//						ansOne = null;
//					}
//					else {
//						if(ansTen != null){
//							ans = ansTen + "*" + ansOne;
//							ansTen = null;
//							ansOne = null;
//						}
//						else{
//							if(ansOne != null){
//								ans = ansOne;
//								ansOne = null;
//							}
//						}
//					}
//				}
				EditText textAns = (EditText)findViewById(R.id.editText1);
				ans = textAns.getText().toString();
				
				checkAnswer(ans);
			}
		});
		
//		btnHun.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(v.getId()==R.id.buttonHun){
//					//scan
//					isHundred = true;
//					IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
//					scanIntegrator.initiateScan();
//				}
//			}
//		});
//		
//		btnTen.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(v.getId()==R.id.ButtonTen){
//					//scan
//					isTen = true;
//					IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
//					scanIntegrator.initiateScan();
//				}
//			}
//		});
//		
//		btnOne.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(v.getId()==R.id.ButtonOne){
//					//scan
//					isOne = true;
//					IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
//					scanIntegrator.initiateScan();
//				}
//			}
//		});
//		
//		btnSym.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if(v.getId()==R.id.buttonSym){
//					//scan
//					isSym = true;
//					IntentIntegrator scanIntegrator = new IntentIntegrator(MainActivity.this);
//					scanIntegrator.initiateScan();
//				}
//			}
//		});

	}
	
//	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//		//retrieve scan result
//		IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//		if (scanningResult != null) {
//			//we have a result
//			String scanContent = scanningResult.getContents();
//			if(isHundred){
//				ansHun = scanContent;
//				isHundred = false;
//			}
//			if(isTen){
//				ansTen = scanContent;
//				isTen = false;
//			}
//			if(isOne){
//				ansOne = scanContent;
//				isOne = false;
//			}
//			if(isSym){
//				ansSym = scanContent;
//				isSym = false;
//			}
////			checkAnswer(scanContent);
//		}
//		else{
//		    Toast toast = Toast.makeText(getApplicationContext(), 
//		        "No scan data received!", Toast.LENGTH_SHORT);
//		    toast.show();
//		}
//		
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void selectQuiz(){
		
		myquiz.getWritableDatabase();
		if(start == 0){
			myquiz.createTableQuiz();
			quiz_list = myquiz.getAllQuiz();
			Log.d("Database", "it was successfull");
			start++;
			Log.d("Database", "Total : "+quiz_list.size());
		}
		
		gameData = myquiz.getGameData();
		TextView showDatabase = (TextView)findViewById(R.id.textShowDatabase);
		
		for(int i=0;i<gameData.size();i++){
			Log.d("Game DataBase", "Level : "+gameData.get(i).getLevel()+" Candy Level : "+gameData.get(i).getCandyLevel()+" Stage : "+gameData.get(i).getStage()+" HighScore : "+gameData.get(i).getHighScore());
		}
		
		if(gameData.size()==0){
			showDatabase.setText("Database is NULL.");
		}
		else{
			showDatabase.setText("Level : "+gameData.get(gameData.size()-1).getLevel()+" Stage : "+gameData.get(gameData.size()-1).getStage()+" Score : "+gameData.get(gameData.size()-1).getScore());
		}
		
		if((live >= 1 && round == 0) || !isCorrect){
			chooseSetQuiz = r.nextInt(High-Low)+Low;	
		}
		
		if(round < setSelectQuiz.length){
			if(live >= 1){
				if(!isCorrect){
					round = 0;
					stage = stage - 1;
//					perfect = 0;
					if(stage < 1){
						stage = 1;
					}
				}
			}
		}
		
		if(round == setSelectQuiz.length){
			round = 0;
			candyLevel = candyLevel + 1;
			perfect = 0;
			if(level.contentEquals("easy")){
				if(gameData.size() == 0){
					myquiz.addGameData(1,live,level,stage, candyLevel, score , highscore);
				}
				else{
//					for(int i = 0;i < gameData.size();i++){
//						if(gameData.get(i).getLevel() == "easy" && gameData.get(i).getScore() < score)
//							myquiz.editGameData(level, candyLevel, score);
//					}
//					if(gameData.get(0).getScore()<score){
						myquiz.editGameData(live,level,stage, candyLevel, score);
//					}
				}
//				stage = stage + 1;
				if(stage >= 6){
					stage = 0;
					highscore = score;
					if(highscore > gameData.get(0).getHighScore()){
						myquiz.editHighScore(level, highscore);
					}
					else{
						myquiz.editHighScore(level, gameData.get(0).getHighScore());
					}
					level = "normal";
					score = 0;
					highscore = 0;
					myquiz.addGameData(2,live,level,stage, candyLevel, score , highscore);
				}
			}
			if(level.contentEquals("normal")){
//				myquiz.addGameData(2,level,stage, candyLevel, score);
//				if(gameData.size() != 1){
					myquiz.editGameData(live,level,stage, candyLevel, score);
//				}
				if(stage >= 8){
					highscore = score;
					if(highscore > gameData.get(1).getHighScore()){
						myquiz.editHighScore(level, highscore);
					}
					else{
						myquiz.editHighScore(level, gameData.get(1).getHighScore());
					}
					level = "hard";
					stage = 0;
					score = 0;
					highscore = 0;
					myquiz.addGameData(3,live,level,stage, candyLevel, score , highscore);
				}
			}
			if (level.contentEquals("hard")){
//				myquiz.addGameData(3,level,stage, candyLevel, score);
//				if(gameData.size() != 2){
					myquiz.editGameData(live,level,stage, candyLevel, score);
//				}
				if(stage >= 9){
					highscore = score;
					if(highscore > gameData.get(2).getHighScore()){
						myquiz.editHighScore(level, highscore);
					}
					else{
						myquiz.editHighScore(level, gameData.get(2).getHighScore());
					}
					stage = 1;
					score = 0;
					level = "easy";
				}
			}
			stage = stage + 1;
//			showDatabase.setText("Level : "+gameData.get(gameData.size()-1).getLevel()+" Candy Level : "+gameData.get(gameData.size()-1).getCandyLevel()+" Score : "+gameData.get(gameData.size()-1).getScore());
		}
		
		if(level.compareToIgnoreCase("easy")==0)
			plusValueLevelID = 0;
		else if(level.compareToIgnoreCase("normal")==0)
			plusValueLevelID = 60;
		else
			plusValueLevelID = 140;
		
		if(stage == 1)
			plusValueStageID = 0;
		else if(stage == 2)
			plusValueStageID = 10;
		else if(stage == 3)
			plusValueStageID = 20;
		else if(stage == 4)
			plusValueStageID = 30;
		else if(stage == 5)
			plusValueStageID = 40;
		else if(stage == 6)
			plusValueStageID = 50;
		else if(stage == 7)
			plusValueStageID = 60;
		else if(stage == 8)
			plusValueStageID = 70;
		else if(stage == 9)
			plusValueStageID = 80;
		
		for(int i = 0 ; i < setQuiz[chooseSetQuiz].length ; i++){
			setSelectQuiz[i] = setQuiz[chooseSetQuiz][i] + plusValueLevelID + plusValueStageID;
		}
		
		ImageView quiz = (ImageView) findViewById(R.id.imageView1);
		Resources resources = this.getResources();
		int resourceID;
		
		String[] selectQuiz = quiz_list.get(setSelectQuiz[round]).getQuizs().split(".png");
		
		resourceID = resources.getIdentifier(selectQuiz[0], "drawable",
				"com.example.testappselectquizandstage");
		quiz.setImageResource(resourceID);
		TextView showLevel = (TextView)findViewById(R.id.textShowLevel);
		showLevel.setText(quiz_list.get(setSelectQuiz[round]).getLevel());
		TextView showStage = (TextView)findViewById(R.id.textShowStage);
		showStage.setText(quiz_list.get(setSelectQuiz[round]).getStage()+" - "+(round+1));
		
//		gameData = myquiz.getGameData();
//		if(gameData.size()==0){
//			showDatabase.setText("Database is NULL.");
////			level = "easy";
////			stage = 1;
////			candyLevel = 0;
////			score = 0;
//		}
//		else{
//			showDatabase.setText("Level : "+gameData.get(gameData.size()-1).getLevel()+" Candy Level : "+gameData.get(gameData.size()-1).getCandyLevel()+" Score : "+gameData.get(gameData.size()-1).getScore());
//			level = gameData.get(gameData.size()-1).getLevel();
//			stage = gameData.get(gameData.size()-1).getStage() + 1;
//			candyLevel = gameData.get(gameData.size()-1).getCandyLevel();
//			score = gameData.get(gameData.size()-1).getScore();
//		}
		
	}
	
	public void checkAnswer(String answer){
		String textResult = "Hello";
		TextView showLive = (TextView)findViewById(R.id.textShowLive);
		TextView showScore = (TextView)findViewById(R.id.textShowScore);
		if(answer.compareTo(quiz_list.get(setSelectQuiz[round]).getAnswer().toString())==0){
			isCorrect = true;
			perfect = perfect + 1;
			textResult = "CORRECT!!! Perfect X"+perfect;
			Log.d("Answer", textResult);
			score = score + quiz_list.get(setSelectQuiz[round]).getPoint();
			round = round + 1;
		}
		else{
			live = live - 1;
			candyLevel = candyLevel - 1;
			perfect = 0;
			isCorrect = false;
//			round = 0;
			if(live < 1){
				live = 3;
				stage = 0;
				score = 0;
				myquiz.editGameData(live,level,stage, candyLevel, score);
//				isGameOver = true;
//				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//
//				// set title
//				alertDialogBuilder.setTitle("GAME OVER!!");
//
//				// set dialog message
//				alertDialogBuilder
//						.setMessage("Sorry T^T")
//						.setCancelable(false)
//						.setPositiveButton("Main Menu",
//								new DialogInterface.OnClickListener() {
//									public void onClick(DialogInterface dialog,
//											int id) {
//										// if this button is clicked, just close
//										// the dialog box and do nothing
//										finish();
//										System.exit(0);
//									}
//
//								});
//
//				// create alert dialog
//				AlertDialog alertDialog = alertDialogBuilder.create();
//
//				// show it
//				alertDialog.show();
				textResult = "Game Over!!!";
				Log.d("Answer", textResult);
			}
			else{
				textResult = "WRONG!!!";
				Log.d("Answer", textResult);
				score = score/4;
				if(score < 0){
					score = 0;
				}
			}
//			round = 0;
		}
		if(isCorrect)
			Log.d("Answer", quiz_list.get(setSelectQuiz[round-1]).getAnswer()+" "+answer);
		else{
			Log.d("Answer", quiz_list.get(setSelectQuiz[round]).getAnswer()+" "+answer);
		}
		showScore.setText(""+score);
		showLive.setText(""+live);
//		round = 0;
//		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
//
//		// set title
//		alertDialogBuilder.setTitle("Your Answer is...");
//
//		// set dialog message
//		alertDialogBuilder
//				.setMessage(textResult)
//				.setCancelable(false)
//				.setPositiveButton("Main Menu",
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,
//									int id) {
//								// if this button is clicked, just close
//								// the dialog box and do nothing
//								finish();
//								System.exit(0);
//							}
//
//						});
//
//		// create alert dialog
//		AlertDialog alertDialog = alertDialogBuilder.create();
//
//		// show it
//		alertDialog.show();
		
		selectQuiz();
	}
}

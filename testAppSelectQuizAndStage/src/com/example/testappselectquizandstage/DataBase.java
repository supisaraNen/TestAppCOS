package com.example.testappselectquizandstage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

	//	table Colors
//	public static final String TABLE_NAME = "Colors";
//	public static final String COLUMN_ID = "_id";
//	public static final String COLUMN_PIC = "picture";
//	public static final String COLUMN_RED = "red";
//	public static final String COLUMN_GREEN = "green";
//	public static final String COLUMN_BLUE = "blue";
	private static final String DATABASE_NAME = "test3";

	//	table Quiz
	public static final String TABLE2_NAME = "Quiz";
	public static final String COLUMN2_ID = "_id";
	public static final String COLUMN2_LEVEL = "level";
	public static final String COLUMN2_STAGE = "state";
	public static final String COLUMN2_QUIZ = "quiz";
	public static final String COLUMN2_POINT = "point";
	public static final String COLUMN2_ANS = "ans";
	
	public static final String TABLE3_NAME = "Game";
	public static final String COLUMN3_ID = "_id";
	public static final String COLUMN3_LIVE = "live";
	public static final String COLUMN3_LEVEL = "level";
	public static final String COLUMN3_STAGE = "stage";
	public static final String COLUMN3_CANDYLEVEL = "candy_level";
	public static final String COLUMN3_SCORE = "score";
	public static final String COLUMN3_HIGHSCORE = "highscore";
	
	Context context;
//	private Vector<getColor> color_list = new Vector<getColor>();
	private Vector<getQuiz> quiz_list = new Vector<getQuiz>();
	private Vector<GameDataBase> gameData = new Vector<GameDataBase>();

	public DataBase(Context ctx) {
		super(ctx, DATABASE_NAME, null, 1);
		context = ctx;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create table colors and quiz.
//		db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID
//				+ " INTEGER PRIMARY KEY , " + COLUMN_PIC + " TEXT ,"
//				+ COLUMN_RED + " INTEGER ," + COLUMN_GREEN + " INTEGER ,"
//				+ COLUMN_BLUE + " INTEGER" + ");");
//
//		Log.d("CREATE TABLE", "Create Color Table Successfully.");
		
		db.execSQL("CREATE TABLE " + TABLE2_NAME + "(" + COLUMN2_ID
	            + " INTEGER PRIMARY KEY ,"
	            + COLUMN2_LEVEL+ " TEXT ,"
	            + COLUMN2_STAGE+ " TEXT ,"
	            + COLUMN2_QUIZ+ " TEXT ,"
	            + COLUMN2_POINT+ " INTEGER ,"
	            + COLUMN2_ANS+ " TEXT "+");");
		
		Log.i("CREATE TABLE","Create Quiz Table Successfully.");
		
		db.execSQL("CREATE TABLE " + TABLE3_NAME + "(" + COLUMN3_ID
	            + " INTEGER PRIMARY KEY,"
	            + COLUMN3_LIVE+ " INTEGER ,"
	            + COLUMN3_LEVEL+ " TEXT ,"
	            + COLUMN3_STAGE+ " INTEGER ,"
	            + COLUMN3_CANDYLEVEL+ " INTEGER ,"
	            + COLUMN3_SCORE+ " INTEGER ,"
	            + COLUMN3_HIGHSCORE+ " INTEGER "+");");
		
		Log.i("CREATE TABLE","Create Game Table Successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE3_NAME);
		onCreate(db);

	}
	
	//	import the color data(from file .csv) into database.
//	public void createTableColor(){
//		SQLiteDatabase db = this.getWritableDatabase();
//		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					context.getAssets().open("tablecolor.csv")));
//			String readLine = null;
//			readLine = br.readLine();
//			try {
//				while ((readLine = br.readLine()) != null) {
//					Log.i("Data Input", readLine);
//					String[] str = readLine.split(",");
//					ContentValues values = new ContentValues();
//					values.put(COLUMN_ID, Integer.parseInt(str[0]));
//				    values.put(COLUMN_PIC, str[1]);
//				    values.put(COLUMN_RED, Integer.parseInt(str[2]));
//				    values.put(COLUMN_GREEN, Integer.parseInt(str[3]));
//				    values.put(COLUMN_BLUE, Integer.parseInt(str[4]));
//					
//				    db.insert(TABLE_NAME, null, values);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	// Getting All Color
//	public Vector<getColor> getAllColor() {
//		try {
//			color_list.clear();
//			// Select All Query
//			String selectQuery = "SELECT * FROM " + TABLE_NAME;
//			SQLiteDatabase db = this.getWritableDatabase();
//			Cursor cursor = db.rawQuery(selectQuery, null);
//			// looping through all rows and adding to list
//			if (cursor.moveToFirst()) {
//				do {
//					getColor color = new getColor(Integer.parseInt(cursor
//							.getString(0)), cursor.getString(1),
//							Integer.parseInt(cursor.getString(2)),
//							Integer.parseInt(cursor.getString(3)),
//							Integer.parseInt(cursor.getString(4)));
//					color_list.add(color);
//				} while (cursor.moveToNext());
//			}
//			// return contact list
//			cursor.close();
//			db.close();
//			return color_list;
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("all_records", "" + e);
//		}
//		return color_list;
//	}
	
	//	import the quiz data(from file .csv) into database.
	public void createTableQuiz(){
		SQLiteDatabase db = this.getWritableDatabase();
		try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("database_quiz.csv")));  
            String readLine = null;  
            readLine = br.readLine();    
            try {  
                while ((readLine = br.readLine()) != null) {  
                	Log.i("Data Input", readLine);
                    String[] str = readLine.split(",");  
                    ContentValues values = new ContentValues();
					values.put(COLUMN2_ID, Integer.parseInt(str[0]));
					values.put(COLUMN2_LEVEL, str[1]);
					values.put(COLUMN2_STAGE, str[2]);
				    values.put(COLUMN2_QUIZ, str[3]);
				    values.put(COLUMN2_POINT, Integer.parseInt(str[4]));
				    values.put(COLUMN2_ANS, str[5]);
					
				    db.insert(TABLE2_NAME, null, values);
                    
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
	
	// Getting All Quiz
	public Vector<getQuiz> getAllQuiz() {
        try {
            quiz_list.clear();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE2_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    getQuiz quiz = new getQuiz(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            Integer.parseInt(cursor.getString(4)),
                            cursor.getString(5)
                    );
                   quiz_list.add(quiz);
                } while (cursor.moveToNext());
            }
            // return contact list
            cursor.close();
            db.close();
            return quiz_list;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_records", "" + e);
        }
        return quiz_list;
    }
	
	public void addGameData(int id,int live,String level,int stage,int candyLevel,int score,int highscore) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN3_ID, id);
		values.put(COLUMN3_LIVE, live);
		values.put(COLUMN3_LEVEL, level);
		values.put(COLUMN3_STAGE, stage);
		values.put(COLUMN3_CANDYLEVEL, candyLevel);
	    values.put(COLUMN3_SCORE, score);
	    values.put(COLUMN3_HIGHSCORE, highscore);
		
	    db.insert(TABLE3_NAME, null, values);
	}
	
	public void editGameData(int live,String level,int stage,int candyLevel,int score) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN3_LIVE, live);
		values.put(COLUMN3_STAGE, stage);
		values.put(COLUMN3_CANDYLEVEL, candyLevel);
	    values.put(COLUMN3_SCORE, score);
//	    values.put(COLUMN3_HIGHSCORE, highscore);
		
	    db.update(TABLE3_NAME, values, "level = ?", new String[] { level });
	}
	
	public void editHighScore(String level,int highscore){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();;
	    values.put(COLUMN3_HIGHSCORE, highscore);
		
	    db.update(TABLE3_NAME, values, "level = ?", new String[] { level });
	}
	
	public Vector<GameDataBase> getGameData() {
		try {
           gameData.clear();
            // Select All Query
            String selectQuery = "SELECT * FROM " + TABLE3_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    GameDataBase game = new GameDataBase(
                            Integer.parseInt(cursor.getString(0)),
                            Integer.parseInt(cursor.getString(1)),
                            cursor.getString(2),
                            Integer.parseInt(cursor.getString(3)),
                            Integer.parseInt(cursor.getString(4)),
                            Integer.parseInt(cursor.getString(5)),
                            Integer.parseInt(cursor.getString(6))
                    );
                   gameData.add(game);
                } while (cursor.moveToNext());
            }
            // return contact list
            cursor.close();
            db.close();
            return gameData;
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("all_records", "" + e);
        }
        return gameData;
	}
	
	public void deleteGameData(String level){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE3_NAME, COLUMN3_LEVEL + " =? ", new String[]{ level });
	}
}

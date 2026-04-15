package com.example.MySQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	static String name="com.example.activity.mstx";
	static int dbVersion=3;
	public DatabaseHelper(Context context) {
		super(context, name, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTableRandom="create table random(id int(10) primary key,";
		createTableRandom+="uid varchar(10),sculture varchar(20),";
		createTableRandom+="uploadTime datetime,picPath varchar(500),";
		createTableRandom+="introduce varchar(500),label varchar(20),";
		createTableRandom+="city varchar(20),slike int(5),";
		createTableRandom+="collection int(5),pinglun int(5))";
		//随拍表
		System.out.println("sql "+createTableRandom);
		db.execSQL(createTableRandom);
		
		String createTabeMenu="create table if not exists menu(" +
				"id int(10) primary key," +
				"cname varchar(30)," +
				"uid varchar(10)," +
				"primaryPic varchar(30)," +
				"difficulty varchar(10)," +
				"flavour varchar(10)," +
				"ctime varchar(10)," +
				"tools varchar(10)," +
				"craft varchar(10)," +
				"food varchar(500)," +
				"codiments varchar(500)," +
				"introduction varchar(500)," +
				"tips varchar(50),slike int(5),collection int(5),"+
				"pinglun int(5),uploadTime datetime,sculture varchar(20)" +
				");";
		System.out.println("sql "+createTabeMenu);
		//菜谱表	
		db.execSQL(createTabeMenu);
		
		String proc="create table pro(id int(5) primary key,step1 varchar(200),step2 varchar(200)," +
				"step3 varchar(200),step4 varchar(200),step5 varchar(200),step6 varchar(200),step7 varchar(200)," +
				"step8 varchar(200),step9 varchar(200),step10 varchar(200),step11 varchar(200)," +
				"step12 varchar(200),step13 varchar(200),step14 varchar(200),step15 varchar(200)," +
				"step16 varchar(200),step17 varchar(200),step18 varchar(200),step19 varchar(200)," +
				"step20 varchar(200))";
		System.out.println("sql "+proc);
		//菜品制作过程
		db.execSQL(proc);
		
		String label="create table labels(label varchar(20) PRIMARY KEY)";
		//标签表
		db.execSQL(label);	
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}	
}
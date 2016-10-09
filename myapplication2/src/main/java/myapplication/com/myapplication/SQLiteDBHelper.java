package myapplication.com.myapplication;

import android.database.sqlite.SQLiteDatabase;

import java.io.File;


public class SQLiteDBHelper {
	private static String DB_PATH = SDcardHelper.getSDCardPath();
	private static String DB_NAME = "student.db";
	
	static {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH+File.separator+DB_NAME, null);
		db.execSQL("create table if not exists student(_id integer primary key autoincrement," +
				"name varchar(20)," +
				"sex varchar(4)," +
				"address varchar(100)," +
				"money integer)");
		db.close();
	}
	
	public static SQLiteDatabase getSqLiteDatabase() {
		return SQLiteDatabase.openOrCreateDatabase(DB_PATH+File.separator+DB_NAME, null);
	}
	
}

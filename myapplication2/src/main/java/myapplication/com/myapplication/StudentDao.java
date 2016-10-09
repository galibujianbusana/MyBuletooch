package myapplication.com.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	

	
	public boolean insert(Student stu) {
		SQLiteDatabase db = SQLiteDBHelper.getSqLiteDatabase();
		try {			
			db.execSQL("insert into student (name,sex,address,money) values(?,?,?,?)", 
					new Object[]{stu.getName(),stu.getSex(),stu.getAddress(),stu.getMoney()});
			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

	public void delete(int id) {
		SQLiteDatabase db = SQLiteDBHelper.getSqLiteDatabase();
		//db.execSQL("delete from student where _id = ?", new Object[]{student.getId()});		
		db.execSQL("delete from student where _id = "+id);
		db.close();
	}
	

	public void update(Student stu) {
		SQLiteDatabase db = SQLiteDBHelper.getSqLiteDatabase();
		db.execSQL("update student set name = ?,sex=?,address=?,money=? where _id = ?",
				new Object[]{stu.getName(),stu.getSex(),stu.getAddress(),stu.getMoney(),stu.getId()});
		db.close();
	}
	

	public Student findById(int id) {
		SQLiteDatabase db = SQLiteDBHelper.getSqLiteDatabase();
		Cursor cursor = db.rawQuery("select * from student where _id = ?", new String[]{id+""});
		Student student = null;
		if (cursor.moveToNext()) {
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex = cursor.getString(cursor.getColumnIndex("sex"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			int money = cursor.getInt(cursor.getColumnIndex("money"));
			student = new Student(_id, name, sex, address, money);
		}
		return student;
	}
	

	public List<Student> findAll() {		
		List<Student> list = new ArrayList<Student>();
		SQLiteDatabase db = SQLiteDBHelper.getSqLiteDatabase();
		//  db.rawQuery("select * from 表名 orderby KEY_ENDTIME"，null);
		//
		//　KEY_ENDTIME ASC 升序
		//　KEY_ENDTIME DESC 降序
		//Cursor cursor = db.rawQuery("select * from student", null);
		Cursor cursor = db.rawQuery("select * from student  order by money ASC",null);
		Student student = null;
		while (cursor.moveToNext()) {
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sex = cursor.getString(cursor.getColumnIndex("sex"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			int money = cursor.getInt(cursor.getColumnIndex("money"));
			student = new Student(_id, name, sex, address, money);
			list.add(student);
		}
		return list;
	}

}

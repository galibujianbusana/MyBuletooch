package myapplication.com.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    StudentDao studentDao = new StudentDao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert(View v) {
        studentDao.insert(new Student(2, "冯", "男", "航海路阳光城", 300));
        studentDao.insert(new Student(2, "魏", "男", "东建材", 3));
        studentDao.insert(new Student(2, "冯", "男", "航海路阳光城", 600));


    }
    public void delete(View v) {
        studentDao.delete(1);
    }
    public void update(View v) {
        studentDao.update(new Student(3, "标哥", "男", "南京", 10));
    }
    public void findId(View v) {
        Student student = studentDao.findById(4);
        Toast.makeText(this, student.toString(), Toast.LENGTH_SHORT).show();
    }
    public void findAll(View v) {
        List<Student> list = studentDao.findAll();
        Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show();
    }
}

package myapplication.com.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import myapplication.com.myapplication.adapter.Myadapter;

public class RiziList_Activity extends Activity {

    ListView listView;
    StudentDao studentDao=new StudentDao();
    List<Student> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rizi_list);
        initView();
        data=getData();
        Myadapter myadapter=new Myadapter(getApplicationContext(),data);
        listView.setAdapter(myadapter);

    }
    public  void initView(){
        listView= (ListView) findViewById(R.id.listview);

    }
    public List<Student> getData(){
        List<Student> list=studentDao.findAll();
     return list;
    }
}

package myapplication.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import myapplication.com.myapplication.R;
import myapplication.com.myapplication.Student;
import myapplication.com.myapplication.Utils.JinzhiForm;

/**
 * Created by Administrator on 2016/9/29.
 */
public class Myadapter extends BaseAdapter{
    List<Student> data;
    Context context;

    public Myadapter(Context context, List<Student> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.layout_rizhiitem,null);
            holder=new ViewHolder();
            holder.textView1= (TextView) convertView.findViewById(R.id.text1);
            holder.textView2= (TextView) convertView.findViewById(R.id.text2);
            holder.textView3= (TextView) convertView.findViewById(R.id.text3);
            holder.textView4= (TextView) convertView.findViewById(R.id.text4);
            holder.textView5= (TextView) convertView.findViewById(R.id.text5);
            holder.textView6= (TextView) convertView.findViewById(R.id.text6);
            holder.textView7= (TextView) convertView.findViewById(R.id.text7);
            holder.textView8= (TextView) convertView.findViewById(R.id.text8);
            holder.textView9= (TextView) convertView.findViewById(R.id.text9);
            holder.textView10= (TextView) convertView.findViewById(R.id.text10);
            holder.textView_time= (TextView) convertView.findViewById(R.id.textview_time);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        Student bean=data.get(position);
        String time=bean.getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Time=sdf.format(Long.parseLong(time));
        holder.textView_time.setText(Time);
        String  msg1=bean.getSex();
        JinzhiForm jinzhiForm=new JinzhiForm();
        String msg=jinzhiForm.Fromerjinzhi(msg1);
        //String msg = "0000000000";
        String x1=msg.substring(0,1);
        String x2=msg.substring(1,2);
        String x3=msg.substring(2,3);
        String x4=msg.substring(3,4);
        String x5=msg.substring(4,5);
        String x6=msg.substring(5,6);
        String x7=msg.substring(6,7);
        String x8=msg.substring(7,8);
        String x9=msg.substring(8,9);
        String x10=msg.substring(9,10);
        if(x1.equals("0")){
            holder.textView1.setText("缺失");
            holder.textView1.setTextColor(Color.RED);
        }
        if(x2.equals("0")){
            holder.textView2.setText("缺失");
            holder.textView2.setTextColor(Color.RED);
        }
        if(x3.equals("0")){
            holder.textView3.setText("缺失");
            holder.textView3.setTextColor(Color.RED);
        }
        if(x4.equals("0")){
            holder.textView4.setText("缺失");
            holder.textView4.setTextColor(Color.RED);
        }
        if(x5.equals("0")){
            holder.textView5.setText("缺失");
            holder.textView5.setTextColor(Color.RED);
        }

        if(x6.equals("0")){
            holder.textView6.setText("缺失");
            holder.textView6.setTextColor(Color.RED);
        }

        if(x7.equals("0")){
            holder.textView7.setText("缺失");
            holder.textView7.setTextColor(Color.RED);
        }

        if(x8.equals("0")){
            holder.textView8.setText("缺失");
            holder.textView8.setTextColor(Color.RED);
        }
        if(x9.equals("0")){
            holder.textView9.setText("缺失");
            holder.textView9.setTextColor(Color.RED);
        }

        if(x10.equals("0")){
            holder.textView10.setText("缺失");
            holder.textView10.setTextColor(Color.RED);
        }
        return convertView;
    }

   class ViewHolder{
       TextView textView_time;
       TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    }
}
